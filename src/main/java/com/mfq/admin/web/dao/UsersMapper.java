package com.mfq.admin.web.dao;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.bean.User;
import com.mfq.admin.web.bean.example.UsersExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MQMDao
public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UsersExample example);

    User selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") User record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    public List<User> queryUserByPage(@Param("start")long start,@Param("size") int size);

    public List<Map<String,Object>> queryCertify(
            @Param("start")Integer start,@Param("pagesize")Integer pagesize,@Param("type")String type,@Param("uid")String uid,
            @Param("phone")String phone,@Param("cardid")String cardid,@Param("applytimefrom")String applytimefrom,@Param("applytimeto")String applytimeto,
            @Param("checktimefrom")String checktimefrom,@Param("checktimeto")String checktimeto,@Param("count") String count
    );

    public List<Map<String,Object>> queryMobilesByPage(
            @Param("mobiles")String [] mobiles,
            @Param("start")long start,
            @Param("size")int size,
            @Param("username") String username,
            @Param("realname")String realname,
            @Param("phone")String phone,
            @Param("inviteid")String inviteid,
            @Param("alipay")String alipay,
            @Param("status")String status,
            @Param("fromtime")String fromtime,
            @Param("totime")String totime,
            @Param("count")String count);

    public Map<String,Object> queryUserDetail(@Param("uid") long uid);

    public Map<String,Object> queryInteviewUserDetail(@Param("uid") long uid);

}