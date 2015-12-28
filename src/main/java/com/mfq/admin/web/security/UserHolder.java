/**
 * 
 */
package com.mfq.admin.web.security;

import org.apache.commons.lang.StringUtils;

/**
 * 后台登录用户上下文
 */
public class UserHolder {

    private static ThreadLocal<String> ipLocal = new ThreadLocal<String>();
    private static ThreadLocal<Long> hosplIdLocal = new ThreadLocal<Long>();
    private static ThreadLocal<UserDetail> userDetailLocal = new ThreadLocal<>();

    public static String currentIpAddress() {
        return ipLocal.get() == null ? "UNKNOW" : ipLocal.get();
    }

    public static void setCurrentIpAddress(String ip) {
        ipLocal.set(ip);
    }

    public static Long currentUserHosplId() {
        return hosplIdLocal.get() == null ? 0L : hosplIdLocal.get();
    }

    public static void setUserHosplId(Long hosplId) {
        hosplIdLocal.set(hosplId);
    }

    public static void setUserDetail(UserDetail userDetail) {
        userDetailLocal.set(userDetail);
    }

    public static void clear() {
        userDetailLocal.remove();
        hosplIdLocal.remove();
        ipLocal.remove();
    }

    public static UserDetail currentUserDetail() {
        return userDetailLocal.get();
    }

    public static long getUserId() {
        if (userDetailLocal.get() == null
                || userDetailLocal.get().getSysUser() == null) {
            return 0;
        } else {
            return userDetailLocal.get().getSysUser().getId();
        }
    }

    public static String currentUser() {
        UserDetail userDetail = currentUserDetail();
        return userDetail != null ? userDetail.getUsername() : null;
    }

    /**
     * 当前用户是否已经登录
     * 
     * @return true 如果登录
     */
    public static boolean isLogin() {
        return StringUtils.isNotBlank(currentUser());
    }
}
