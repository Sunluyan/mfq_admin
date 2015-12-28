package com.mfq.admin.web.handler;

import com.mfq.admin.web.models.user.Grade;

public class GradeEnumHandler extends EnumOrdinalTypeHandler<Grade> {

    public GradeEnumHandler(){
        super(Grade.class);
    }
    
    @Override
    protected Object getEnumValue(Grade parameter) {
        return parameter.getId();
    }

    @Override
    protected Grade getEnumFromValue(Object value) {
        return Grade.fromId((Integer) value);
    }
}