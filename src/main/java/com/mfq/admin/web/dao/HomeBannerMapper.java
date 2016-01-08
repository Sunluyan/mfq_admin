package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.HomeBanner;
import com.mfq.admin.web.bean.example.HomeBannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface HomeBannerMapper {
    int countByExample(HomeBannerExample example);

    int deleteByExample(HomeBannerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HomeBanner record);

    int insertSelective(HomeBanner record);

    List<HomeBanner> selectByExample(HomeBannerExample example);

    HomeBanner selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HomeBanner record, @Param("example") HomeBannerExample example);

    int updateByExample(@Param("record") HomeBanner record, @Param("example") HomeBannerExample example);

    int updateByPrimaryKeySelective(HomeBanner record);

    int updateByPrimaryKey(HomeBanner record);
}