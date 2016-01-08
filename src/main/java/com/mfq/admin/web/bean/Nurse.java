package com.mfq.admin.web.bean;

public class Nurse {
    private Integer nurseNumber;

    private String name;

    private String phone;

    private String address;

    private String idCard;

    private Integer gender;



    public Integer getNurseNumber() {
        return nurseNumber;
    }

    public void setNurseNumber(Integer nurseNumber) {
        this.nurseNumber = nurseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Nurse(Integer nurseNumber, String name, String phone,
                 Gender gender, String address, String idCard) {
        super();
        this.nurseNumber = nurseNumber;
        this.name = name;
        this.phone = phone;
        if(gender!=null){
            this.gender = gender.getValue();
        }
        this.address = address;
        this.idCard = idCard;
    }
    public Nurse(){
        super();
    }
}