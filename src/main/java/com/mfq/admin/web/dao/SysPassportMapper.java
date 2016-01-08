package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.SysPassport;
import com.mfq.admin.web.bean.example.SysPassportExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface SysPassportMapper {
    int countByExample(SysPassportExample example);

    int deleteByExample(SysPassportExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(SysPassport record);

    int insertSelective(SysPassport record);

    List<SysPassport> selectByExample(SysPassportExample example);

    SysPassport selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") SysPassport record, @Param("example") SysPassportExample example);

    int updateByExample(@Param("record") SysPassport record, @Param("example") SysPassportExample example);

    int updateByPrimaryKeySelective(SysPassport record);

    int updateByPrimaryKey(SysPassport record);


    SysPassport queryValidPassportByTicket(@Param("uid") long uid,
                                                  @Param("ticket") String ticket, @Param("status") int status);


    int updateDefaultTicket(@Param("uid") long uid,
                                       @Param("ticket") String ticket, @Param("createdAt") Date createdAt,
                                       @Param("expiredAt") Date expiredAt);
}