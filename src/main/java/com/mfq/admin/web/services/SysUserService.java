package com.mfq.admin.web.services;

import java.util.List;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.SysUser;
import com.mfq.admin.web.bean.example.SysUserExample;
import com.mfq.admin.web.dao.SysUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.admin.web.constants.Status;

@Service
public class SysUserService {

    @Resource
    SysUserMapper mapper;
    
    private static final int LIMIT = 20;
    
    
    /**
     * 事务管理，用于关联用户创建
     * @param mobile
     * @return
     */
    public long createUser(String username, String realname, String mobile, long roleId) {
        if (StringUtils.isBlank(username) && StringUtils.isBlank(realname)) {
            throw new RuntimeException("username and realname both are empty");
        }
        SysUser sysUser = new SysUser(username, realname, mobile, roleId);
        insertSysUser(sysUser);
        return sysUser == null? 0 : sysUser.getId();
    }
    
    public List<SysUser> querySysUserByPage(Long page) {
        if(page == null){
            page = 1L;
        }
        long start = (page - 1) * LIMIT;
        List<SysUser> users = mapper.querySysUserByPage(start, LIMIT);
        if(CollectionUtils.isEmpty(users)){
            users = Lists.newArrayList();
        }
        return users;
    }
    
    public long insertSysUser(SysUser user){
        return mapper.insert(user);
    }
    
    public SysUser querySysUser(long uid) {
        SysUser user = mapper.selectByPrimaryKey(uid);
        if(user == null){
            user = new SysUser();
        }
        return user;
    }


    public SysUser queryUserByName(String username) {
        SysUserExample example = new SysUserExample();
        example.or().andUsernameEqualTo(username);
        SysUser user = mapper.selectByExample(example).get(0);
        if(user == null){
            user = new SysUser();
        }
        return user;
    }

    public boolean updateStatus(long uid, Status status) {
        SysUser user = new SysUser();
        user.setId(uid);
        user.setStatus(status);
        return mapper.updateByPrimaryKeySelective(user) == 1;
    }
    public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		SysUserService service = ac.getBean(SysUserService.class);
		System.out.println(service.queryUserByName("hui.zhang"));
	}



}
