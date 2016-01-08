package com.mfq.admin.web.services;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.UserExtend;
import com.mfq.admin.web.bean.example.UsersExtendExample;
import com.mfq.admin.web.dao.UsersExtendMapper;
import org.springframework.stereotype.Service;


@Service
public class UserExtendService {

    //private static final Logger logger = LoggerFactory
    //        .getLogger(UserExtendService.class);

    @Resource
	UsersExtendMapper mapper;

    public int insertUserExtend(UserExtend userExtend){
    	return mapper.insert(userExtend);
    }

	public UserExtend getUserExtendByUid(long uid) {
		UsersExtendExample example = new UsersExtendExample();
		example.or().andUidEqualTo(uid);
		return mapper.selectByExample(example).get(0);
		
	}

	public int updateUserExtend(UserExtend extend) {
		UsersExtendExample example = new UsersExtendExample();
		example.or().andUidEqualTo(extend.getUid());
		return mapper.updateByExampleSelective(extend,example);
		
	}

}
