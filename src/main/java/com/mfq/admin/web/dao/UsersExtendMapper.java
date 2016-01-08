package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.UserExtend;
import com.mfq.admin.web.bean.example.UsersExtendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@MQMDao
public interface UsersExtendMapper {
    int countByExample(UsersExtendExample example);

    int deleteByExample(UsersExtendExample example);

    int insert(UserExtend record);

    int insertSelective(UserExtend record);

    List<UserExtend> selectByExample(UsersExtendExample example);

    int updateByExampleSelective(@Param("record") UserExtend record, @Param("example") UsersExtendExample example);

    int updateByExample(@Param("record") UserExtend record, @Param("example") UsersExtendExample example);
}