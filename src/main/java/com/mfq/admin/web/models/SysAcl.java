package com.mfq.admin.web.models;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 权限url表
 * @author xingyongshan
 *
 */
public class SysAcl {

    long id;
    long pid; // 根目录的为0，非菜单的默认为0
    String name;
    String url; // 通过url映射acl是否是同一个，需要抽取uri main部分进行判断
    
    int type; // type为1时为菜单，else为2。 AdminConstants.ACL_TYPE_MENU, AdminConstants.ACL_TYPE_ELSE
    int index; // 排序
    Date createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}