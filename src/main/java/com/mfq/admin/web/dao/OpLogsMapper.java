package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.OpLogs;
import com.mfq.admin.web.bean.example.OpLogsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface OpLogsMapper {
    int countByExample(OpLogsExample example);

    int deleteByExample(OpLogsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpLogs record);

    int insertSelective(OpLogs record);

    List<OpLogs> selectByExample(OpLogsExample example);

    OpLogs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpLogs record, @Param("example") OpLogsExample example);

    int updateByExample(@Param("record") OpLogs record, @Param("example") OpLogsExample example);

    int updateByPrimaryKeySelective(OpLogs record);

    int updateByPrimaryKey(OpLogs record);

    public boolean addLog(@Param("user") String user,
                          @Param("type") String type, @Param("description") String description,
                          @Param("ip") String ip);
}