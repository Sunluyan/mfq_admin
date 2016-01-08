package com.mfq.admin.web.controllers;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.admin.web.bean.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.mfq.admin.web.services.UserService;
import com.mfq.admin.web.utils.VerifyUtils;

@Controller
public class CheckerController {

    private static final Logger logger = LoggerFactory
            .getLogger(CheckerController.class);

    @Resource
    UserService userService;

    @RequestMapping(value = { "/mobile/check",
            "/mobile/check/" }, method = RequestMethod.GET)
    public String checkMobileUsage(HttpServletRequest request,
            HttpServletResponse response) {
    	
        return "check/mobile";
    }

    @RequestMapping(value = { "/mobile/check",
            "/mobile/check/" }, method = RequestMethod.POST)
    public String postMobileUsage(HttpServletRequest request,
            HttpServletResponse response) {

        Map<String, String> map = Maps.newHashMap();
        String message = "";
        try {
            String mobiles = (String) request.getParameter("mobiles");
            logger.info("mobile check param:{}", mobiles);
            if (StringUtils.isBlank(mobiles)) {
                message = "参数不能为空！";
            } else {
                mobiles = replaceBlank(mobiles);
                logger.info("After preprocess mobiles:{}", mobiles);
                String[] ss = StringUtils.split(mobiles, ","); // 逗号分割
                if (ss == null) {
                    message = "传入参数异常！";
                }
                if (ss != null) {
                    for (String mobile : ss) {
                        if (!VerifyUtils.verifyMobile(mobile)) {
                            map.put(mobile, "手机号码格式错误");
                        } else {
                            User user = userService.queryUserByMobile(mobile);
                            if (user != null && user.getUid() > 0) {
                                map.put(mobile, "已注册");
                            } else {
                                map.put(mobile, "未使用");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception Check Mobile Usage!", e);
            message = "系统异常";
        }
        if (StringUtils.isBlank(message)) {
            message = "success";
        }
        request.setAttribute("message", message);
        request.setAttribute("map", map);
        return "check/mobile";
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s+|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll(",");
        }
        return dest;
    }
    
    public static void main(String[] args){
        System.out.print(replaceBlank("18612258336\n18511870338"));
    }
}