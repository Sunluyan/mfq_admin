package com.mfq.admin.web.models.user;

import java.util.Date;

import org.springframework.beans.BeanUtils;

public class User implements java.io.Serializable {

    private static final long serialVersionUID = -5974700168528723202L;
    
    private Long uid;

    private Status status;

    private String nick;

    private String email;

    private String mobile;

    private Gender gender;

    private String icon;

    private String img;

    private String pic;

    private Integer locationId;

    private String location;

    private Integer birthday;

    private String intro;

    private String interest;

    private String homesite;

    private String career;

    private String realname;

    private Date createdAt;

    private Date updatedAt;

    private Integer sign;

    private String origin;
    
    public User() {
    }

    public User(User other) {
        BeanUtils.copyProperties(other, this);
    }

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getBirthday() {
		return birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getHomesite() {
		return homesite;
	}

	public void setHomesite(String homesite) {
		this.homesite = homesite;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", status=" + status + ", nick=" + nick
				+ ", email=" + email + ", mobile=" + mobile + ", gender="
				+ gender + ", icon=" + icon + ", img=" + img + ", pic=" + pic
				+ ", locationId=" + locationId + ", location=" + location
				+ ", birthday=" + birthday + ", intro=" + intro + ", interest="
				+ interest + ", homesite=" + homesite + ", career=" + career
				+ ", realname=" + realname + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", sign=" + sign + ", origin="
				+ origin + "]";
	}

    
    
    
}
