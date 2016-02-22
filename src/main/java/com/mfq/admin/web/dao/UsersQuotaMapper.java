package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.User;
import com.mfq.admin.web.bean.UserQuota;
import com.mfq.admin.web.bean.example.UsersQuotaExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@MQMDao
@Component
public interface UsersQuotaMapper {
    int countByExample(UsersQuotaExample example);

    int deleteByExample(UsersQuotaExample example);

    int insert(UserQuota record);

    int insertSelective(UserQuota record);

    List<UserQuota> selectByExample(UsersQuotaExample example);

    int updateByExampleSelective(@Param("record") UserQuota record, @Param("example") UsersQuotaExample example);

    int updateByExample(@Param("record") UserQuota record, @Param("example") UsersQuotaExample example);

    public Map<String,Object> queryCertifyQuota(@Param("uid") long uid);

    List<UserQuota> certifyDataExample(@Param("example")UsersQuotaExample example, @Param("start")int start, @Param("size")int page);
}