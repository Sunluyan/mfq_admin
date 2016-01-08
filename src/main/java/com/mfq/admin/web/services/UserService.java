package com.mfq.admin.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.Nurse;
import com.mfq.admin.web.bean.User;
import com.mfq.admin.web.bean.example.NurseExample;
import com.mfq.admin.web.bean.example.UsersExample;
import com.mfq.admin.web.dao.NurseMapper;
import com.mfq.admin.web.dao.OrderInfoMapper;
import com.mfq.admin.web.dao.UsersMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.admin.web.constants.AuthStatus;

@Service
public class UserService {

    @Resource
    UsersMapper mapper;
    @Resource
    NurseMapper nurseMapper;

    @Resource
    OrderInfoMapper orderInfoMapper;
    public int PageSize = 50;

    public User queryUserByMobile(String mobile) {
        UsersExample example = new UsersExample();
        example.or().andMobileEqualTo(mobile);
        List<User> users = mapper.selectByExample(example);
        User user = (users.size() != 0)? users.get(0) : new User();
        return user;
    }

    public List<User> queryUsers(List<Long> list) {
        UsersExample example = new UsersExample();
        example.or().andUidIn(list);
        List<User> users = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            users = Lists.newArrayList();
        }
        return users;
    }


    public User queryUser(Long id) {

        User user = mapper.selectByPrimaryKey(id);
        if (user == null) {
            user = new User();
        }
        return user;
    }
    /*
     * 	Integer page = Integer.parseInt(request.getParameter("page"));
    	String type = request.getParameter("type");
    	String uid = request.getParameter("uid");
    	String phone = request.getParameter("phone");
    	String cardid = request.getParameter("cardid");
    	String applytimefrom = request.getParameter("applytimefrom");
    	String applytimeto = request.getParameter("applytimeto");
    	String checktimefrom = request.getParameter("checktimefrom");
    	String checktimeto = request.getParameter("checktimeto");
     */

    public Map<String, Object> certifyData(Integer page, String type, String uid, String phone, String cardid,
                                           String applytimefrom, String applytimeto, String checktimefrom, String checktimeto) {
        if (page == null || page.equals("")) {
            page = 1;
        }
        Integer start = (page - 1) * PageSize;
        List<Map<String, Object>> data = mapper.queryCertify(start, PageSize, type, uid, phone, cardid, applytimefrom, applytimeto, checktimefrom, checktimeto, null);
        for (Map<String, Object> map : data) {

            String createtime = map.get("createtime").toString();
            String updatetime = map.get("updatetime").toString();
            map.put("createtime", createtime);
            map.put("updatetime", updatetime);

            Integer authStatus = Integer.parseInt(map.get("auth_status").toString());
            map.put("auth_status", AuthStatus.fromId(authStatus).getDesc());

        }
        List<Map<String, Object>> count = mapper.queryCertify(start, PageSize, type, uid, phone, cardid, applytimefrom, applytimeto, checktimefrom, checktimeto, "yes");
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", data);
        result.put("count", count);
        return result;
    }


    /**
     * 该方法用于检索用户，
     *
     * @param model
     * @param mobiles  手机号
     * @param page     页数
     * @param username 用户名
     * @param realname 真实姓名
     * @param phone    手机
     * @param inviteid 邀请人id（暂查询邀请码）
     * @param alipay   支付宝
     * @param fromtime 按照起始和结束时间查询
     * @param totime
     */
    public void queryUsersByPage(Model model, String[] mobiles, long page, String username, String realname,
                                 String phone, String inviteid, String alipay, String status, String fromtime, String totime) {
        long start = (page - 1) * PageSize;
        long total = Long.parseLong(mapper.queryMobilesByPage(mobiles, start, PageSize, username, realname,
                phone, inviteid, alipay, status, fromtime, totime, "123").get(0).get("total").toString());

        //传入条件，查询用户信息
        List<Map<String, Object>> data = mapper.queryMobilesByPage(mobiles, start, PageSize, username, realname,
                phone, inviteid, alipay, status, fromtime, totime, null);

        //获取用户的uid，并放入「findStatusByUid」中查询每个id的各类订单数
        List<String> uids = new ArrayList<String>();
        for (Map<String, Object> map : data) {
            uids.add(map.get("uid").toString());
        }

        List<Map<String, Object>> orderCount = new ArrayList<Map<String, Object>>();
        if (data.size() > 0) {
            orderCount = orderInfoMapper.findStatusByUid(uids);
        }


        //uid,zero(未支付),one(已支付),three(已取消)   OrderStatus
        for (Map<String, Object> map : data) {
            String uid = map.get("uid").toString();
            boolean isHave = false;
            //循环，如果找到对应的uid，则放入map中，如果没有，则出来后放入0
            for (Map<String, Object> order : orderCount) {
                String orderUid = order.get("uid").toString();
                if (orderUid.equals(uid)) {
                    map.put("notpay", order.get("zero"));
                    map.put("alreadypay", order.get("one"));
                    map.put("cancel", order.get("three"));
                    orderCount.remove(order);
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                map.put("notpay", 0);
                map.put("alreadypay", 0);
                map.put("cancel", 0);
            }
            //实名认证字段
            if (map.get("auth_status") != null) {
                Integer authStatus = Integer.parseInt(map.get("auth_status").toString());
                map.put("status", AuthStatus.fromId(authStatus).getDesc());

            }

            //性别字段
            if (map.get("gender").toString() != null) {
                String gender = map.get("gender").toString();
                if (gender.equals("0")) {
                    map.put("gender", "无");
                } else if (gender.equals("1")) {
                    map.put("gender", "男");
                } else if (gender.equals("2")) {
                    map.put("gender", "女");
                }
            }

        }
        Map<String, Object> query = new HashMap<String, Object>();
        query.put("username", username);
        query.put("realname", realname);
        query.put("phone", phone);
        query.put("inviteid", inviteid);
        query.put("alipay", alipay);
        query.put("status", status);
        query.put("fromtime", fromtime);
        query.put("totime", totime);

        model.addAttribute("query", query);
        model.addAttribute("users", data);
        model.addAttribute("page", page);
        model.addAttribute("total", Math.ceil(total / PageSize));
    }


    public Map<String, Object> queryUserDetail(long uid) {

        return mapper.queryUserDetail(uid);
    }

    public Map<String, Object> queryInteviewUserDetail(long uid) {
        Map<String, Object> data = mapper.queryInteviewUserDetail(uid);

        Integer gender = Integer.parseInt(data.get("gender").toString());
        switch (gender) {
            case 0:
                data.put("gender", "未设置");
                break;
            case 1:
                data.put("gender", "男");
                break;
            case 2:
                data.put("gender", "女");
                break;
        }

        String contact = data.get("contact").toString();
        if (contact == null || contact.length() < 1) {
            data.put("contact", data.get("mobile"));
        }
        return data;
    }

    public List<Nurse> getAllNurse() {
        NurseExample example = new NurseExample();
        return nurseMapper.selectByExample(example);
    }

    public Nurse getNurseById(Integer id) {
        return nurseMapper.selectByPrimaryKey(id);
    }

    public Integer updateNurse(Nurse nurse) {
        return nurseMapper.updateByPrimaryKeySelective(nurse);
    }

    public Integer deleteNurse(Integer id) {
        return nurseMapper.deleteByPrimaryKey(id);
    }

    public Integer addNurse(Nurse nurse) {
        Integer count = nurseMapper.insertSelective(nurse);
        return count;
    }


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserService service = ac.getBean(UserService.class);
        List<Nurse> list = service.getAllNurse();

    }
}


