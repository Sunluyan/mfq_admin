package com.mfq.admin.web.constants;

/**
 * Created by liuzhiguo1 on 16/2/23.
 */
public enum SysOperationType {
    REMARK(1,"添加用户备注"),
    FEEDBACK_TYPE(2,"修改认证类型"),
    FEEDBACK(3,"修改认证反馈"),
    UPDATE_INTERVIEW_PICTURE(4,"上传面签照片"),
    UPDATE_INTERVIEW(5,"修改面签资料备注"),
    UPDATE_USER_STATUS(6,"修改用户实名状态"),
    UPDATE_ORDER_PRICE(7,"修改订单价格"),
    UPDATE_ORDER_PRICE_REMARK(8,"修改订单价格备注"),
    UPDATE_INTERVIEW_CHECK(9,"面签审核(放款/拒绝)");

    int type;
    String desc;

    SysOperationType(int type,String desc){
        this.type = type;
        this.desc = desc;
    }

    public static SysOperationType fromType(int type){
        for(SysOperationType s : SysOperationType.values()){
            if(type == s.getType()){
                return s;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


}
