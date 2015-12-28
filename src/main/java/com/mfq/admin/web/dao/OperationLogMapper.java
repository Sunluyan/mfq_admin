package com.mfq.admin.web.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.OpLog;

@MQMDao
public interface OperationLogMapper {

    public boolean addLog(@Param("user") String user,
            @Param("type") String type, @Param("description") String description,
            @Param("ip") String ip);

    public List<OpLog> queryOpLogs(@Param("user") String user,
            @Param("type") String type, @Param("startTime") Date startTime,
            @Param("endTime") Date endTime, @Param("start") long start,
            @Param("size") long size);

}
