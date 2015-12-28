package com.mfq.admin.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.order.FinanceBill;
import com.mfq.admin.web.models.order.FinanceBillExample;

@MQMDao
public interface FinanceBillMapper {
    int countByExample(FinanceBillExample example);

    int deleteByExample(FinanceBillExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceBill record);

    int insertSelective(FinanceBill record);

    List<FinanceBill> selectByExample(FinanceBillExample example);

    FinanceBill selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceBill record, @Param("example") FinanceBillExample example);

    int updateByExample(@Param("record") FinanceBill record, @Param("example") FinanceBillExample example);

    int updateByPrimaryKeySelective(FinanceBill record);

    int updateByPrimaryKey(FinanceBill record);
    
    //返回同一个订单的最近的一个不是-1的账单
    List<FinanceBill> selectFinanceByPage(@Param("start")Integer start,
    		@Param("size")Integer size,
    		@Param("uid")long uid,
    		@Param("realname")String realname,
    		@Param("phone")String phone,
    		@Param("idcard")String idcard,
    		@Param("endTime")String endTime,
    		@Param("applytimefrom")String applytimefrom,
    		@Param("applytimeto")String applytimeto,
    		@Param("type") Integer type,
    		@Param("count") String count);
    
    
//    Map<String,Object> queryFinanceDetail(@Param("id") long id);
    
}


