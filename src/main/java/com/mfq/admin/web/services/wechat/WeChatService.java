package com.mfq.admin.web.services.wechat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.User;
import com.mfq.admin.web.bean.UserExtend;
import com.mfq.admin.web.bean.WechatMsg;
import com.mfq.admin.web.bean.example.WechatMsgExample;
import com.mfq.admin.web.dao.WeChatMsgMapper;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mfq.admin.web.constants.Constants;
import com.mfq.admin.web.services.UserExtendService;
import com.mfq.admin.web.services.UserService;
import com.mfq.admin.web.utils.HttpUtil;
import com.mfq.admin.web.utils.JSONUtil;
import com.mfq.admin.web.utils.ShareCodeUtil;
import com.mfq.admin.web.utils.SignHelper;
import com.mfq.admin.web.utils.VerifyUtils;

@Service
public class WeChatService {

    @Resource
	WeChatMsgMapper mapper;
    @Resource
    UserExtendService userExtendService;
    @Resource
    UserService userService;
    
    public List<String> queryOpenIds(){
		WechatMsgExample example = new WechatMsgExample();
		List<WechatMsg> list = mapper.selectByExample(example);
		List<String> openIds = new ArrayList<>();
		for (WechatMsg wechatMsg : list) {
			openIds.add(wechatMsg.getOpenId());
		}
		return openIds;
    }
    
    public List<WechatMsg> queryMsgByOpenId(String openId){
		WechatMsgExample example = new WechatMsgExample();
		example.or().andOpenIdEqualTo(openId);
    	return mapper.selectByExample(example);
    }

	public String setInviteCode(String mobile, String openId) {
		if(!VerifyUtils.verifyMobile(mobile)|| openId.length() < 10){
			return "参数错误";
		}
		User user = userService.queryUserByMobile(mobile);
		if(user == null || user.getUid() <= 0){
			return "用户未注册";
		}
		UserExtend extend = userExtendService.getUserExtendByUid(user.getUid());
		String code = genInviteCode(user.getUid());
		if(extend== null || extend.getUid() <0){
			extend = new UserExtend();
			extend.setUid(user.getUid());
			extend.setIsBind(false);
			extend.setInviteCode(code);
			extend.setOpenId(openId);
			extend.setRemark("");
			userExtendService.insertUserExtend(extend);
		}else{
			code = extend.getInviteCode();
		}
		
		return "邀请码已生成，邀请码为"+code;
	}
	
	private String genInviteCode(long id){
		return ShareCodeUtil.toSerialCode(id);
	}

	public String sendInviteCodeToUserWeChat(String mobile, String openId) {
		User user = userService.queryUserByMobile(mobile);
		if(user == null || user.getUid() <= 0){
			return "用户未注册";
		}
		UserExtend extend = userExtendService.getUserExtendByUid(user.getUid());
		if(extend== null || extend.getUid() <0){
			return "邀请码未生成";
		}
		String msg = Constants.WECHAT_MSG_INVITECODE.replace("INVITE_CODE", extend.getInviteCode()).replace("USER", user.getMobile());
		String url = Constants.SERVICE_URL+"/wechat/sendMessage/";
		
        Map<String, Object> params = Maps.newHashMap();
        params.put("user", extend.getOpenId());
        params.put("msg", msg);
        params.put("msgType", "text");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JSONUtil.writeToJson(params);
        String resp = HttpUtil.postJson(url, body, true);
        
        return resp;
	}
}
