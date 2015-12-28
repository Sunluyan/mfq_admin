package com.mfq.admin.web.models;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SysPassport {

    long uid; // required
    String ticket; // required
    Date createdAt; // required
    Date expiredAt; // required
    Date activedAt; // required
    String password; // required

    public SysPassport(){

    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Date getActivedAt() {
        return activedAt;
    }

    public void setActivedAt(Date activedAt) {
        this.activedAt = activedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}