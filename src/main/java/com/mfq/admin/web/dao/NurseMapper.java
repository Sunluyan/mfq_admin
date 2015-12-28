package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.user.Nurse;

@MQMDao
public interface NurseMapper {

    int deleteByPrimaryKey(Integer nurseNumber);

    int insert(Nurse record);

    int insertSelective(Nurse record);

    Nurse selectByPrimaryKey(Integer nurseNumber);

    int updateByPrimaryKeySelective(Nurse record);

    int updateByPrimaryKey(Nurse record);
    
    List<Nurse> selectAll();
}