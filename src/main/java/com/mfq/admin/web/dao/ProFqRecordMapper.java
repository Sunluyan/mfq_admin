package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.ProFqRecord;
import com.mfq.admin.web.bean.ProFqRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface ProFqRecordMapper {
    int countByExample(ProFqRecordExample example);

    int deleteByExample(ProFqRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProFqRecord record);

    int insertSelective(ProFqRecord record);

    List<ProFqRecord> selectByExample(ProFqRecordExample example);

    ProFqRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProFqRecord record, @Param("example") ProFqRecordExample example);

    int updateByExample(@Param("record") ProFqRecord record, @Param("example") ProFqRecordExample example);

    int updateByPrimaryKeySelective(ProFqRecord record);

    int updateByPrimaryKey(ProFqRecord record);
}