package com.mfq.admin.web.dao;

import com.mfq.admin.web.bean.Billtopay;
import com.mfq.admin.web.bean.BilltopayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BilltopayMapper {
    int countByExample(BilltopayExample example);

    int deleteByExample(BilltopayExample example);

    int insert(Billtopay record);

    int insertSelective(Billtopay record);

    List<Billtopay> selectByExampleWithBLOBs(BilltopayExample example);

    List<Billtopay> selectByExample(BilltopayExample example);

    int updateByExampleSelective(@Param("record") Billtopay record, @Param("example") BilltopayExample example);

    int updateByExampleWithBLOBs(@Param("record") Billtopay record, @Param("example") BilltopayExample example);

    int updateByExample(@Param("record") Billtopay record, @Param("example") BilltopayExample example);
}