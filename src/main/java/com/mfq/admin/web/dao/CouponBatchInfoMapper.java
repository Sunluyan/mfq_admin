package com.mfq.admin.web.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.coupon.CouponBatchInfo;

import java.util.List;

@MQMDao
public interface CouponBatchInfoMapper {
	
    public long insertOne(CouponBatchInfo couponBatchInfo);
    
    public CouponBatchInfo findById(@Param("id") long id);

    public CouponBatchInfo findByBatch(@Param("batch") String batch);

    public List<CouponBatchInfo> findByBatchs(@Param("batchs") String batchs);

}