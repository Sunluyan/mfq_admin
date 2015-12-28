package com.mfq.admin.web.utils;

import java.util.UUID;

/**
 * Created by xingyongshan on 15/8/9.
 */
public class CommonUtil {

    // private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private CommonUtil() {

    }

    public static String fixSql(String sql) {
        return sql.replaceAll("@#%", "");
    }
    
    public static String createTicket() {
        return Md5.crypt(UUID.randomUUID().toString());
    }
}