package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.FinanceBill;
import com.mfq.admin.web.bean.example.FinanceBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
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
}