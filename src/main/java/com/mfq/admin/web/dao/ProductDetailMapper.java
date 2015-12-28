package com.mfq.admin.web.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.ProductDetail;

@MQMDao
public interface ProductDetailMapper {

    public ProductDetail findByPid(@Param("pid") long pid);
    
    public long insertDetail(ProductDetail model);
    
    public void updateOne(ProductDetail model);
}