package com.mfq.admin.web.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheContext {
	
	private static RedisCache cache = new RedisCache();
	
	private static final int exp = 7000;
    
	
	//缓存key
    private static final String TICKET_KEY = "wechat_jsapi_tickets";
    
}
