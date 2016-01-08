package com.mfq.admin.web.bean;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo {
    private Integer id;

    private String orderNo;

    private BigDecimal price;

    private Long uid;

    private Long pid;

    private Integer payType;

    private BigDecimal periodPay;

    private Integer period;

    private BigDecimal onlinePay;

    private BigDecimal hospitalPay;

    private BigDecimal useBalance;

    private String couponNum;

    private String securityCode;

    private Integer policyStatus;

    private Integer status;

    private Integer refundType;

    private Date createdAt;

    private Date updatedAt;

    private Date serviceStartTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPeriodPay() {
        return periodPay;
    }

    public void setPeriodPay(BigDecimal periodPay) {
        this.periodPay = periodPay;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(BigDecimal onlinePay) {
        this.onlinePay = onlinePay;
    }

    public BigDecimal getHospitalPay() {
        return hospitalPay;
    }

    public void setHospitalPay(BigDecimal hospitalPay) {
        this.hospitalPay = hospitalPay;
    }

    public BigDecimal getUseBalance() {
        return useBalance;
    }

    public void setUseBalance(BigDecimal useBalance) {
        this.useBalance = useBalance;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum == null ? null : couponNum.trim();
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode == null ? null : securityCode.trim();
    }

    public Integer getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(Integer policyStatus) {
        this.policyStatus = policyStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }
}