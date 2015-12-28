package com.mfq.admin.web.controllers;

import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mfq.admin.web.dao.SysUserMapper;
import com.mfq.admin.web.models.SysPassport;
import com.mfq.admin.web.models.SysUser;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.services.PassportService;
import com.mfq.admin.web.utils.CookieUtils;
import com.mfq.admin.web.utils.PasswordUtils;
import com.mfq.admin.web.utils.RequestUtils;

/**
 * 登录，注销接口
 * 
 * @author xingyongshan
 *
 */
@Controller
public class LogonController extends BaseController {

    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    PassportService passportService;

    @RequestMapping("/login/")
    public String login(HttpServletRequest request) throws Exception {
        // UserHolder not been filtered
        if (UserHolder.isLogin()) {
            // 登录状态，跳转到首页
            return "redirect:/";
        }
        String _r = URLDecoder.decode(RequestUtils.getString(request, "_r", ""),
                "UTF-8");
        request.setAttribute("_r", _r);
        return "/login";
    }

    @RequestMapping("/xlogin/")
    public String xlogin(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	System.out.println("xlogin");
        String _r = URLDecoder.decode(RequestUtils.getString(request, "_r", ""),
                "UTF-8");
        if (_r.contains("/logout/")) {
            _r = "";// 防止死循环
        }
        
        String queryString = StringUtils.isBlank(_r) ? "" : "?_r=" + _r;
        String username = RequestUtils.getString(request, "user", "");
        System.out.println(username+"controller");
        String password = RequestUtils.getString(request, "password", "");
        if (!username.isEmpty() && !password.isEmpty()) {
        	System.out.println("come to if , username="+username);
            String passwordMd5 = PasswordUtils.encode(password);
            System.out.println(passwordMd5);
            SysUser user = sysUserMapper.querySysUserByName(username);
            System.out.println(user+"logon");
            if (user != null) {
                SysPassport passport = passportService.login(user.getId(),
                        passwordMd5);
                // if login ok
                if (passport.getUid() > 0) {
                    operationLogDao.addLog(username, "login_ok",
                            String.format("[%s] login ok.", username),
                            request.getRemoteAddr());
                }
                CookieUtils.setLoginCookie(request, response, passport, false);
                _r = StringUtils.isBlank(_r) ? "/" : _r;
                return "redirect:" + _r;
            }
        }
        return "redirect:/login/" + queryString;
    }

    @RequestMapping("/logout/")
    public String logout(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.info("User {} logout!", UserHolder.getUserId());
        CookieUtils.deletePassport(request, response);
        return "/logout";
    }
}