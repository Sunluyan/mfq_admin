package com.mfq.admin.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mfq.admin.web.annotation.MQMDao;
import com.mfq.admin.web.models.user.User;

@MQMDao
public interface UserMapper {
	
    public User queryUserByMobile(@Param("mobile") String mobile);
    
    public List<User> queryUsers(@Param("list") List<Long> uids);
    
    public User queryUser(@Param("id") Long id);

	public List<String> queryAllMobiles();
	//OrderInfo
//mobiles, start, PageSize,username,realname,
	//phone,inviteid,alipay,isReal,fromtime,totime
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

	public long queryUserCount();
	
	
	public List<User> queryUserByPage(@Param("start")long start,@Param("size") int size);
	
	
	public List<Map<String,Object>> queryCertify(
			@Param("start")Integer start,@Param("pagesize")Integer pagesize,@Param("type")String type,@Param("uid")String uid,
			@Param("phone")String phone,@Param("cardid")String cardid,@Param("applytimefrom")String applytimefrom,@Param("applytimeto")String applytimeto,
			@Param("checktimefrom")String checktimefrom,@Param("checktimeto")String checktimeto,@Param("count") String count
			);
	
	public Map<String,Object> queryUserDetail(@Param("uid") long uid);
	
	public Map<String,Object> queryInteviewUserDetail(@Param("uid") long uid);
	
	public Integer insertInterviewCompany(@Param("uid")long uid,@Param("comname")String comname,@Param("salary")long salary
			,@Param("insureid")String insureid,@Param("workyear")String workyear,@Param("remark")String remark);
	
	public Integer updateByPrimaryKeySelective(User record);
	
	public User selectByPrimaryKey(@Param("uid")long uid);
}










