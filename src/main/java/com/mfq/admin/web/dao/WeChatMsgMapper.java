package com.mfq.admin.web.dao;

import com.mfq.admin.web.models.wechat.WeChatMsg;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;

@MQMDao
public interface WeChatMsgMapper {

    public List<WeChatMsg> queryWeChatMsgByOpenId(@Param("openId") String openId);
    
    public List<String> queryOpenIds();
}
