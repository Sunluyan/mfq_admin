package com.mfq.admin.web.constants;

public enum PFlag {

    DEFAULT(0, "默认"),
    INDEX_SUG(1, "首页推荐");

    int value;
    String desc;
    
    PFlag(int value, String desc){
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
    
    public static PFlag fromValue(int value){
        for(PFlag pf : PFlag.values()){
            if(pf.getValue() == value){
                return pf;
            }
        }
        return null;
    }
}
