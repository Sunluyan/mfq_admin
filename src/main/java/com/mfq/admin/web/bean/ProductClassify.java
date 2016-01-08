package com.mfq.admin.web.bean;

public class ProductClassify {
    private Integer id;

    private String name;

    private String flag;

    private String desp;

    private Integer rootId;

    private String icon;

    private String hgImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp == null ? null : desp.trim();
    }

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getHgImage() {
        return hgImage;
    }

    public void setHgImage(String hgImage) {
        this.hgImage = hgImage == null ? null : hgImage.trim();
    }
}