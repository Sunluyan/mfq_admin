package com.mfq.admin.web.models;

import java.util.Date;

public class Hospital {

    long id; 
    String name;
    String img; 
    String address;
    Date createdAt; 
    Date updatedAt;
    long cityId;
    String cityName;
    
    public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
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
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
		return "Hospital [id=" + id + ", name=" + name + ", img=" + img
				+ ", address=" + address + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", cityId=" + cityId
				+ ", cityName=" + cityName + "]";
	}
    
    
}
