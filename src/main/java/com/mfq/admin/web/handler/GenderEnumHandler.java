package com.mfq.admin.web.handler;

import com.mfq.admin.web.models.user.Gender;

public class GenderEnumHandler extends EnumOrdinalTypeHandler<Gender> {

    public GenderEnumHandler(){
        super(Gender.class);
    }
    
    @Override
    protected Object getEnumValue(Gender parameter) {
        return parameter.getValue();
    }

    @Override
    protected Gender getEnumFromValue(Object value) {
        return Gender.fromValue((Integer) value);
    }
}
