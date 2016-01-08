package com.mfq.admin.web.bean;

public class ProductDetail {
    private Long pid;

    private String consumeStep;

    private String reserve;

    private String specialNote;

    private String cureMeans;

    private String cureDur;

    private String cureHospital;

    private String recoverDur;

    private String cureNum;

    private String anesMethod;

    private String doctorLevel;

    private String cureCycle;

    private String body;

    private String merit;

    private String cureMethod;

    private String tabooCrowd;

    private String warning;

    private String crowd;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getConsumeStep() {
        return consumeStep;
    }

    public void setConsumeStep(String consumeStep) {
        this.consumeStep = consumeStep == null ? null : consumeStep.trim();
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote == null ? null : specialNote.trim();
    }

    public String getCureMeans() {
        return cureMeans;
    }

    public void setCureMeans(String cureMeans) {
        this.cureMeans = cureMeans == null ? null : cureMeans.trim();
    }

    public String getCureDur() {
        return cureDur;
    }

    public void setCureDur(String cureDur) {
        this.cureDur = cureDur == null ? null : cureDur.trim();
    }

    public String getCureHospital() {
        return cureHospital;
    }

    public void setCureHospital(String cureHospital) {
        this.cureHospital = cureHospital == null ? null : cureHospital.trim();
    }

    public String getRecoverDur() {
        return recoverDur;
    }

    public void setRecoverDur(String recoverDur) {
        this.recoverDur = recoverDur == null ? null : recoverDur.trim();
    }

    public String getCureNum() {
        return cureNum;
    }

    public void setCureNum(String cureNum) {
        this.cureNum = cureNum == null ? null : cureNum.trim();
    }

    public String getAnesMethod() {
        return anesMethod;
    }

    public void setAnesMethod(String anesMethod) {
        this.anesMethod = anesMethod == null ? null : anesMethod.trim();
    }

    public String getDoctorLevel() {
        return doctorLevel;
    }

    public void setDoctorLevel(String doctorLevel) {
        this.doctorLevel = doctorLevel == null ? null : doctorLevel.trim();
    }

    public String getCureCycle() {
        return cureCycle;
    }

    public void setCureCycle(String cureCycle) {
        this.cureCycle = cureCycle == null ? null : cureCycle.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getMerit() {
        return merit;
    }

    public void setMerit(String merit) {
        this.merit = merit == null ? null : merit.trim();
    }

    public String getCureMethod() {
        return cureMethod;
    }

    public void setCureMethod(String cureMethod) {
        this.cureMethod = cureMethod == null ? null : cureMethod.trim();
    }

    public String getTabooCrowd() {
        return tabooCrowd;
    }

    public void setTabooCrowd(String tabooCrowd) {
        this.tabooCrowd = tabooCrowd == null ? null : tabooCrowd.trim();
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning == null ? null : warning.trim();
    }

    public String getCrowd() {
        return crowd;
    }

    public void setCrowd(String crowd) {
        this.crowd = crowd == null ? null : crowd.trim();
    }
}