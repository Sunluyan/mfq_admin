package com.mfq.admin.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mfq.admin.web.services.SendMessageService;

@Controller
public class SendMessageController {

    private static final Logger logger = LoggerFactory
            .getLogger(SendMessageController.class);
    
    @Resource
    SendMessageService service;
    
    @RequestMapping(value = "/sms/send/", method = {RequestMethod.GET})
    public String send(
            Model model) {
        return "sms/form";
    }
    
    @RequestMapping(value = "/sms/send/all/", method = {RequestMethod.POST})
    public String sendAll(HttpServletRequest request, Model model,
//            @RequestParam(value = "over", required = true) String over,
            @RequestParam(value = "content", required = true) String content) {
    	logger.info("========"+content);
    	service.sendMessageToAll(content);
        return "sms/form";
    }
 

}
