package com.mfq.admin.web.handler;

import com.mfq.admin.web.constants.BannerType;

public class BannerTypeEnumHandler extends EnumOrdinalTypeHandler<BannerType> {

    public BannerTypeEnumHandler(){
        super(BannerType.class);
    }
    
    @Override
    protected Object getEnumValue(BannerType parameter) {
        return parameter.getId();
    }

    @Override
    protected BannerType getEnumFromValue(Object value) {
        return BannerType.fromId((Integer) value);
    }
}
