package com.mfq.admin.web.constants;

public enum AclTypeEnum {

    ALL(0, "所有"),
    MENU(1, "菜单"),
    OTHER(2, "其它");

    int id; 
    String desc;
    
    AclTypeEnum(int id, String desc){
        this.id = id;
        this.desc = desc;
    }
    
    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public static AclTypeEnum fromId(int id){
        for(AclTypeEnum acl : AclTypeEnum.values()){
            if(acl.getId() == id){
                return acl;
            }
        }
        return null;
    }
}