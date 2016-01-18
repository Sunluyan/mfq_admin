/**
 * 
 */
package com.mfq.admin.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mfq.admin.web.security.UserHolder;

/**
 * 管理后台首页
 */
@Controller
public class IndexController {
    
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = { "", "/" })
    public String index(Model model) {
        try {
            model.addAttribute("showtyes", System.currentTimeMillis() % 2 == 0);
            if (!UserHolder.isLogin()) {
                // 非登录状态，跳转到登录页
                return "redirect:/login/";
            }
        } catch (Exception ex) {
            logger.error("Exception_IN_ManageSystem_Index", ex);
        }
        return "index";
    }

    @RequestMapping(value = {"/index","/index/"})
    public String homeIndex(Model model){

        try {
            

        }catch (Exception e){
            logger.error("首页index is error {}", e);
        }
        return "index/home_index";
    }
}