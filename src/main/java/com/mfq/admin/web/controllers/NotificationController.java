package com.mfq.admin.web.controllers;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mfq.admin.web.services.NotificationService;
import com.mfq.admin.web.services.QiniuManipulater;

@Controller
@RequestMapping("/noti")
public class NotificationController {

	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Resource
	NotificationService notificationService;

	@RequestMapping(value = { "/send/", "/send" }, method = RequestMethod.GET)
	public String sendMsg(HttpServletRequest request, HttpServletResponse response) {
		return "send/send";
	}

	@RequestMapping(value = { "/send/", "/send" }, method = RequestMethod.POST)
	public String send(@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "type", defaultValue = "") int type,
			@RequestParam(value = "url", defaultValue = "") String url,
			@RequestParam(value = "desc", defaultValue = "") String desc,
			@RequestParam(value = "img_file") MultipartFile imgFile,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String img = null;
			if (!imgFile.isEmpty()) {
				File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
				imgFile.transferTo(tmpFile);
				img = QiniuManipulater.qiniuUploadNotificationImg(tmpFile);
			} else { // 判断是否需要更新img
				img = "";
			}
			notificationService.saveMsg(mobile, title, type, desc, img);
			model.addAttribute("msg", "发送成功。。");
		} catch (Exception e) {
			logger.error("send notification is error", e);
			model.addAttribute("msg", "发送失败。。");
		}
		
		logger.info("user login meifenqi fronted system.....");
		return "send/send";
	}

}