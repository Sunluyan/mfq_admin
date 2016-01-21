package com.mfq.admin.web.models.view;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.coupon.Coupon;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hui on 2016/1/4.
 */
public class FinanceOrder {

	private long id;  //payrecord id
	
	private String orderNo; //订单号

	private String mobile;  //用户手机号

	private BigDecimal orderMoney;  //订单额度

	private String realname;  //
	
    private Integer orderType;  //订单类型

    private String tradeNo;  //支付单号

    private BigDecimal useAmount; //支付额度

    private BigDecimal useBalance; //使用余额

    private BigDecimal usePresent;  //使用赠送额度
    
    private BigDecimal useCard;  //优惠券抵用额度

    private String tpp; //支付api

	private long uid;
    
    private Long pid;  //产品id
    
    private String pName; //产品名称

    private String bankCode;  //银行code

    private Integer cardType;  //优惠劵类型

    private String cardNo;  //优惠券no

    private Integer payStatus;  //支付状态

    private Date callbackAt;  //支付回调时间
    
    
    //订单
    
    private Date createdAt;  //订单创建时间

    private Date serviceStartTime;  //服务时间
    
    private long hid; //医院id
    
    private String hospitalName; //医院名称



	public FinanceOrder(){

	}

	public FinanceOrder(User user, UserQuota quota, OrderInfo order, Hospital hospital, Product product,PayRecord payRecord, Coupon coupon){
		this.id = payRecord.getId();
		this.orderNo = payRecord.getOrderNo();
		this.orderType = 0;   //// TODO: 16/1/16
		this.tradeNo = payRecord.getTradeNo();
		this.useAmount = payRecord.getAmount();
		this.useBalance = payRecord.getBalance();
		this.usePresent = payRecord.getPresent();
		this.useCard = coupon.getMoney();
		this.tpp = payRecord.getTpp();
		this.pid = order.getPid();
		this.pName = product.getName();
		this.bankCode = payRecord.getBankCode();
		this.cardType = 1;   //// TODO: 16/1/16
		this.cardNo = coupon.getCouponNum();
		this.payStatus = payRecord.getStatus();
		this.callbackAt = payRecord.getCallbackAt();

		this.orderMoney = payRecord.getAmount();
		this.createdAt = payRecord.getUpdatedAt();
		this.serviceStartTime = null;

		if(payRecord.getOrderNo().startsWith("mn")){
			this.createdAt = order.getCreatedAt();
			this.serviceStartTime = order.getServiceStartTime();
			this.orderMoney = order.getPrice();
		}



		this.mobile = user.getMobile();
		this.realname = quota.getRealname();
		this.uid = user.getUid();


		this.hid = hospital.getId();
		this.hospitalName = hospital.getName();
	

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
		this.tradeNo = tradeNo;
	}

	public BigDecimal getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}

	public BigDecimal getUseBalance() {
		return useBalance;
	}

	public void setUseBalance(BigDecimal useBalance) {
		this.useBalance = useBalance;
	}

	public BigDecimal getUsePresent() {
		return usePresent;
	}

	public void setUsePresent(BigDecimal usePresent) {
		this.usePresent = usePresent;
	}

	public BigDecimal getUseCard() {
		return useCard;
	}

	public void setUseCard(BigDecimal useCard) {
		this.useCard = useCard;
	}

	public String getTpp() {
		return tpp;
	}

	public void setTpp(String tpp) {
		this.tpp = tpp;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
		this.cardNo = cardNo;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getCallbackAt() {
		return callbackAt;
	}

	public void setCallbackAt(Date callbackAt) {
		this.callbackAt = callbackAt;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getpName() {
		return pName;
	}



	public void setpName(String pName) {
		this.pName = pName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getServiceStartTime() {
		return serviceStartTime;
	}

	public void setServiceStartTime(Date serviceStartTime) {
		this.serviceStartTime = serviceStartTime;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
}
