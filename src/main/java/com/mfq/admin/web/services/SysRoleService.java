package com.mfq.admin.web.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.SysRole;
import com.mfq.admin.web.bean.example.SysRoleExample;
import com.mfq.admin.web.dao.SysRoleMapper;
import org.springframework.stereotype.Service;


@Service
public class SysRoleService {

    @Resource
    SysRoleMapper mapper;

    public long insertRole(SysRole role) {
        return mapper.insert(role);

    }

    public List<SysRole> queryAll() {

        return mapper.selectByExample(new SysRoleExample());
    }

    public List<SysRole> queryRoles(Set<Long> list) {
        SysRoleExample example = new SysRoleExample();
        Iterator<Long> it = list.iterator();
        List<Long> lists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            lists.add(it.next());
        }
        example.or().andIdIn(lists);
        return mapper.selectByExample(example);
    }
    
    public SysRole queryRole(long id) {
        SysRoleExample example = new SysRoleExample();
        example.or().andIdEqualTo(id);
        return mapper.selectByExample(example).get(0);
    }

    public SysRole queryRoleByName(String rolename) {
        SysRoleExample example = new SysRoleExample();
        example.or().andRolenameEqualTo(rolename);
        return mapper.selectByExample(example).get(0);
    }
}