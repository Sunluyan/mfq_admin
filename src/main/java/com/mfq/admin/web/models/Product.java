package com.mfq.admin.web.models;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.mfq.admin.web.constants.ProductType;

public class Product {
    long id;            // 产品ID
    String name;        // 产品名称
    int tid;            // 产品类别
    int hospitalId;     // 医院ID
    BigDecimal price;   // 团购总价格
    BigDecimal marketPrice; //市场价
    BigDecimal onlinePay;  // 定金-在线支付
    BigDecimal hospitalPay; //到院支付
    BigDecimal pPrice;  // 分期价格,只记录一个基准的？
    ProductType type;   //产品类别
    String type2;
    boolean isFq;       //是否分期
    int pNum;           // 分期数，只记录一个最大的？
    Date dateStart;     // 有效期开始时间
    Date dateEnd;       // 有效期结束时间
    long totalNum;            //产品总量
    long remainNum;      //剩余数量
    long viewNum;       // 浏览量
    long saleNum;       // 销量
    int cityId;         //城市id
    int flag;           // 推荐
    String img;         // 产品图片
    boolean online;      // 产品状态,default true
    Date createdAt;     // 创建日期
    Date updatedAt;     // 最后更新时间
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getTid() {
        return tid;
    }
    
    public boolean isFq() {
		return isFq;
	}
	public void setFq(boolean isFq) {
		this.isFq = isFq;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public void setTid(int tid) {
        this.tid = tid;
    }
    public int getHospitalId() {
        return hospitalId;
    }
    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
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
    public BigDecimal getpPrice() {
        return pPrice;
    }
    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }
    public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public int getpNum() {
        return pNum;
    }
    public void setpNum(int pNum) {
        this.pNum = pNum;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public long getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}
	public long getRemainNum() {
		return remainNum;
	}
	public void setRemainNum(long remainNum) {
		this.remainNum = remainNum;
	}
	public long getViewNum() {
        return viewNum;
    }
    public void setViewNum(long viewNum) {
        this.viewNum = viewNum;
    }
    public long getSaleNum() {
        return saleNum;
    }
    public void setSaleNum(long saleNum) {
        this.saleNum = saleNum;
    }
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public boolean isOnline() {
        return online;
    }
    public void setOnline(boolean online) {
        this.online = online;
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
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}