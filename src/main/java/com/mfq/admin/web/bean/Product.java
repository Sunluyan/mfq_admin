package com.mfq.admin.web.bean;

import com.mfq.admin.web.constants.ProductType;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Long id;

    private String name;

    private Integer tid;

    private Long hospitalId;

    private Integer cityId;

    private Integer flag;

    private String img;

    private Integer orderNo;

    private BigDecimal hospitalPay;

    private ProductType type;

    private String type2;

    private Boolean isFq;

    private BigDecimal onlinePay;

    private BigDecimal price;

    private BigDecimal marketPrice;

    private BigDecimal pPrice;

    private Integer pNum;

    private Date dateStart;

    private Date dateEnd;

    private Long remainNum;

    private Long totalNum;

    private Long viewNum;

    private Long saleNum;

    private Boolean online;

    private Date createdAt;

    private Date updatedAt;

//    public Product(){
//        this.orderNo= 1;
//        this.saleNum = 0l;
//        this.viewNum = 0l;
//        this.createdAt = new Date();
//        this.updatedAt = new Date();
//    }

    public Product(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getHospitalPay() {
        return hospitalPay;
    }

    public void setHospitalPay(BigDecimal hospitalPay) {
        this.hospitalPay = hospitalPay;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    public Boolean getIsFq() {
        return isFq;
    }

    public void setIsFq(Boolean isFq) {
        this.isFq = isFq;
    }

    public BigDecimal getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(BigDecimal onlinePay) {
        this.onlinePay = onlinePay;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getpPrice() {
        return pPrice;
    }

    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
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

    public Long getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Long remainNum) {
        this.remainNum = remainNum;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Long saleNum) {
        this.saleNum = saleNum;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
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
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tid=" + tid +
                ", hospitalId=" + hospitalId +
                ", cityId=" + cityId +
                ", flag=" + flag +
                ", img='" + img + '\'' +
                ", orderNo=" + orderNo +
                ", hospitalPay=" + hospitalPay +
                ", type=" + type +
                ", type2='" + type2 + '\'' +
                ", isFq=" + isFq +
                ", onlinePay=" + onlinePay +
                ", price=" + price +
                ", marketPrice=" + marketPrice +
                ", pPrice=" + pPrice +
                ", pNum=" + pNum +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", remainNum=" + remainNum +
                ", totalNum=" + totalNum +
                ", viewNum=" + viewNum +
                ", saleNum=" + saleNum +
                ", online=" + online +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * 添加产品时
     * @param id
     * @param name
     * @param tid
     * @param hospitalId
     * @param cityId
     * @param flag
     * @param img
     * @param type
     * @param type2
     * @param isFq
     * @param price
     * @param marketPrice
     * @param dateStart
     * @param dateEnd
     * @param totalNum
     * @param online
     * @param createdAt
     * @param updatedAt
     */
    public Product(Long id, String name, Integer tid, Long hospitalId, Integer cityId, Integer flag, String img,  ProductType type, String type2, Boolean isFq, BigDecimal price, BigDecimal marketPrice,  Date dateStart, Date dateEnd,  Long totalNum , Boolean online, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        this.hospitalId = hospitalId;
        this.cityId = cityId;
        this.flag = flag;
        this.img = img;
        this.type = type;
        this.type2 = type2;
        this.isFq = isFq;
        this.price = price;
        this.marketPrice = marketPrice;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.totalNum = totalNum;
        this.online = online;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.hospitalPay = BigDecimal.valueOf(0);
        this.onlinePay = BigDecimal.valueOf(0);
        this.pPrice = BigDecimal.valueOf(0);
        this.pNum = 12;
        this.remainNum = totalNum;
    }
}