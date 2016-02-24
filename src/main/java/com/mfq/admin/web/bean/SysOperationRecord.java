package com.mfq.admin.web.bean;

import com.mfq.admin.web.constants.SysOperationType;

import java.util.Date;

public class SysOperationRecord {
    private Long id;

    private Integer sysId;

    private Long userId;

    private Date time;

    private SysOperationType type;

    private String content;

    private String username;

    private String sysUsername;

    private String timeSDF;

    public String getTimeSDF() {
        return timeSDF;
    }

    public void setTimeSDF(String timeSDF) {
        this.timeSDF = timeSDF;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public SysOperationType getType() {
        return type;
    }

    public void setType(SysOperationType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getSysUsername() {
        return sysUsername;
    }

    public void setSysUsername(String sysUsername) {
        this.sysUsername = sysUsername == null ? null : sysUsername.trim();
    }

    @Override
    public String toString() {
        return "SysOperationRecord{" +
                "id=" + id +
                ", sysId=" + sysId +
                ", userId=" + userId +
                ", time=" + time +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", sysUsername='" + sysUsername + '\'' +
                '}';
    }
}