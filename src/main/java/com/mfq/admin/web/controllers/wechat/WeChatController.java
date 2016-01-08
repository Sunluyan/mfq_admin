package com.mfq.admin.web.controllers.wechat;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mfq.admin.web.bean.WechatMsg;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mfq.admin.web.controllers.BaseController;
import com.mfq.admin.web.services.wechat.WeChatService;

/**
 * 微信
 * 
 * @author hui
 *
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController extends BaseController {

	@Resource
    WeChatService weChatService;
	
    @RequestMapping("/message/")
    public String messages(HttpServletRequest request, Model model) throws Exception {
    	List<String> lists =weChatService.queryOpenIds();
    	model.addAttribute("users", lists);
        return "/wechat/user";
    }
    
    @RequestMapping("/message/list/")
    public String msglist(HttpServletRequest request, Model model) throws Exception {
    	String openId = request.getParameter("openid");
    	List<WechatMsg> list =weChatService.queryMsgByOpenId(openId);
    	model.addAttribute("msgs",list);
    	model.addAttribute("openid",openId);
    	return "/wechat/message";
    }
    
    @RequestMapping("/gen/invite/")
    public String invite(HttpServletRequest request, Model model) throws Exception {
    	String openId = StringUtils.stripToEmpty(request.getParameter("openid"));
    	String mobile = StringUtils.stripToEmpty(request.getParameter("mobile"));
    	String ret =weChatService.setInviteCode(mobile, openId);
    	model.addAttribute("msg",ret);
    	model.addAttribute("openid",openId);
    	model.addAttribute("mobile",mobile);
    	return "/wechat/success";
    }
    
    @RequestMapping("/send/invite/")
    public String sendInvite(HttpServletRequest request, Model model) throws Exception {
    	String openId = StringUtils.stripToEmpty(request.getParameter("openid"));
    	String mobile = StringUtils.stripToEmpty(request.getParameter("mobile"));
    	String ret =weChatService.sendInviteCodeToUserWeChat(mobile, openId);
    	//JSONObject ret = JSONObject.parseObject(result);
    	model.addAttribute("msg",ret);
    	model.addAttribute("openid",openId);
    	return "/wechat/success";
    }
    
    

}