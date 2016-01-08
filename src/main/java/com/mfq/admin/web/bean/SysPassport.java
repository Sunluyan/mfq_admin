package com.mfq.admin.web.bean;

import java.util.Date;

public class SysPassport {
    private Long uid;

    private String ticket;

    private Date createdAt;

    private Date expiredAt;

    private Date activedAt;

    private String password;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
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
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "SysPassport{" +
                "uid=" + uid +
                ", ticket='" + ticket + '\'' +
                ", createdAt=" + createdAt +
                ", expiredAt=" + expiredAt +
                ", activedAt=" + activedAt +
                ", password='" + password + '\'' +
                '}';
    }
}