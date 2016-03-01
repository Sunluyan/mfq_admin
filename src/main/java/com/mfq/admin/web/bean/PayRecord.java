package com.mfq.admin.web.bean;

import java.math.BigDecimal;
import java.util.Date;

public class PayRecord {
    Long id;

    Integer orderType;

    String tradeNo;

    String orderNo;

    BigDecimal amount;

    BigDecimal balance;

    BigDecimal present;

    Long uid;

    String tpp;

    String bankCode;

    Integer cardType;

    String cardNo;

    private Integer status;

    private Date payAt;

    private Date callbackAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPresent() {
        return present;
    }

    public void setPresent(BigDecimal present) {
        this.present = present;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTpp() {
        return tpp;
    }

    public void setTpp(String tpp) {
        this.tpp = tpp == null ? null : tpp.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public Date getCallbackAt() {
        return callbackAt;
    }

    public void setCallbackAt(Date callbackAt) {
        this.callbackAt = callbackAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PayRecord{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", tradeNo='" + tradeNo + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                ", present=" + present +
                ", uid=" + uid +
                ", tpp='" + tpp + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", cardType=" + cardType +
                ", cardNo='" + cardNo + '\'' +
                ", status=" + status +
                ", payAt=" + payAt +
                ", callbackAt=" + callbackAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}