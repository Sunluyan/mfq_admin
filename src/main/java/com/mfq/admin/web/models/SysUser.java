package com.mfq.admin.web.models;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.mfq.admin.web.constants.Status;

/**
 * 后台用户表
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 7769914492983551824L;

    long id; // 唯一
    String username; // 唯一
    String realname;
    String mobile; // 手机号码
    long roleId; // role id
    long hospitalId;  //所属医院 公司内部用户 0
    Status status; // 1有效 2禁用 -1删除
    Date created;
    Date updated;

    public SysUser() {
    }

    public SysUser(String username, String realname, String mobile, long roleId) {
        this.username = username;
        this.realname = realname;
        this.mobile = mobile;
        this.roleId = roleId;
        this.status = Status.NORMAL;
        this.created = new Date();
        this.updated = new Date();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    public String getDisplayName() {
        if (StringUtils.isNotBlank(realname)) {
            return realname;
        }
        return username;
    }

    @Override
    public String toString() {
        return "SysUser [username=" + username + ", realname=" + realname
                + ", roleId= " + roleId + ", status=" + status
                + ", updated=" + updated + "]";
    }
}