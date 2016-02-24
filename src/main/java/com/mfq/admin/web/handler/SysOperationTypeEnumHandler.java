package com.mfq.admin.web.handler;

import com.mfq.admin.web.constants.SysOperationType;

/**
 * Created by liuzhiguo1 on 16/2/23.
 */
public class SysOperationTypeEnumHandler extends EnumOrdinalTypeHandler<SysOperationType> {

    public SysOperationTypeEnumHandler(){
        super(SysOperationType.class);
    }

    @Override
    protected Object getEnumValue(SysOperationType parameter) {
        return parameter.getDesc();
    }

    @Override
    protected SysOperationType getEnumFromValue(Object value) {
        return SysOperationType.getDescFromType((int)value);
    }

}
