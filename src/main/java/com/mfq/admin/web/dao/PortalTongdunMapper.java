package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.PortalTongdun;
import com.mfq.admin.web.bean.PortalTongdunExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface PortalTongdunMapper {
    int countByExample(PortalTongdunExample example);

    int deleteByExample(PortalTongdunExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PortalTongdun record);

    int insertSelective(PortalTongdun record);

    List<PortalTongdun> selectByExample(PortalTongdunExample example);

    PortalTongdun selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PortalTongdun record, @Param("example") PortalTongdunExample example);

    int updateByExample(@Param("record") PortalTongdun record, @Param("example") PortalTongdunExample example);

    int updateByPrimaryKeySelective(PortalTongdun record);

    int updateByPrimaryKey(PortalTongdun record);
}