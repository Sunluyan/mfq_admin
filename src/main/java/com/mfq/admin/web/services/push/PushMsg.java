package com.mfq.admin.web.services.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushResponse;
import com.mfq.admin.web.utils.JSONUtil;

@Service
public class PushMsg extends BasePushMsg {

	private static final Logger logger = LoggerFactory.getLogger(PushMsg.class);

	public static PushResponse AndroidPushMsgToAll(String message) throws PushClientException, PushServerException {
		try {
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(0)
					.addMessage(message) // 添加透传消息
					.addSendTime(System.currentTimeMillis() / 1000 + 100) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushAndroidClient.pushMsgToAll(request);
			// Http
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			return response;

		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error("AndroidPushMsgToAll is error {}", e);
				throw e;
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
				throw e;
			}
		}

	}
	
	public static PushResponse AndroidPushNotificationToAll(String title , String desc, String url) throws PushClientException, PushServerException {
		try {
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("description",desc);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			if(url !=null && !"".equals(url))
				notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); 
			notification.put("custom_content", jsonCustormCont);
			
			// 4. specify request arguments
			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString())
					.addSendTime(System.currentTimeMillis() / 1000 + 100) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDeviceType(3);
			// 5. http request
			PushMsgToAllResponse response = pushAndroidClient.pushMsgToAll(request);
			// Http
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			return response;

		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error("AndroidPushMsgToAll is error {}", e);
				throw e;
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
				throw e;
			}
		}

	}

	public static PushResponse AndroidPushMsgToSingleDevice(String title, String desc, String url, String channelId)
			throws PushClientException, PushServerException {

		try {

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(0).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					addMessage(title).addDeviceType(3);// deviceType
																			// =>
																			// 3:android,
																			// 4:ios
			// 5. http request
			return pushAndroidClient.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error("AndroidPushMsgToSingleDevice is errror {}", e);
				throw e;
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
				throw e;
			}
		}

	}
	
	public static PushResponse AndroidPushNotificationToSingleDevice(String title, String desc, String url, String channelId)
			throws PushClientException, PushServerException {

		try {
			// 4. specify request arguments
			// 创建 Android的通知
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("description", desc);
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style", 4);
			notification.put("open_type", 1);
			if(url != null && !"".equals(url))
				notification.put("url", url);
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("key", "value"); // 自定义内容，key-value
			notification.put("custom_content", jsonCustormCont);

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // message有效时间
					addMessageType(1).// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
					addMessage(notification.toString()).addDeviceType(3);// deviceType
																			// =>
																			// 3:android,
																			// 4:ios
			// 5. http request
			return pushAndroidClient.pushMsgToSingleDevice(request);
			// Http请求结果解析打印
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error("AndroidPushMsgToSingleDevice is errror {}", e);
				throw e;
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
				throw e;
			}
		}

	}

	public static PushResponse IOSPushNotificationToAll(String title) throws PushClientException, PushServerException {

		try {
			// 4. specify request arguments
			// 创建IOS通知
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", title);
			// jsonAPS.put("sound", "ttt"); // 设置通知铃声样式,例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");

			PushMsgToAllRequest request = new PushMsgToAllRequest().addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString()).addSendTime(System.currentTimeMillis() / 1000 + 100) // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(2).addDeviceType(4);
			// 5. http request
			PushMsgToAllResponse response = pushIOSClient.pushMsgToAll(request);
			// Http璇锋眰缁撴灉瑙ｆ瀽鎵撳嵃
			logger.info("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: "
					+ response.getTimerId());
			return response;
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(" IOSPushNotificationToAll error {}", e);
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return null;
	}

	public static PushResponse IOSPushNotificationToSingleDevice(String title, String channelId)
			throws PushClientException, PushServerException {
		try {
			// 4. specify request arguments
			// make IOS Notification
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", title);
			// jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");

			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest().addChannelId(channelId)
					.addMsgExpires(new Integer(3600)). // 设置message的有效时间
					addMessageType(1).// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
					addMessage(notification.toString()).addDeployStatus(2). // IOS,
																			// DeployStatus
																			// =>
																			// 1:
																			// Developer
																			// 2:
																			// Production.
			addDeviceType(4);// deviceType => 3:android, 4:ios
			// 5. http request
			return pushIOSClient.pushMsgToSingleDevice(request);
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				logger.error("IOSPushNotificationToSingleDevice error is {}==={}", BaiduPushConstants.ERROROPTTYPE, e);
			} else {
				logger.error("IOSPushNotificationToSingleDevice error is {}", e);
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				logger.error(String.format("requestId: %d, errorCode: %d, errorMessage: %s", e.getRequestId(),
						e.getErrorCode(), e.getErrorMsg()));
			}
		}
		return null;
	}

	public static void PushToAll(String title, String desc) throws PushClientException, PushServerException {
		try {
			AndroidPushMsgToAll(desc);
			AndroidPushNotificationToAll(title, desc, null);
			IOSPushNotificationToAll(title);
		} catch (Exception e) {
			logger.error("push to all error {}", e);
		}

	}

	public static void PushToSingleDevice(String title, String desc, String url, String channelId, String app) {
		try {
			PushResponse response= null;
			if(app.equals("Android")){
				response = AndroidPushNotificationToSingleDevice(title, desc, url, channelId);
				ResolveResponse(response);
				response = AndroidPushMsgToSingleDevice(title, desc, url, channelId);
			}else if(app.equals("iOS")){
				response = IOSPushNotificationToSingleDevice(title, channelId);
			}else{
				logger.error("device is not found");
			}
			ResolveResponse(response);
		} catch (Exception e) {
			logger.error("push to all error {}", e);
		}
	}

	public static String ResolveResponse(PushResponse response) {
		String ret = JSONUtil.writeToJson(response);
		logger.info("BaiduPush response {}", ret);
		return ret;
	}
	
	public static void main(String[] args) {
		String channelId = "4986035792698312869";
		PushToSingleDevice("test", "desc" , "", channelId, "Android");
	}

}
