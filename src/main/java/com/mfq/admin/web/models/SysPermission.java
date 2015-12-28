package com.mfq.admin.web.models;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SysPermission {

    long acl; // 指aclID
    long role; // 角色－也是ID,＝0所有人都有权限,>0表示对某个角色赋予权限
    
    public long getAcl() {
        return acl;
    }
    public void setAcl(long acl) {
        this.acl = acl;
    }
    public long getRole() {
        return role;
    }
    public void setRole(long role) {
        this.role = role;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}