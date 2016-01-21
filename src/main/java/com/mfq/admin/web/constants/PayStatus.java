package com.mfq.admin.web.constants;

public enum PayStatus {

    GO_PAY(1,"未支付"),
    PAID(2,"支付完成"),
    CANCEL(3,"取消支付");

    int value;
    String desc;

    PayStatus(int value, String desc) {

        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static PayStatus fromValue(Integer value) {
        if (value == null) {
            return null;
        }

        for (PayStatus s : PayStatus.values())
            if (s.getValue() == value) return s;
        return null;
    }
}