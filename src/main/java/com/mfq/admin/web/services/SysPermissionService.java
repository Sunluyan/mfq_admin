package com.mfq.admin.web.services;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mfq.admin.web.dao.SysPermissionMapper;
import com.mfq.admin.web.models.SysAcl;
import com.mfq.admin.web.models.SysPermission;
import com.mfq.admin.web.models.SysRole;

@Service
public class SysPermissionService {

    private static final Logger logger = LoggerFactory
            .getLogger(SysPermissionService.class);

    @Resource
    SysPermissionMapper mapper;
    @Resource
    SysRoleService roleService;
    @Resource
    SysAclService aclService;

    public Map<Long, String> permissionNameFromAcl(List<SysAcl> acls) {
        Map<Long, String> roleNames = Maps.newHashMap();
        try {
            Map<Long, List<Long>> pmsMap = permissionFromAcl(acls);
            if (CollectionUtils.isEmpty(pmsMap)) {
                return roleNames;
            }
            Set<Long> roleSet = Sets.newHashSet(); // 用于从数据库获取role信息 去重后的集合
            for (Long key : pmsMap.keySet()) {
                roleSet.addAll(pmsMap.get(key));
            }
            List<SysRole> roles = roleService.queryRoles(roleSet);
            Map<Long, String> tmp = Maps.newHashMap();
            for (SysRole role : roles) {
                tmp.put(role.getId(), role.getRolename());
            }
            for (Long key : pmsMap.keySet()) {
                List<Long> v = pmsMap.get(key);
                StringBuilder nameBuilder = new StringBuilder();
                for (Long t : v) {
                    String rname = tmp.get(t);
                    if (rname == null) {
                        rname = "公共权限";
                    }
                    if (StringUtils.isBlank(nameBuilder.toString())) {
                        nameBuilder.append(rname);
                    } else {
                        nameBuilder.append(",").append(rname);
                    }
                }
                roleNames.put(key, nameBuilder.toString());
            }
        } catch (Exception e) {
            logger.error("Exception_PERMISSION_NAMES", e);
        }
        return roleNames;
    }

    public Map<Long, List<Long>> permissionFromAcl(List<SysAcl> acls) {
        Map<Long, List<Long>> map = Maps.newHashMap();
        try {
            List<Long> list = Lists.newArrayList();
            for (SysAcl acl : acls) {
                list.add(acl.getId());
            }
            List<SysPermission> permissions = queryByAcls(list);
            for (SysPermission p : permissions) {
                List<Long> v = map.get(p.getAcl());
                if (v == null) {
                    v = Lists.newArrayList();
                }
                v.add(p.getRole());
                map.put(p.getAcl(), v);
            }
        } catch (Exception e) {
            logger.error("Exception_PERMISSION", e);
        }
        return map;
    }

    public void submitEditModel(Long id, String url, String name, Integer type,
            Long pid, Integer index, String[] ps) {
        SysAcl acl = null;
        if(id == null || id == 0){ // add
            acl = new SysAcl();
            acl.setCreatedAt(new Date());
            acl.setName(name);
            acl.setPid(pid);
            acl.setType(type);
            acl.setUrl(url);
            acl.setIndex(index);
            aclService.insertOne(acl);
        }else{ // edit
            acl = aclService.findById(id);
            acl.setName(name);
            acl.setPid(pid);
            acl.setType(type);
            acl.setUrl(url);
            acl.setIndex(index);
            aclService.updateOne(acl);
        }
        for(String role : ps){
            SysPermission model = new SysPermission();
            model.setAcl(acl.getId());
            model.setRole(Long.parseLong(role));
            insertOne(model);
        }
    }

    public void buildEditModel(Long id, Model model) {
        List<SysPermission> permissions = null;
        if (id == null || id == 0) {
            permissions = Lists.newArrayList();
        } else {
            permissions = queryByAcl(id);
        }
        SysAcl acl = aclService.findById(id);
        Map<Long, String> roleNames = Maps.newHashMap();
        Map<Long, Boolean> roleValues = Maps.newHashMap();
        List<SysRole> roles = roleService.queryAll();
        for (SysRole role : roles) { // 没加条件判断
            roleNames.put(role.getId(), role.getRolename());
            for (SysPermission p : permissions) {
                if (role.getId() == p.getRole()) {
                    roleValues.put(role.getId(), true);
                }
            }
            if (roleValues.get(role.getId()) == null) {
                roleValues.put(role.getId(), false);
            }
        }
        model.addAttribute("id", id);
        model.addAttribute("acl", acl);
        model.addAttribute("roleNames", roleNames);
        model.addAttribute("roleValues", roleValues);
    }

    public long insertOne(SysPermission model) {
        return mapper.insertOne(model);
    }

    public List<SysPermission> queryAll() {
        return mapper.queryAll();
    }

    public List<SysPermission> queryByAcls(List<Long> list) {
        return mapper.queryByAcls(list);
    }

    public List<SysPermission> queryByAcl(long acl) {
        return mapper.queryByAcl(acl);
    }

    public List<SysPermission> queryByRole(long role) {
        return mapper.queryByRole(role);
    }

    public List<SysPermission> deleteOne(long role, long acl) {
        return mapper.deleteOne(role, acl);
    }

    public long deleteByRole(long role) {
        return mapper.deleteByRole(role);
    }

    public long deleteByAcl(long acl) {
        return mapper.deleteByAcl(acl);
    }
}