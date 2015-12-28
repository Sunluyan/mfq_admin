package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.notification.Notification;

@MQMDao
public interface NotificationMapper {

	public int insertNotification(Notification no);
	
}
