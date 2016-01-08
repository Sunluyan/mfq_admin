package com.mfq.admin.web.cache;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import com.mfq.admin.web.bean.SysPassport;
import com.mfq.admin.web.bean.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mfq.admin.web.context.SpringWrapper;
import com.mfq.admin.web.services.PassportService;
import com.mfq.admin.web.services.SysUserService;


public class SysUserCache {

    private static Logger logger = LoggerFactory.getLogger(SysUserCache.class);

    private static Cache<String, SysUser> cache = CacheBuilder.newBuilder()//
            .initialCapacity(1000).maximumSize(1000)// 1000是包括无效的ticket，加入黑名单
            .expireAfterWrite(10, TimeUnit.MINUTES)// 缓存10分钟
            .build();

    private static final SysUser DEAD_USER = new SysUser();

    /**
     * 从cookie中验证票据并解析用户信息
     * 
     * @param cookiePassport
     *            cookie中票据(只有uid/ticket)
     * @param cacheable
     *            是否缓存结果（如果验票成功，缓存用户信息）
     * @return 用户信息或者null
     * @throws Exception
     *             任何异常
     */
    public static SysUser validateUser(SysPassport cookiePassport, boolean cacheable)
            throws Exception {
        if (cookiePassport == null || cookiePassport.getUid() <= 0) {
            return null;
        }
        String ticket = cookiePassport.getTicket();
        SysUser user = cacheable ? getUser(ticket) : null;

        if (user == null) {
            SysUserService sysUserService = (SysUserService) SpringWrapper
                    .getBean("sysUserService");
            PassportService passportService = (PassportService) SpringWrapper
                    .getBean("passportService");
            SysPassport validPassport = passportService
                    .validateTicket(cookiePassport.getUid(), ticket);
            logger.info("----------------" + validPassport.getUid()
                    + "-------------");
            if (validPassport == null || validPassport.getUid() <= 0) {
                // 无效的票据，加入黑名单
                DEAD_USER.setId(0l);
                cache.put(ticket, DEAD_USER);
                return null;
            }
            user = sysUserService.querySysUser(validPassport.getUid());
            if (cacheable) {
                cacheUser(ticket, user);
            }
        }
        return user != DEAD_USER ? user : null;
    }

    /**
     * 获取某个ticket的缓存用户
     * 
     * @param ticket
     *            票据信息
     * @return 用户信息或者null
     */
    public static SysUser getUser(String ticket) {
        return ticket != null ? cache.getIfPresent(ticket) : null;
    }

    /**
     * 缓存某个用户
     * 
     * @param ticket
     *            票据信息
     * @param user
     *            用户信息
     */
    public static void cacheUser(String ticket, SysUser user) {
        if (user != null && user.getId() > 0) {
            cache.put(ticket, user);
        }
    }

    public static void invalidUser(String ticket) {
        if (ticket != null) {
            cache.invalidate(ticket);
        }
    }
    
    // 需要用redis缓存一段时间，假如有更新的情况下，reload用户权限
    public static boolean hasAuthority(Menu menu, SysUser user){
        return true;
    }
    
    // reload
    public static boolean reload(Menu menu, SysUser user){
        return true;
    }
}