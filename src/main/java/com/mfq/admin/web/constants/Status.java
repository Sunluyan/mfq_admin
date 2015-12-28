package com.mfq.admin.web.constants;

public enum Status {

    NORMAL(0 , "正常"),   // 正常
    FORBIDDEN(1, "禁用"), // 禁用
    DELETED(-1 , "删除"); // 删除

    private final int value;
    private final String name;

    private Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
    
    public String getName(){
    	return name;
    }

    public static Status fromValue(int value) {
        for (Status s : Status.values()) {
            if (value == s.getValue()) {
                return s;
            }
        }
        return null;
    }
}