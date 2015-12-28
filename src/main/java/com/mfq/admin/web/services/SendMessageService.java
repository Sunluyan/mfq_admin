package com.mfq.admin.web.services;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.mfq.admin.web.dao.UserMapper;
import com.mfq.admin.web.models.user.User;
import com.mfq.admin.web.utils.HttpUtil;
import com.mfq.admin.web.utils.JSONUtil;
import com.mfq.admin.web.utils.MFQAdminUtil;
import com.mfq.admin.web.utils.RunnerUtils;
import com.mfq.admin.web.utils.SignHelper;

@Service
public class SendMessageService {

    @Resource
    UserMapper userMapper;

	public int sendMessageToAll(String content) {
		
		int size = 10;
		List<User>  users = null;
		int page = 1;
		long total = userMapper.queryUserCount();
		long start = 0;
		do{
			start = (page - 1) * size;
			users = userMapper.queryUserByPage(start, size);
			for(int i=0;i<users.size();i++){
				RequestSendMsg(content, users.get(i).getMobile());
				System.out.println(users.get(i).getMobile());
			}
			page=page+1;
		}while(start < total);
		
		return 0;
	}
	
	private String RequestSendMsg(final String content, final String mobiles){
		if(!"15910812061".equals(mobiles)){
			return "";
		}
		final String url = "http://m.5imfq.com/sms/send";
		RunnerUtils.submit(new Thread() {
            @Override
            public void run() {
        		
                Map<String, Object> params = Maps.newHashMap();
                params.put("mobile", mobiles);
                params.put("message", content);
                String sign = SignHelper.makeSign(params);
                String key = MFQAdminUtil.makeKey(params);
                params.put("sign", sign);
                params.put("key", key);
                String body = JSONUtil.writeToJson(params);
                HttpUtil.postJson(url, body, true);
            }
        });

        
        return "";
		
	}

	public static void main(String[] args){

	}

}
