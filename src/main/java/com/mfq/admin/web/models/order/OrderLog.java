package com.mfq.admin.web.models.order;

import java.util.Date;

/**
 * 
* 订单日志实体类
*
 */
public class OrderLog {

	/**
	 * ID
	 */
	private int id;
	/**
	 * 操作类型
	 */
	private int type;
	/**
	 * 操作记录ID
	 */
	private int refId;
	/**
	 * 用户UID
	 */
	private int uid;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 操作
	 */
	private String op;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 创建时间
	 */
	private Date createdAt;
	/**
	 * 更新时间
	 */
	private Date updatedAt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public int getRefId() {
		return refId;
	}
	
	public void setRefId(int refId) {
		this.refId = refId;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getOp() {
		return op;
	}
	
	public void setOp(String op) {
		this.op = op;
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
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
	
}
