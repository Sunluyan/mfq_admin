package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.AreaCity;
import com.mfq.admin.web.bean.example.AreaCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@MQMDao
@Component
public interface AreaCityMapper {
    int countByExample(AreaCityExample example);

    int deleteByExample(AreaCityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaCity record);

    int insertSelective(AreaCity record);

    List<AreaCity> selectByExample(AreaCityExample example);

    AreaCity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaCity record, @Param("example") AreaCityExample example);

    int updateByExample(@Param("record") AreaCity record, @Param("example") AreaCityExample example);

    int updateByPrimaryKeySelective(AreaCity record);

    int updateByPrimaryKey(AreaCity record);

    List<AreaCity> findAllExistAreaCity();

}