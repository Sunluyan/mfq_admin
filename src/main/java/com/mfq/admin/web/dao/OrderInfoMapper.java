package com.mfq.admin.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.order.OrderInfo;

@MQMDao
public interface OrderInfoMapper {

    public long findCount(@Param("orderNo") String orderNo,
            @Param("uid") long uid, @Param("securityCode") String securityCode,
            @Param("status") int status, @Param("ob") String ob,
            @Param("oe") String oe, @Param("start") long start,
            @Param("pagesize") long pagesize);

    public List<OrderInfo> findByPage(@Param("orderNo") String orderNo,
            @Param("uid") long uid, @Param("securityCode") String securityCode,
            @Param("status") int status, @Param("ob") String ob,
            @Param("oe") String oe, @Param("start") long start,
            @Param("pagesize") long pagesize);
            
    public List<OrderInfo> findByPageByHospital(@Param("orderNo") String orderNo,
            @Param("uid") long uid, @Param("securityCode") String securityCode,
            @Param("status") int status, @Param("ob") String ob,
            @Param("oe") String oe, @Param("start") long start,
            @Param("hospitalId") long hospitalId, @Param("pagesize") long pagesize);

    public long updateOrderStatusSafe(@Param("id") long id,
            @Param("oldStatus") int oldStatus,
            @Param("newStatus") int newStatus);
    
    public OrderInfo findById(@Param("id") long id);
    
    public OrderInfo findByOrderNo(@Param("orderNo") String orderNo);
    
	public long findCountByFinanceOrder(@Param("orderNo") String orderNo,
			@Param("mobile") String mobile, @Param("hospitalName") String hospitalName, @Param("payType") int payType,
			@Param("payApi") String payApi, @Param("status") int status, @Param("ob") String ob, @Param("oe") String oe,
			@Param("start") long start, @Param("pagesize") long pagesize);

	public List<Map<String,Object>> findByFinancePage(@Param("orderNo") String orderNo,
			@Param("mobile") String mobile, @Param("hospitalName") String hospitalName, @Param("payType") int payType,
			@Param("payApi") String payApi, @Param("status")int [] status, @Param("ob") String ob, @Param("oe") String oe,
			@Param("start") long start, @Param("pagesize") long pageSize);
	
	public List<Map<String,Object>> findStatusByUid(@Param("uids")List<String> uids);
}