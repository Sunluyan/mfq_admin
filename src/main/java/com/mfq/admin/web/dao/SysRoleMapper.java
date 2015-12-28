package com.mfq.admin.web.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.SysRole;

@MQMDao
public interface SysRoleMapper {

    public long insertRole(SysRole model);

    public List<SysRole> queryAll();
    
    public List<SysRole> queryRoles(@Param("list") Set<Long> list);
    
    public SysRole queryRole(@Param("id") long id);

    public SysRole queryRoleByName(@Param("rolename") String rolename);

}
