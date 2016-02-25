package com.mfq.admin.web.bean;

import com.mfq.admin.web.constants.BannerType;

import java.util.Date;

public class HomeBanner {
    private Long id;

    private String name;

    private String img;

    BannerType pType;

    private Long pId;

    private String url;

    private Integer index;

    private Integer type;

    private Integer flag;

    private Date createdAt;



    public HomeBanner(long id, String name, String img, BannerType pType, long pId, String url){
        this.id = id;
        this.img = img;
        this.name = name;
        this.pType = pType;
        this.pId = pId;
        this.url = url;
        this.index = 0;
        this.type = 0;
        this.flag = 0;
        this.createdAt = new Date();
    }

    public HomeBanner() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public BannerType getpType() {
        return pType;
    }

    public void setpType(BannerType pType) {
        this.pType = pType;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}