package com.mfq.admin.web.handler;

import com.mfq.admin.web.constants.Status;

public class SysUserStatusEnumHandler extends EnumOrdinalTypeHandler<Status> {

    public SysUserStatusEnumHandler(){
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