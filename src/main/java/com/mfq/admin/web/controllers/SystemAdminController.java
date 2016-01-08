/**
 * 
 */
package com.mfq.admin.web.controllers;

import java.util.List;

import com.mfq.admin.web.bean.SysRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;

/**
 * 权限控制功能
 */
@Controller
@RequestMapping("/systemadmin")
public class SystemAdminController extends BaseController {

    @RequestMapping("roles/")
    public String roles(Model model) {
        List<SysRole> roles = Lists.newArrayList(); // roleDao.queryAll();
        model.addAttribute("roles", roles);
        return "/systemadmin/roles";
    }
}