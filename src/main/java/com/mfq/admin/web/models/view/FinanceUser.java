package com.mfq.admin.web.models.view;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hui on 2016/1/4.
 */
public class FinanceUser {

    private long uid;
    private String name;
    private String mobile;
    
    private BigDecimal blance; //用户剩余余额
    private BigDecimal orderMoney; //订单总消费
    private BigDecimal recharge;  //充值额度
    private BigDecimal quotaAll;  //用户总额度
    private BigDecimal  quotaLeft;  //用户剩余额度
    
    private int cz_count;  //充值个数  总
    private int mn_count;  //订单个数
    private int fl_count;  //还款个数

    private int cz_count_at;  //此时间段充值个数
    private int mn_count_at;  //此时间段订单个数
    private int fl_count_at;  //此时间段还款个数
    
    private List<FinanceOrder> orders;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public BigDecimal getBlance() {
		return blance;
	}

	public void setBlance(BigDecimal blance) {
		this.blance = blance;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public BigDecimal getRecharge() {
		return recharge;
	}

	public void setRecharge(BigDecimal recharge) {
		this.recharge = recharge;
	}

	public BigDecimal getQuotaAll() {
		return quotaAll;
	}

	public void setQuotaAll(BigDecimal quotaAll) {
		this.quotaAll = quotaAll;
	}

	public BigDecimal getQuotaLeft() {
		return quotaLeft;
	}

	public void setQuotaLeft(BigDecimal quotaLeft) {
		this.quotaLeft = quotaLeft;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getCz_count() {
        return cz_count;
    }

    public void setCz_count(int cz_count) {
        this.cz_count = cz_count;
    }
    
    public List<FinanceOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<FinanceOrder> orders) {
		this.orders = orders;
	}

	public int getMn_count() {
        return mn_count;
    }

    public void setMn_count(int mn_count) {
        this.mn_count = mn_count;
    }

    public int getFl_count() {
        return fl_count;
    }

    public void setFl_count(int fl_count) {
        this.fl_count = fl_count;
    }

    public int getCz_count_at() {
        return cz_count_at;
    }

    public void setCz_count_at(int cz_count_at) {
        this.cz_count_at = cz_count_at;
    }

    public int getMn_count_at() {
        return mn_count_at;
    }

    public void setMn_count_at(int mn_count_at) {
        this.mn_count_at = mn_count_at;
    }

    public int getFl_count_at() {
        return fl_count_at;
    }

    public void setFl_count_at(int fl_count_at) {
        this.fl_count_at = fl_count_at;
    }
}
