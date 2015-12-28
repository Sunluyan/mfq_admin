package com.mfq.admin.web.constants;

import com.mfq.admin.web.utils.Config;

public class AdminConstants {

    public static int SUCCESS = 0;

    public static int NEED_LOGIN = 900; // 需要登录
    public static int FAIL = 999; // 失败
    public static int CONTENT_TOO_LONG = 911;
    public static int UNLAWFUL_CONTENT = 901; // 非法内容
    public static int PARAMUNLAWFUL = 902; // 非法 param
    public static int UNLAWFULL_OPERATE = 903; // 非法操作

    public static final String TOKEN_COOKIE = "token";
    public static final String FORBIDDEN_OPEN_LOGIN = "_forbidden";// 在一定时间内禁止第三方自动登录(例如主站）

    public static final String SOURCE_ME = "adminweb";

    // 公共权限
    public static final int PUBLIC_AUTHORITY = 0;

    public static final String WEB_SITE = Config.getItem("web_site");
    public static final String ADMIN_SITE = Config.getItem("admin_site");

    public static final String WEB_DOMAIN = Config.getItem("web_domain");
    public static final String ADMIN_DOMAIN = Config.getItem("admin_domain");

    /** 管理员用户 */
    public static final String USER_ADMIN = "admin";

    /** 管理员角色 */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /** 普通用户角色 */
    public static final String ROLE_USER = "ROLE_USER";

    /** password salt */
    public static final String PASSWORD_SALT = "$%#@!&*(#ADY#$()0@@@+-=-@!#!~`']";

    public static final String SEC_KEY = "Xo+81y.0AA61j89],f|yu6";

    // 当前登录用户在attribute中的属性名称
    public static String CURRENT_USER_ATTRIBUTE = "user";
    // 当前登录用户uid在attribute中的属性名称
    public static String CURRENT_XID_ATTRIBUTE = "xid";

    // 七牛回调－app
    public static String QINIU_CALLBACK_FORAPP = "/picture/callback/";
    public static String QINIU_CALLBACK_SECKEY = "qnGjs9x,kDflj+13Us-dKdOk";

}
