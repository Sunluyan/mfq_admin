package com.mfq.admin.web.handler;

import com.mfq.admin.web.constants.ProductType;

public class ProductTypeEnumHandler extends EnumOrdinalTypeHandler<ProductType> {

    public ProductTypeEnumHandler(){
        super(ProductType.class);
    }
    
    @Override
    protected Object getEnumValue(ProductType parameter) {
        return parameter.getId();
    }

    @Override
    protected ProductType getEnumFromValue(Object value) {
        return ProductType.fromId((Integer) value);
    }
}
