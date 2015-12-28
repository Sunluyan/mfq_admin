package com.mfq.admin.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.constants.Status;
import com.mfq.admin.web.models.SysUser;

@MQMDao
public interface SysUserMapper {

	public long insertSysUser(SysUser user);

	public List<SysUser> querySysUserByPage(@Param("start") long start, @Param("limit") long limit);

	public SysUser querySysUser(@Param("id") long id);

	public SysUser querySysUserByName(@Param("username") String username);

	public boolean updateStatus(@Param("id") long id,
            @Param("status") Status status);
}
