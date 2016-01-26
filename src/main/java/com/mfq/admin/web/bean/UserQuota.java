package com.mfq.admin.web.bean;

import java.math.BigDecimal;
import java.util.Date;

public class UserQuota {
    private Long uid;

    private String realname;

    private String school;

    private String contact;

    private Integer grade;

    private String classes;

    private Date startschoolAt;

    private Integer gender;

    private String idCard;

    private String idcardFront;

    private String idcardReverse;

    private BigDecimal balance;

    private BigDecimal quotaAll;

    private BigDecimal quotaLeft;

    private Integer schoolLocationId;

    private String schoolLocation;

    private String origin;

    private String location;

    private String homesite;

    private Integer authStatus;

    private BigDecimal present;

    private Integer wishPlastic;

    private String schoolRemark;

    private Date createdAt;

    private Date updatedAt;

    private String studentId;

    private String schoolLevel;

    private Integer scholasticYears;

    private String company;

    private String salary;

    private String faculty;

    private String speciality;

    private Long userType;

    private String socialInsuranceId;

    private String workRemark;

    private String workYears;

    private String position;

    private String department;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }

    public Date getStartschoolAt() {
        return startschoolAt;
    }

    public void setStartschoolAt(Date startschoolAt) {
        this.startschoolAt = startschoolAt;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getIdcardFront() {
        return idcardFront;
    }

    public void setIdcardFront(String idcardFront) {
        this.idcardFront = idcardFront == null ? null : idcardFront.trim();
    }

    public String getIdcardReverse() {
        return idcardReverse;
    }

    public void setIdcardReverse(String idcardReverse) {
        this.idcardReverse = idcardReverse == null ? null : idcardReverse.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Integer getSchoolLocationId() {
        return schoolLocationId;
    }

    public void setSchoolLocationId(Integer schoolLocationId) {
        this.schoolLocationId = schoolLocationId;
    }

    public String getSchoolLocation() {
        return schoolLocation;
    }

    public void setSchoolLocation(String schoolLocation) {
        this.schoolLocation = schoolLocation == null ? null : schoolLocation.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getHomesite() {
        return homesite;
    }

    public void setHomesite(String homesite) {
        this.homesite = homesite == null ? null : homesite.trim();
    }

    public Integer getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Integer authStatus) {
        this.authStatus = authStatus;
    }

    public BigDecimal getPresent() {
        return present;
    }

    public void setPresent(BigDecimal present) {
        this.present = present;
    }

    public Integer getWishPlastic() {
        return wishPlastic;
    }

    public void setWishPlastic(Integer wishPlastic) {
        this.wishPlastic = wishPlastic;
    }

    public String getSchoolRemark() {
        return schoolRemark;
    }

    public void setSchoolRemark(String schoolRemark) {
        this.schoolRemark = schoolRemark == null ? null : schoolRemark.trim();
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel == null ? null : schoolLevel.trim();
    }

    public Integer getScholasticYears() {
        return scholasticYears;
    }

    public void setScholasticYears(Integer scholasticYears) {
        this.scholasticYears = scholasticYears;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty == null ? null : faculty.trim();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality == null ? null : speciality.trim();
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public String getSocialInsuranceId() {
        return socialInsuranceId;
    }

    public void setSocialInsuranceId(String socialInsuranceId) {
        this.socialInsuranceId = socialInsuranceId == null ? null : socialInsuranceId.trim();
    }

    public String getWorkRemark() {
        return workRemark;
    }

    public void setWorkRemark(String workRemark) {
        this.workRemark = workRemark == null ? null : workRemark.trim();
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears == null ? null : workYears.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    @Override
    public String toString() {
        return "UserQuota{" +
                "uid=" + uid +
                ", realname='" + realname + '\'' +
                ", school='" + school + '\'' +
                ", contact='" + contact + '\'' +
                ", grade=" + grade +
                ", classes='" + classes + '\'' +
                ", startschoolAt=" + startschoolAt +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", idcardFront='" + idcardFront + '\'' +
                ", idcardReverse='" + idcardReverse + '\'' +
                ", balance=" + balance +
                ", quotaAll=" + quotaAll +
                ", quotaLeft=" + quotaLeft +
                ", schoolLocationId=" + schoolLocationId +
                ", schoolLocation='" + schoolLocation + '\'' +
                ", origin='" + origin + '\'' +
                ", location='" + location + '\'' +
                ", homesite='" + homesite + '\'' +
                ", authStatus=" + authStatus +
                ", present=" + present +
                ", wishPlastic=" + wishPlastic +
                ", schoolRemark='" + schoolRemark + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", studentId='" + studentId + '\'' +
                ", schoolLevel='" + schoolLevel + '\'' +
                ", scholasticYears=" + scholasticYears +
                ", company='" + company + '\'' +
                ", salary='" + salary + '\'' +
                ", faculty='" + faculty + '\'' +
                ", speciality='" + speciality + '\'' +
                ", userType=" + userType +
                ", socialInsuranceId='" + socialInsuranceId + '\'' +
                ", workRemark='" + workRemark + '\'' +
                ", workYears='" + workYears + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}