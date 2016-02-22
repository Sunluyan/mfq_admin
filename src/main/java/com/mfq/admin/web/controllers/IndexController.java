/**
 * 
 */
package com.mfq.admin.web.controllers;

import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.security.UserDetail;
import com.mfq.admin.web.services.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mfq.admin.web.security.UserHolder;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理后台首页
 */
@Controller
public class IndexController extends BaseController{
    
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Resource
    SysUserService sysUserService;

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

    @RequestMapping(value = {"/my","/my/"}, method = {RequestMethod.GET})
    public String myPage(Model model){
        UserDetail userDetail = getLoginUserDetail();
        if(userDetail==null){
            model.addAttribute("msg","用户不存在...");
            return "index/home_index";
        }
        SysUser user = sysUserService.querySysUser(userDetail.getSysUser().getId());
        model.addAttribute("user",user);

        return "my";
    }

    @RequestMapping(value = {"/my","/my/"}, method = {RequestMethod.POST})
    public String setInfo(Model model,
                          HttpServletRequest request,
                          @RequestParam(value = "name", required = true)String name,
                          @RequestParam(value = "cname", required = true) String realname,
                          @RequestParam(value = "phone", required = true) String moible,
                          @RequestParam(value = "opassword", defaultValue = "") String opass,
                          @RequestParam(value = "password", defaultValue = "") String npass,
                          @RequestParam(value = "cpassword", defaultValue = "")String cpass){
        String msg = "";
        if(!npass.equals(cpass)){
            msg = "两次密码输入不一样!!!";
        }else {
            msg = sysUserService.resetUserInfo(request, name, realname,moible,opass,npass,cpass);
        }
        model.addAttribute("msg", msg);

        return "index";
    }

}