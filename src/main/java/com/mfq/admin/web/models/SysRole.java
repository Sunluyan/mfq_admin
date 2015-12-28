package com.mfq.admin.web.models;

/**
 * 角色
 */
public class SysRole {

    long id; // role id
    String rolename;
    String roledesc;

    public SysRole() {
    }

    public SysRole(String rolename, String roledesc) {
        super();
        this.rolename = rolename;
        this.roledesc = roledesc;
    } 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [");
        if (rolename != null) {
            builder.append("rolename=");
            builder.append(rolename);
            builder.append(", ");
        }
        if (roledesc != null) {
            builder.append("roledesc=");
            builder.append(roledesc);
        }
        builder.append("]");
        return builder.toString();
    }

}
