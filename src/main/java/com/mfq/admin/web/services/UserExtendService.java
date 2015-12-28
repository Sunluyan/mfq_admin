package com.mfq.admin.web.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.admin.web.dao.UserExtendMapper;
import com.mfq.admin.web.models.user.UserExtend;

@Service
public class UserExtendService {

    //private static final Logger logger = LoggerFactory
    //        .getLogger(UserExtendService.class);

    @Resource
    UserExtendMapper mapper;

    public int insertUserExtend(UserExtend userExtend){
    	return mapper.insertUserExtend(userExtend);
    }

	public UserExtend getUserExtendByUid(long uid) {
		
		return mapper.queryUserExtendByUid(uid);
		
	}

	public int updateUserExtend(UserExtend extend) {
		return mapper.updateUserExtend(extend);
		
	}

}
