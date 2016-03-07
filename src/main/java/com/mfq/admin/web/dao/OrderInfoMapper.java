package com.mfq.admin.web.dao;

import com.google.common.collect.Ordering;
import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.OrderInfo;
import com.mfq.admin.web.bean.example.OrderInfoExample;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface OrderInfoMapper {
    int countByExample(OrderInfoExample example);

    int deleteByExample(OrderInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    List<OrderInfo> selectByExample(OrderInfoExample example);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByExample(@Param("record") OrderInfo record, @Param("example") OrderInfoExample example);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);




    public long findCount(@Param("orderNo") String orderNo,
                          @Param("uid") Long uid, @Param("securityCode") String securityCode,
                          @Param("status") int status, @Param("ob") String ob,
                          @Param("oe") String oe, @Param("start") long start,
                          @Param("pagesize") long pagesize);

    public List<OrderInfo> findByPage(@Param("orderNo") String orderNo,
                                      @Param("uid") Long uid, @Param("securityCode") String securityCode,
                                      @Param("status") int status, @Param("ob") String ob,
                                      @Param("oe") String oe, @Param("start") long start,
                                      @Param("pagesize") long pagesize);



    public List<OrderInfo> findByPageByHospital(@Param("orderNo") String orderNo,
                                                @Param("uid") Long uid, @Param("securityCode") String securityCode,
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

    public List<OrderInfo> findByPageOfLiu(@Param("example") OrderInfoExample example,@Param("start") long start ,
                                           @Param("pagesize")long pagesize );

    long updateOrderPrice(@Param("orderNo") String orderNo, @Param("price") BigDecimal addPrice, @Param("remark") String s);
}