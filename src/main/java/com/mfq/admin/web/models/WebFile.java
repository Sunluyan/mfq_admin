package com.mfq.admin.web.models;

import java.util.Date;

/**
 * 上传的图像.
 */
public class WebFile {

    private int id;
    private int format; // 1: image 2: file
    private String uploader;
    private String description;
    private int album;
    private String dimension;
    private int size;
    private String uploadName;
    private String baseUrl;
    private String srcUrl;
    private Date createdAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getBigImageUrl() {
        return baseUrl;
    }

    public String getMediumImageUrl() {
        return baseUrl.replaceFirst("_b\\.", "_m\\.");
    }

    public String getSmallImageUrl() {
        return baseUrl.replaceFirst("_b\\.", "_fs\\.");
    }

    public String getCoverImageUrl() {
        return baseUrl.replaceFirst("_b\\.", "_fc\\.");
    }

    public String getHeadImageUrl() {
        return baseUrl.replaceFirst("_b\\.", "_fb\\.");
    }
}
