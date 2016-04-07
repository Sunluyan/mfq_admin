package com.mfq.admin.web.controllers;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mfq.admin.web.bean.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.mfq.admin.web.constants.AclTypeEnum;
import com.mfq.admin.web.constants.Status;
import com.mfq.admin.web.services.HospitalService;
import com.mfq.admin.web.services.PassportService;
import com.mfq.admin.web.services.SysAclService;
import com.mfq.admin.web.services.SysPermissionService;
import com.mfq.admin.web.services.SysRoleService;
import com.mfq.admin.web.services.SysUserService;

@Controller
public class SysUserController {
    
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Resource
    SysUserService sysUserService;
    @Resource
    PassportService passportService;
    @Resource
    SysRoleService sysRoleService;
    @Resource
    SysAclService sysAclService;
    @Resource
    SysPermissionService sysPermissionService;
    @Resource
    HospitalService hospitalService;

    public SysUserController() {
    }

    @RequestMapping(value = "/sysuser/delete/", method = RequestMethod.GET)
    public String userDelete(HttpServletRequest request, Model model,
                             @RequestParam(value = "id",required = true)Integer id) {

        sysUserService.updateStatus(id.longValue(),Status.DELETED);
        return "redirect:/sysuser/list/"; // 添加成功，跳转到列表页
    }

    @RequestMapping(value = "/sysuser/list/", method = { RequestMethod.GET })
    public String userList(HttpServletRequest request, Model model)
            throws Exception {
        String pagestr = request.getParameter("page");
        Long page = 1L;
        if (StringUtils.isNotBlank(pagestr)) {
            page = Long.parseLong(pagestr);
        }
        List<SysUser> users = sysUserService.querySysUserByPage(page);
        List<SysRole> roles = sysRoleService.queryAll();
        model.addAttribute("roles", roles);
        model.addAttribute("users", users);
        return "/sysuser/user_list";
    }

    @RequestMapping(value = "/sysuser/add/", method = RequestMethod.GET)
    public String userAdd(HttpServletRequest request, Model model) {

        List<SysRole> roles = sysRoleService.queryAll();
        List<Status> statuses = Lists.newArrayList();
        List<Hospital> hospitals = hospitalService.queryAll();
        for(Status s : Status.values()){
            statuses.add(s);
        }
        model.addAttribute("roles", roles);
        model.addAttribute("statuses", statuses);
        model.addAttribute("hospitals", hospitals);
        return "/sysuser/user_add";
    }

    @RequestMapping(value = "/sysuser/add/", method = RequestMethod.POST)
    public String submitUserAdd(HttpServletRequest request, Model model,
                                @RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "realname", required = true) String realname,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "hospital", required = true) String hospital,
                                @RequestParam(value = "mobile", required = true) String mobile,
                                @RequestParam(value = "role", required = true) String role,
                                @RequestParam(value = "status", required = true) int status) {

        String msg = "";
        try{
            SysUser user = sysUserService.queryUserByName(username);
            if(user != null ){
                msg = "用户已存在！";
            }else{
                Long roleId = Long.parseLong(role);
                Long hospitalId = Long.parseLong(hospital);
                user = new SysUser(username, realname, mobile, roleId);
                user.setStatus(Status.fromValue(status));
                user.setHospitalId(0l);

                //医院用户 roleid 为 6 7
                if(roleId == 6 || roleId == 7){
                    user.setHospitalId(hospitalId);
                }
                long uid=sysUserService.createUser(user, password);
                if(uid==0){
                    model.addAttribute("error", "创建时间失败!!");
                    return "redirect:/sysuser/add/";       // 调用user_add的模版
                }

            }
        }catch(Exception e){
            logger.error("exception_in_adduser", e);
        }
        if(StringUtils.isNotBlank(msg)){
            model.addAttribute("error", msg);
            return "redirect:/sysuser/add/";       // 调用user_add的模版
        }else{
            return "redirect:/sysuser/list/"; // 添加成功，跳转到列表页
        }
    }

    @RequestMapping(value = "/sysuser/edit/", method = RequestMethod.GET)
    public String userEditPage(HttpServletRequest request, Model model,
                               @RequestParam(value = "id",required = true)Integer id) {

        List<SysRole> roles = sysRoleService.queryAll();
        List<Status> statuses = Lists.newArrayList();
        List<Hospital> hospitals = hospitalService.queryAll();
        for(Status s : Status.values()){
            statuses.add(s);
        }
        model.addAttribute("roles", roles);
        model.addAttribute("statuses", statuses);
        model.addAttribute("hospitals", hospitals);

        SysUser user = sysUserService.querySysUser(id);
        model.addAttribute("user",user);

        return "/sysuser/user_edit";
    }

    @RequestMapping(value = "/sysuser/edit/", method = RequestMethod.POST)
    public String userEdit(HttpServletRequest request, Model model,
                                @RequestParam(value = "id", required = true) Integer id,
                                @RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "realname", required = true) String realname,
                                @RequestParam(value = "password", required = true) String password,
                                @RequestParam(value = "hospital", required = true) String hospital,
                                @RequestParam(value = "mobile", required = true) String mobile,
                                @RequestParam(value = "role", required = true) String role,
                                @RequestParam(value = "status", required = true) int status) {

        String msg = "";
        try{
            SysUser user = sysUserService.querySysUser(id.longValue());
            if(user == null ){
                msg = "用户不存在！";
            }else{
                Long roleId = Long.parseLong(role);
                Long hospitalId = Long.parseLong(hospital);
                user = new SysUser(username, realname, mobile, roleId);
                user.setId(id.longValue());
                user.setStatus(Status.fromValue(status));
                user.setHospitalId(0l);

                //医院用户 roleid 为 6 7
                if(roleId == 6 || roleId == 7){
                    user.setHospitalId(hospitalId);
                }
                int count = sysUserService.updateSysuser(user);
                if(count==0){
                    model.addAttribute("error", "创建时间失败!!");
                    return "redirect:/sysuser/edit/";       // 调用user_add的模版
                }
            }
        }catch(Exception e){
            logger.error("exception_in_adduser", e);
        }
        if(StringUtils.isNotBlank(msg)){
            model.addAttribute("error", msg);
            return "redirect:/sysuser/add/";       // 调用user_add的模版
        }else{
            return "redirect:/sysuser/list/"; // 添加成功，跳转到列表页
        }
    }

    @RequestMapping(value = "/sysuser/role/", method = { RequestMethod.GET })
    public String userRole(HttpServletRequest request, Model model)
            throws Exception {
        List<SysRole> roles = sysRoleService.queryAll();
        model.addAttribute("roles", roles);
        return "/sysuser/role_list";
    }

    @RequestMapping(value = "/sysuser/permission/", method = {
            RequestMethod.GET })
    public String userPermission(HttpServletRequest request, Model model)
            throws Exception {
        String pagestr = request.getParameter("page");
        String typestr = request.getParameter("type");

        Long page = 1L;
        if (StringUtils.isNotBlank(pagestr)) {
            page = Long.parseLong(pagestr);
        }
        AclTypeEnum type = AclTypeEnum.ALL;
        if (StringUtils.isNotBlank(typestr)) {
            type = AclTypeEnum.fromId(Integer.parseInt(typestr));
        }
        List<SysAcl> acls = sysAclService.findByType(type, page);
        long size = sysAclService.findCount(type, page);

        Map<Long, String> permissions = sysPermissionService
                .permissionNameFromAcl(acls);
        model.addAttribute("permissions", permissions);
        model.addAttribute("acls", acls);
        model.addAttribute("type", type.getId());
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "/sysuser/permission_list";
    }

    @RequestMapping(value = "/sysuser/permission_add/", method = {
            RequestMethod.GET })
    public String userPermissionAdd(HttpServletRequest request, Model model)
            throws Exception {

        Long id = 0L;
        sysPermissionService.buildEditModel(id, model);

        return "/sysuser/permission_edit";
    }

    @RequestMapping(value = "/sysuser/permission_edit/", method = {
            RequestMethod.GET })
    public String userPermissionEdit(HttpServletRequest request, Model model,
            @RequestParam(defaultValue = "0", required = false) Long id)
                    throws Exception {

        sysPermissionService.buildEditModel(id, model);

        return "/sysuser/permission_edit";
    }
    
    @RequestMapping(value = "/sysuser/permission_edit/", method = {
            RequestMethod.POST })
    public String submitUserPermissionEdit(HttpServletRequest request, Model model)
                    throws Exception {
        Long id = Long.parseLong(request.getParameter("id"));
        String url = request.getParameter("url");
        String name = request.getParameter("name");
        Integer type = Integer.parseInt(request.getParameter("type"));
        Long pid = Long.parseLong(request.getParameter("pid"));
        Integer index = Integer.parseInt(request.getParameter("index"));
        String[] ps = request.getParameterValues("permissionbox");
        
        sysPermissionService.submitEditModel(id, url, name, type, pid, index, ps);

        return "redirect:/sysuser/permission/";
    }
    
      
}