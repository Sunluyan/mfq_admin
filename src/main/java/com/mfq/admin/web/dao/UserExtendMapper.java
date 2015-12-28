package com.mfq.admin.web.dao;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.user.UserExtend;

@MQMDao
public interface UserExtendMapper {

    public int insertUserExtend(UserExtend extend);

	public UserExtend queryUserExtendByUid(@Param("uid") long uid);

	public int updateUserExtend(UserExtend extend);


}