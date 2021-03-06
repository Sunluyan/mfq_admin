package com.mfq.admin.web.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.mfq.admin.web.bean.PayRecord;
import com.mfq.admin.web.bean.example.PayRecordExample;
import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import org.springframework.stereotype.Component;


@MQMDao
@Component
public interface PayRecordMapper {
	int countByExample(PayRecordExample example);

    int deleteByExample(PayRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PayRecord record);

    int insertSelective(PayRecord record);

    List<PayRecord> selectByExample(PayRecordExample example);
    
    List<PayRecord> selectByExamplePage(PayRecordExample example);

    PayRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PayRecord record, @Param("example") PayRecordExample example);

    int updateByExample(@Param("record") PayRecord record, @Param("example") PayRecordExample example);

    int updateByPrimaryKeySelective(PayRecord record);

    int updateByPrimaryKey(PayRecord record);

    List<Long> queryByUpDateAndGroupByUid(@Param("ob")Date ob, @Param("oe")Date oe, @Param("status")int status, @Param("start")Integer start, @Param("size")Integer size);

}