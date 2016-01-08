package com.mfq.admin.web.bean;

import java.math.BigDecimal;
import java.util.Date;

public class FinanceBill {
    private Long id;

    private Long uid;

    private String billNo;

    private String orderNo;

    private BigDecimal newBalance;

    private BigDecimal lateFee;

    private Integer curPeriod;

    private Integer allPeriod;

    private Integer status;

    private Date tradeAt;

    private Date chargeAt;

    private Date billAt;

    private Date dueAt;

    private Date payAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Integer getCurPeriod() {
        return curPeriod;
    }

    public void setCurPeriod(Integer curPeriod) {
        this.curPeriod = curPeriod;
    }

    public Integer getAllPeriod() {
        return allPeriod;
    }

    public void setAllPeriod(Integer allPeriod) {
        this.allPeriod = allPeriod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTradeAt() {
        return tradeAt;
    }

    public void setTradeAt(Date tradeAt) {
        this.tradeAt = tradeAt;
    }

    public Date getChargeAt() {
        return chargeAt;
    }

    public void setChargeAt(Date chargeAt) {
        this.chargeAt = chargeAt;
    }

    public Date getBillAt() {
        return billAt;
    }

    public void setBillAt(Date billAt) {
        this.billAt = billAt;
    }

    public Date getDueAt() {
        return dueAt;
    }

    public void setDueAt(Date dueAt) {
        this.dueAt = dueAt;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}