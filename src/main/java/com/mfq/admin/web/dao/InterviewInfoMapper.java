package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.InterviewInfo;
import com.mfq.admin.web.bean.InterviewInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MQMDao
public interface InterviewInfoMapper {
    int countByExample(InterviewInfoExample example);

    int deleteByExample(InterviewInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InterviewInfo record);

    int insertSelective(InterviewInfo record);

    List<InterviewInfo> selectByExample(InterviewInfoExample example);

    InterviewInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByExample(@Param("record") InterviewInfo record, @Param("example") InterviewInfoExample example);

    int updateByPrimaryKeySelective(InterviewInfo record);

    int updateByPrimaryKey(InterviewInfo record);
}