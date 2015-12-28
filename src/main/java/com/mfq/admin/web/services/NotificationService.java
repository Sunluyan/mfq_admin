package com.mfq.admin.web.services;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.mfq.admin.web.dao.NotificationMapper;
import com.mfq.admin.web.models.notification.Notification;
import com.mfq.admin.web.models.user.User;
import com.mfq.admin.web.models.user.UserExtend;
import com.mfq.admin.web.services.push.PushMsg;
import com.mfq.admin.web.utils.JSONUtil;

@Service
public class NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Resource
	UserService userService;
	@Resource
	UserExtendService userExtendService;
	@Resource
	NotificationMapper mapper;
	@Resource
	PushMsg pushMsg;
	
	public String queryMsgs(long uid, Date time) {
		String ret = "";
		return ret;
	}


	public String saveMsg(String mobile, String title, int type, String desc, String url) throws PushClientException, PushServerException {
		Notification no = new Notification();
		no.setImg(url);
		no.setMsg(desc);
		no.setCreated(new Date());
		no.setType(type);
		no.setTitle(title);
		no.setStatus(0);
		
		long uid = 0;
		if(type == 2){
			User user = userService.queryUserByMobile(mobile);		
			uid = user.getUid();
		}
		no.setUid(uid);	
		int result = mapper.insertNotification(no);
		if(result > 0){
			pushMsg(title, desc, url, type, uid);
			
			return JSONUtil.successResultJson();
		}
		return null;
	}
	
	public String pushMsg(String title, String desc, String url, int type, long uid) throws PushClientException, PushServerException{
		if(type == 1){
			PushMsg.PushToAll(title, desc);
		}else if (type == 2){
			UserExtend extend = userExtendService.getUserExtendByUid(uid);
			if(extend !=null && StringUtils.isNotBlank(extend.getChannelId())){
				logger.info("push to one {},{},{},{},{}", extend.getUid(), title, desc, extend.getChannelId(), extend.getMobileType());
				PushMsg.PushToSingleDevice(title, desc, url, extend.getChannelId(), extend.getMobileType());
			}else{
				logger.error("push to all  error  extend is null {},uid = ",uid);
			}
		}
		
		return "";
	}



}