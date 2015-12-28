package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.SysAcl;

@MQMDao
public interface SysAclMapper {

    public SysAcl findById(@Param("id") long id);
    
    public long findCount(@Param("type") Integer type, @Param("start") Long start, @Param("limit") Long limit);
    
    public List<SysAcl> findAll(@Param("type") Integer type, @Param("start") Long start, @Param("limit") Long limit);
    
    //查找menu的children节点
    public List<SysAcl> findMenuChildren(@Param("pid") long pid, @Param("type") Integer type);
    
    public long updateOne(SysAcl model);
    
    public long insertOne(SysAcl model);
    
    public boolean deleteOne(@Param("id") long id);
}