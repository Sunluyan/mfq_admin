package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.SysPassport;
import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.bean.example.SysPassportExample;
import com.mfq.admin.web.dao.SysPassportMapper;
import com.mfq.admin.web.dao.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.admin.web.constants.Status;
import com.mfq.admin.web.utils.CommonUtil;
import com.mfq.admin.web.utils.DateUtil;
import com.mfq.admin.web.utils.PasswordUtils;

@Service
public class PassportService {

    private static final Logger logger = LoggerFactory.getLogger(PassportService.class);
    
    private final static long TICKET_PERSISTEN_TIME = TimeUnit.DAYS
            .toMillis(14); // 记住密码的有效期14天
    private final static long TICKET_TEMP_TIME = TimeUnit.DAYS.toMillis(7); // 临时登录的有效期6小时
    private final static long TICKET_DELAY_TIME = TICKET_TEMP_TIME / 2; // 每个三个小时刷新一次时间

    @Resource
    SysPassportMapper mapper;
    @Resource
    SysUserMapper sysUserMapper;
    
    /**
     * 新的登录方式
     * 返回的passport中uid大于0成功，等于0验证失败，小于0用户不存在
     * @param password-md5( md5(plainPassword+salt))
     * @return 登录信息，登录成功后会更新时间
     */
    public SysPassport login(long uid, String password) {
        SysPassport passport = queryPassport(uid);
        if (passport.getUid() == 0) {
            passport.setUid(-1l); // 用户不存在
            return passport;
        }
        // 验证密码是否成功
        if (!StringUtils.equalsIgnoreCase(passport.getPassword(), PasswordUtils.encode(password))) {
            // 验证不通过, 登录失败
            passport.setUid(0l);
            passport.setPassword("");
            passport.setTicket("");
        }
        return defaultFlushTicketTime(passport, true, true, false);
    }

    public SysPassport validateTicket(long uid, String ticket) {
        //TODO 暂用该方法,试试别的
        SysPassport passport = mapper.queryValidPassportByTicket(uid, ticket, Status.DELETED.getValue());

       // SysPassport passport = null;
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(uid);
        if(sysUser.getStatus().getValue() != Status.DELETED.getValue()){
           // SysPassportExample example = new SysPassportExample();
           // example.or().andUidEqualTo(uid).andTicketEqualTo(ticket).andExpiredAtLessThan(new Date());
            //System.out.println("--------------"+mapper.selectByExample(example));
            //passport = mapper.selectByExample(example).get(0);
        }
        logger.info("validate from db passport={}", passport);
        if (passport == null) {
            passport = new SysPassport();
            passport.setUid(0l);
        } else {
            // 验证票据成功，此时要刷新票据有效期为新value
            defaultFlushTicketTime(passport, false, false, false);
        }
        return passport;
    }

    /**
     * 创建一个passport，此passport带有ticket以及salt等信息
     */
    public SysPassport createPassport(long uid, String plainPassword) {
        SysPassport passport = new SysPassport();
        passport.setUid(uid);
        passport.setTicket(CommonUtil.createTicket());
        passport.setCreatedAt(new Date());
        passport.setActivedAt(new Date());
        passport.setExpiredAt(DateUtil.addDay(new Date(), 7));
        passport.setPassword(PasswordUtils.encode(plainPassword));
        mapper.insert(passport);
        return passport;
    }


    /**
     * 创建一个passport，此passport带有ticket以及salt等信息
     */
    public SysPassport updatePassport(long uid, String plainPassword) {
        mapper.updateByPassport(uid,PasswordUtils.encode(plainPassword));

        return queryPassport(uid);
    }

    public SysPassport queryPassport(long uid) {

        return mapper.selectByPrimaryKey(uid);
    }

    public boolean flushTicketTime(long uid, long expiredTime)
    {
        SysPassport passport = new SysPassport();
        passport.setUid(uid);
        passport.setExpiredAt(new Date(expiredTime));
        return mapper.updateByPrimaryKeySelective(passport) == 1;
    }

    public boolean destroyPassport(long uid)
    {
        SysPassport passport = new SysPassport();
        passport.setUid(uid);
        passport.setExpiredAt(new Date());
        return mapper.updateByPrimaryKeySelective(passport) == 1;
    }

    /**
     * 按照默认的规则刷新ticket时间
     * <ul>
     * <li>登录操作：
     * <ol>
     * <li>如果ticket已经过期，则ticket值变化（新的ticket值）</li>
     * <li>记住密码，有效期从登录起14天后</li>
     * <li>非记住密码，有效期从登录起6小时</li>
     * <li>第三方登录，有效期从登录起6小时</li>
     * </ol>
     * </li>
     * <li>刷新操作：过期时间小于3小时，延长至6小时，否则什么不操作</li>
     * <li>修改密码：ticket值变化，有效期不变</li>
     * </ul>
     * 
     * @param passport
     *            当前票据
     * @param isLogin
     *            是否登录操作
     * @param autoLogin
     *            是否自动登录 (当前仅当登录操作并且是自动登录)
     * @param resetPassword
     *            是否修改密码,如果是修改密码，则用户强制退出登录
     */
    private SysPassport defaultFlushTicketTime(SysPassport passport, boolean isLogin,
            boolean autoLogin, boolean resetPassword) {
        if (passport.getUid() <= 0)
            return passport;// 密码验证不通过
        long uid = passport.getUid();
        boolean newTicket = resetPassword;// 是否需要新的ticket
        long extendTime = 0L;// 延长ticket的时间
        if (!newTicket) {
            if (isLogin) {
                extendTime = autoLogin ? TICKET_PERSISTEN_TIME
                        : TICKET_TEMP_TIME;
                newTicket = (passport.getExpiredAt().getTime() - System
                        .currentTimeMillis()) < 0;
            } else {
                extendTime = (passport.getExpiredAt().getTime()
                        - System.currentTimeMillis()) < TICKET_DELAY_TIME
                                ? TICKET_TEMP_TIME : 0L;
            }
        }
        if (newTicket || extendTime > 0) {
            if (newTicket) {
                passport.setTicket(CommonUtil.createTicket());
                passport.setCreatedAt(new Date());
            }
            if (extendTime > 0) {
                passport.setExpiredAt(new Date(System.currentTimeMillis() + extendTime));
            }
            mapper.updateDefaultTicket(uid, passport.getTicket(),
                    passport.getCreatedAt(), passport.getExpiredAt());
        }
        return passport;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        SysPassportMapper mapper = ac.getBean(SysPassportMapper.class);

        SysPassportExample example = new SysPassportExample();
        List<SysPassport> list = mapper.selectByExample(example);
        for (SysPassport sysPassport : list) {
            System.out.println(sysPassport.toString());
        }
    }
}