package com.mfq.admin.web.services;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.SysRoleMapper;
import com.mfq.admin.web.models.SysRole;

@Service
public class SysRoleService {

    @Resource
    SysRoleMapper mapper;

    public long insertRole(SysRole role) {
        return mapper.insertRole(role);
    }

    public List<SysRole> queryAll() {
        return mapper.queryAll();
    }

    public List<SysRole> queryRoles(Set<Long> list) {
        return mapper.queryRoles(list);
    }
    
    public SysRole queryRole(long id) {
        return mapper.queryRole(id);
    }

    public SysRole queryRoleByName(String rolename) {
        return mapper.queryRoleByName(rolename);
    }
}