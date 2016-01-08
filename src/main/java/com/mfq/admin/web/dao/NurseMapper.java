package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.Nurse;
import java.util.List;

import com.mfq.admin.web.bean.example.NurseExample;
import org.apache.ibatis.annotations.Param;

@MQMDao
public interface NurseMapper {
    int countByExample(NurseExample example);

    int deleteByExample(NurseExample example);

    int deleteByPrimaryKey(Integer nurseNumber);

    int insert(Nurse record);

    int insertSelective(Nurse record);

    List<Nurse> selectByExample(NurseExample example);

    Nurse selectByPrimaryKey(Integer nurseNumber);

    int updateByExampleSelective(@Param("record") Nurse record, @Param("example") NurseExample example);

    int updateByExample(@Param("record") Nurse record, @Param("example") NurseExample example);

    int updateByPrimaryKeySelective(Nurse record);

    int updateByPrimaryKey(Nurse record);


}