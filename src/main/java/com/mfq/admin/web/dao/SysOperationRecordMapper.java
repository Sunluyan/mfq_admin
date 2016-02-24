package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.SysOperationRecord;
import com.mfq.admin.web.bean.SysOperationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@MQMDao
public interface SysOperationRecordMapper {
    int countByExample(SysOperationRecordExample example);

    int deleteByExample(SysOperationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOperationRecord record);

    int insertSelective(SysOperationRecord record);

    List<SysOperationRecord> selectByExample(SysOperationRecordExample example);

    SysOperationRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOperationRecord record, @Param("example") SysOperationRecordExample example);

    int updateByExample(@Param("record") SysOperationRecord record, @Param("example") SysOperationRecordExample example);

    int updateByPrimaryKeySelective(SysOperationRecord record);

    int updateByPrimaryKey(SysOperationRecord record);
}