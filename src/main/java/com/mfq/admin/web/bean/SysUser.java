package com.mfq.admin.web.bean;

import com.mfq.admin.web.constants.Status;

import java.util.Date;

public class SysUser {
    private Long id;

    private String username;

    private String realname;

    private String mobile;

    private Long roleId;

    private Long hospitalId;

    private Status status;

    private Date created;

    private Date updated;

    public SysUser(String username, String realname, String mobile, long roleId) {
        this.username = username;
        this.realname = realname;
        this.mobile = mobile;
        this.status = Status.NORMAL;
        this.roleId = roleId;
        this.hospitalId = 0l;
        this.created = new Date();
        this.updated = new Date();

    }

    public SysUser() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}