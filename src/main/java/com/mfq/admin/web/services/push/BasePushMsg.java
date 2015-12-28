package com.mfq.admin.web.services.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;

public class BasePushMsg {
	
	private static final Logger logger = LoggerFactory
            .getLogger(BasePushMsg.class);
	
	// 1. get apiKey and secretKey from developer console
	static String apiKey_ios = "1DsXbTCy6bmO8R9lfho601Kt";
	static String secretKey_ios = "8CLkHYUMypeY4NUOjwNuGNmQZbZ4rBMC";
	
	static String apiKey_android = "NxthXWvbyhORnU2lUDLKHc3K";
	static String secretKey_android = "4bDbYXivKzEMOVm5mBE418PoWQsk2ITH";
	
	static BaiduPushClient pushAndroidClient = new BaiduPushClient(new PushKeyPair(apiKey_android, secretKey_android),
			BaiduPushConstants.CHANNEL_REST_URL);
	
	static BaiduPushClient pushIOSClient = new BaiduPushClient(new PushKeyPair(apiKey_ios, secretKey_ios),
			BaiduPushConstants.CHANNEL_REST_URL);
	
	public static void init(){
		// in this request.
		pushAndroidClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.info(event.getMessage());
			}
		});
		
		pushIOSClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				logger.info(event.getMessage());
			}
		});
		
		
	}
}
