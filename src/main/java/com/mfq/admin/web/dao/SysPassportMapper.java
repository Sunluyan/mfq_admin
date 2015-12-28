package com.mfq.admin.web.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.SysPassport;

@MQMDao
public interface SysPassportMapper {

    public long insertPassport(SysPassport passport);

    public SysPassport queryPassport(@Param("uid") long uid);

    public SysPassport queryValidPassportByTicket(@Param("uid") long uid,
            @Param("ticket") String ticket, @Param("status") int status);

    public boolean updateExpired(@Param("uid") long uid,
            @Param("expiredAt") long expiredAt);

    public boolean updateDefaultTicket(@Param("uid") long uid,
            @Param("ticket") String ticket, @Param("createdAt") Date createdAt,
            @Param("expiredAt") Date expiredAt);

}