package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.Presental;
import com.mfq.admin.web.bean.example.PresentalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface PresentalMapper {
    int countByExample(PresentalExample example);

    int deleteByExample(PresentalExample example);

    int deleteByPrimaryKey(String code);

    int insert(Presental record);

    int insertSelective(Presental record);

    List<Presental> selectByExample(PresentalExample example);

    Presental selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") Presental record, @Param("example") PresentalExample example);

    int updateByExample(@Param("record") Presental record, @Param("example") PresentalExample example);

    int updateByPrimaryKeySelective(Presental record);

    int updateByPrimaryKey(Presental record);
}