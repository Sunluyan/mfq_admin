package com.mfq.admin.web.handler;


import com.mfq.admin.web.bean.Status;

public class UserStatusEnumHandler extends EnumOrdinalTypeHandler<Status>{

    public UserStatusEnumHandler(){
        super(Status.class);
    }

    @Override
    protected Object getEnumValue(Status parameter) {
        return parameter.getValue();
    }

    @Override
    protected Status getEnumFromValue(Object value) {
        return Status.fromValue((Integer) value);
    }
}