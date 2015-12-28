package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.SysPermission;

@MQMDao
public interface SysPermissionMapper {
    
    public long insertOne(SysPermission model);
    
    public List<SysPermission> queryAll();

    public List<SysPermission> queryByAcls(@Param("list") List<Long> list);
    
    public List<SysPermission> queryByAcl(@Param("acl") long acl);
    
    public List<SysPermission> queryByRole(@Param("role") long role);
    
    public List<SysPermission> deleteOne(@Param("role") long role, @Param("acl") long acl);
    
    public long deleteByRole(@Param("role") long role);
    
    public long deleteByAcl(@Param("acl") long acl);
}