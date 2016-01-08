package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.HomeClassify;
import com.mfq.admin.web.bean.example.HomeClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface HomeClassifyMapper {
    int countByExample(HomeClassifyExample example);

    int deleteByExample(HomeClassifyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeClassify record);

    int insertSelective(HomeClassify record);

    List<HomeClassify> selectByExample(HomeClassifyExample example);

    HomeClassify selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeClassify record, @Param("example") HomeClassifyExample example);

    int updateByExample(@Param("record") HomeClassify record, @Param("example") HomeClassifyExample example);

    int updateByPrimaryKeySelective(HomeClassify record);

    int updateByPrimaryKey(HomeClassify record);
}