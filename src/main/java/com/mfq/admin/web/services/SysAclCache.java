package com.mfq.admin.web.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mfq.admin.web.bean.Menu;
import com.mfq.admin.web.bean.SysAcl;
import com.mfq.admin.web.bean.SysPermission;
import com.mfq.admin.web.bean.SysRole;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.admin.web.constants.AclTypeEnum;
import com.mfq.admin.web.constants.AdminConstants;
import com.mfq.admin.web.context.SpringWrapper;

/**
 * ActionURL－权限缓存
 * 
 */
public class SysAclCache {

    private static final Logger logger = LoggerFactory
            .getLogger(SysAclCache.class);

    private static Map<Long, Menu> menuMap = Maps.newHashMap();
    private static Map<Long, Menu> roleMenu = Maps.newHashMap();
    private static Map<String, Long> aclMap = Maps.newHashMap(); // aclUrl->aclId
    private static Map<Long, List<Long>> aclsMap = Maps.newHashMap(); // roleId->aclIdList
    private static Menu DUMMY_MENU = new Menu(); // 虚拟menu根节点
    private static volatile SysAclCache instance = null;

    private SysAclCache() {

        SysAclService aclService = (SysAclService) SpringWrapper
                .getBean("sysAclService");
        SysPermissionService sysPermissionService = (SysPermissionService) SpringWrapper
                .getBean("sysPermissionService");
        SysRoleService sysRoleService = (SysRoleService) SpringWrapper
                .getBean("sysRoleService");

        List<SysAcl> acls = aclService.findByType(AclTypeEnum.ALL, null); // 所有
        List<SysPermission> permissions = sysPermissionService.queryAll();
        List<SysRole> roles = sysRoleService.queryAll();
        init(acls, permissions, roles);
    }

    private void init(Collection<SysAcl> acls,
            Collection<SysPermission> permissions, List<SysRole> roles) {
        // 构造menu树形结构
        menuMap.put(DUMMY_MENU.getPid(), DUMMY_MENU);
        Map<String, Long> tempAclMap = Maps.newHashMap();
        for (SysAcl acl : acls) {
            tempAclMap.put(acl.getUrl(), acl.getId()); // 装载ACLMap
            if (acl.getType() == AclTypeEnum.MENU.getId()) {
                Menu m = convertAcl2Menu(acl);
                menuMap.put(acl.getId(), m);
            }
        }
        aclMap = tempAclMap; // 重置aclMap
        // 构造child关系
        for (SysAcl acl : acls) {
            if (acl.getId() == 0
                    || acl.getType() != AclTypeEnum.MENU.getId()) {
                continue;
            }
            Menu parentMenu = menuMap.get(acl.getPid());
            if (parentMenu != null) {
                Menu cam = menuMap.get(acl.getId());
                parentMenu.addChild(cam);
            } else {
                logger.error("MENU WITHOUT PARENT, {}", acl);
            }
        }
        for (Menu menu : DUMMY_MENU.getChildren()) {
            menu.sort();
        }
        DUMMY_MENU.sort();

        // 设置所有action的可访问角色，如果某个菜单的角色为空则可访问
        setAclRoles(permissions);

        // 构造menus的cache
        setRolesMenu(roles);
    }

    private Menu convertAcl2Menu(SysAcl acl) {
        Menu menu = new Menu();
        if (acl == null) {
            return menu;
        }
        menu.setId(acl.getId());
        menu.setIndex(acl.getIndex());
        menu.setName(acl.getName());
        menu.setPid(acl.getPid());
        menu.setUrl(acl.getUrl());
        return menu;
    }

    private void setAclRoles(Collection<SysPermission> permissions) {
        if (CollectionUtils.isEmpty(permissions)) {
            return;
        }
        for (SysPermission permission : permissions) {
            List<Long> list = aclsMap.get(permission.getRole());
            if (CollectionUtils.isEmpty(list)) {
                list = Lists.newArrayList();
            }
            if(!list.contains(permission.getAcl())){
                list.add(permission.getAcl());
                aclsMap.put(permission.getRole(), list);
            }
        }
    }

    private void setRolesMenu(Collection<SysRole> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        for (SysRole role : roles) {
            loadMenuByRole(role.getId());
        }
    }

    /**
     * 
     * @return acl id list
     */
    public List<Long> getSysAcl(long roleId) {
        long pubkey = AdminConstants.PUBLIC_AUTHORITY;
        List<Long> list0 = aclsMap.get(pubkey);
        List<Long> list1 = aclsMap.get(roleId);
        if (CollectionUtils.isEmpty(list1)) {
            list1 = Lists.newArrayList();
        }
        if (CollectionUtils.isEmpty(list0)) {
            list0 = Lists.newArrayList();
        }
        List<Long> list = Lists.newArrayList();
        list.addAll(list0);
        list.addAll(list1);
        return list;
    }

    public Menu getRoot() {
        return DUMMY_MENU;
    }

    public Menu getMenu(long roleId) {
        return roleMenu.get(roleId);
    }

    private Menu loadMenuByRole(long roleId) {
        Menu root = new Menu();
        loadMenuByRole(DUMMY_MENU.getChildren(), root, roleId);
        roleMenu.put(roleId, root);
        return root;
    }

    private void loadMenuByRole(List<Menu> menus, Menu parentMenu,
            long roleId) {
        if (CollectionUtils.isEmpty(menus)) {
            return;
        }
        for (Menu c : menus) {
            if (hasRight(roleId, c.getUrl())) {
                // 构造并copy属性到新类别
                Menu one = new Menu();
                one.setId(c.getId());
                one.setIndex(c.getIndex());
                one.setName(c.getName());
                one.setPid(c.getPid());
                one.setUrl(c.getUrl()); // 构造并copy属性到新类别
                parentMenu.addChild(one); // 先添加child
                // 对后面的
                loadMenuByRole(c.getChildren(), one, roleId);
            }
        }
    }

    /**
     * 判断是否有权限
     * 
     * @param roleId
     * @param uri:注意实际为uri，不是url
     * @return
     */
    public boolean hasRight(Long roleId, String uri) {
        if (roleId == null || StringUtils.isBlank(uri)) {
            return false;
        }
        Long aclId = aclMap.get(uri);
        boolean isHas = hasRight(roleId, aclId);
        return isHas;
    }

    /**
     * 判断是否有权限
     * 
     * @param roleId
     * @param aclId
     * @return
     */
    public boolean hasRight(Long roleId, Long aclId) {
        if (roleId == null || aclId == null) {
            return false;
        }
        List<Long> acls = getSysAcl(roleId);
        if (CollectionUtils.isEmpty(acls)) {
            return false;
        }
        logger.info("权限s URL = {}，此URL = {}", acls, aclId);
        for (Long acl : acls) {
            if (acl == aclId) {
                return true;
            }
        }
        return false;
    }

    public static synchronized SysAclCache reload(boolean force) {
        if (instance == null || force) {
            instance = new SysAclCache();
        }
        return instance;
    }

    public static SysAclCache getInstance() {
        return instance == null ? reload(false) : instance;
    }
}