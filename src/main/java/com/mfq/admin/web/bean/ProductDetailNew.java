package com.mfq.admin.web.bean;

public class ProductDetailNew {
    private Integer id;

    private Integer pid;

    private String description;

    private String preferential;

    private String attention;

    private Integer flag;

    private String ask;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential == null ? null : preferential.trim();
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention == null ? null : attention.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask)
    {
        this.ask = ask == null ? null : ask.trim();
    }

    public ProductDetailNew() {
        super();
    }

    public ProductDetailNew(Integer id, Integer pid, String description, String preferential, String attention, Integer flag, String ask) {
        this.id = null;
        this.pid = pid;
        this.description = description;
        this.preferential = preferential;
        this.attention = attention;
        this.flag = flag;
        this.ask = ask;


    }

    @Override
    public String toString() {
        return "ProductDetailNew{" +
                "id=" + id +
                ", pid=" + pid +
                ", description='" + description + '\'' +
                ", preferential='" + preferential + '\'' +
                ", attention='" + attention + '\'' +
                ", flag=" + flag +
                ", ask='" + ask + '\'' +
                '}';
    }
}