package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.coupon.Coupon;

@MQMDao
public interface CouponMapper {

    public long insertOne(Coupon coupon);

    public Coupon findByCouponNum(@Param("couponNum") String couponNum);

    public List<Coupon> findByUid(@Param("uid") long uid);

    public List<Coupon> findUserValid(@Param("uid") long uid,
            @Param("status") int status,
            @Param("list") List<Long> list);

    public long updateStatus(@Param("couponNum") String couponNum, @Param("status") int status);

    public Coupon findByUserAndNum(@Param("uid")long uid, @Param("couponNum")String couponNum);

    public long delCoupon(@Param("couponNum")String couponNum);

	public List<Coupon> findCouponsByUidAndStatus(@Param("uid") long uid, @Param("status") int couponStatus);

}