package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mfq.admin.web.bean.SysPassport;
import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.bean.example.SysUserExample;
import com.mfq.admin.web.dao.SysUserMapper;
import com.mfq.admin.web.security.UserDetail;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.utils.CookieUtils;
import com.mfq.admin.web.utils.PasswordUtils;
import com.mfq.admin.web.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.admin.web.constants.Status;

@Service
public class SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserService.class);



    @Resource
    SysUserMapper mapper;
    @Resource
    PassportService passportService;
    
    private static final int LIMIT = 20;
    
    
    /**
     * 事务管理，用于关联用户创建
     * @param mobile
     * @return
     */
    public long createUser(String username, String realname, String mobile, long roleId) {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(realname)) {
            throw new RuntimeException("username and realname both are empty");
        }
        SysUser sysUser = new SysUser(username, realname, mobile, roleId);
        insertSysUser(sysUser);
        return sysUser == null? 0 : sysUser.getId();
    }
    
    public List<SysUser> querySysUserByPage(Long page) {
        if(page == null){
            page = 1L;
        }
        long start = (page - 1) * LIMIT;
        List<SysUser> users = mapper.querySysUserByPage(start, LIMIT);
        if(CollectionUtils.isEmpty(users)){
            users = Lists.newArrayList();
        }
        return users;
    }
    
    public long insertSysUser(SysUser user){
        return mapper.insert(user);
    }
    
    public SysUser querySysUser(long uid) {
        SysUser user = mapper.selectByPrimaryKey(uid);
        if(user == null){
            user = new SysUser();
        }
        return user;
    }


    public SysUser queryUserByName(String username) {
        SysUserExample example = new SysUserExample();
        example.or().andUsernameEqualTo(username);

        List<SysUser> users = mapper.selectByExample(example);
        if(users.size()>0){
            return  users.get(0);
        }

        return null;

    }

    public boolean updateStatus(long uid, Status status) {
        SysUser user = new SysUser();
        user.setId(uid);
        user.setStatus(status);
        return mapper.updateByPrimaryKeySelective(user) == 1;
    }

    public String checkSysUserParams(){
        String ret = "";


        return ret;
    }

    public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		SysUserService service = ac.getBean(SysUserService.class);
		System.out.println(service.queryUserByName("hui.zhang"));
	}


    public String resetUserInfo(HttpServletRequest request, String username, String realname, String moible, String opass, String npass, String cpass) {
        String ret = "";

        try {

            SysUserExample example = new SysUserExample();
            example.or().andUsernameEqualTo(username);
            List<SysUser> users = mapper.selectByExample(example);

            if(users.size()<1){
                logger.error("无法获取user..");
                ret = "无法获取用户!!!";
            }
            SysUser user = users.get(0);
            if(user==null||user.getId()<1){
                ret="无法获取用户!";
            }

            user.setMobile(moible);
            user.setRealname(realname);
            mapper.updateByExample(user, example);

            UserDetail userDetail = new UserDetail(user); // need fix me!
            UserHolder.setUserDetail(userDetail);

            ret="修改成功";
            if(StringUtils.isNotBlank(npass)) {
                if (!username.isEmpty() && !opass.isEmpty()) {
                    logger.info("come to if , username="+username);
                    String passwordMd5 = PasswordUtils.encode(opass);

                    if (user != null) {
                        SysPassport passport = passportService.login(user.getId(),
                                passwordMd5);

                        if (passport.getUid() > 0) {

                            logger.info("user resetpass username ,request.getRemoteAddr {} | {}", username,
                                    String.format("[%s] login ok.", username), request.getRemoteAddr());
                            passportService.updatePassport(user.getId(), npass); // 创建passport
                            ret="密码修改成功";
                        }else {
                            ret= "密码错误!!";
                        }

                    }else {
                        ret="密码错误!!!";
                    }
                }

            }


        }catch (Exception e){
            ret="系统错误!!!";
            logger.error("reset sysuser info is {}",e);

        }


        return ret;
    }

    private boolean resertInfo(String name, String realname, String moible, String npass) throws Exception {
        boolean ret = false;
        try {

            ret = true;
        }catch (Exception e){
            ret = false;
            throw new Exception("修改用户错误."+name+" e is {}",e);
        }

        return ret;
    }
}
