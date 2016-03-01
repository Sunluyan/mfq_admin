package com.mfq.admin.web.bean;

import java.util.Date;

public class ProFqRecord {
    private Integer id;

    private Integer pid;

    private Integer period;

    private Float periodPay;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Float getPeriodPay() {
        return periodPay;
    }

    public void setPeriodPay(Float periodPay) {
        this.periodPay = periodPay;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}