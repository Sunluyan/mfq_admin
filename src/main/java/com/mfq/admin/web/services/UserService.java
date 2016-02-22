package com.mfq.admin.web.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.coupon.Coupon;
import com.mfq.admin.web.bean.example.*;
import com.mfq.admin.web.bean.example.NurseExample;
import com.mfq.admin.web.bean.model.OrderModel;
import com.mfq.admin.web.bean.model.OrderPayModel;
import com.mfq.admin.web.constants.*;
import com.mfq.admin.web.dao.*;
import com.mfq.admin.web.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;


@Service
public class UserService {

    @Resource
    UsersMapper mapper;
    @Resource
    NurseMapper nurseMapper;
    @Resource
    UsersQuotaMapper quotaMapper;
    @Resource
    CouponMapper couponMapper;
    @Resource
    PayRecordService payRecordService;

    @Resource
    DeviceMapper deviceMapper;
    @Resource
    OrderInfoMapper orderInfoMapper;
    @Resource
    ProductService productService;
    @Resource
    OrderService orderService;
    @Resource
    OrderFreedomService freedomService;

    @Resource
    HospitalMapper hospitalMapper;
    @Resource
    ProductMapper productMapper;
    @Resource
    UserFeedbackMapper userFeedbackMapper;

    public int PageSize = 50;

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            user.setUid(0l);
        }
        return user;
    }


    /**
     * 查询实名认证和面签用户信息
     * 1.如果查询条件中有 手机号,就先查users再查usersQuota表,count是1
     * 2.如果查询条件中有 认证类型,就先查userFeedback表,然后查出来usersQuota和users
     * 3.如果条件中没有,就按条件查usersQuota,再获取查出来的uid,用uid查users和feedback
     * @param page
     * @param type
     * @param uid
     * @param phone
     * @param cardid
     * @param applytimefrom
     * @param applytimeto
     * @param checktimefrom
     * @param checktimeto
     * @return
     */
    public Map<String, Object> certifyData(Integer page, String type, String uid, String phone, String cardid,
                                           String applytimefrom, String applytimeto, String checktimefrom, String checktimeto,
                                           String feedbacktype) {

        if(StringUtils.isNotEmpty(phone)){
            //通过手机号查找uid
            User user = queryUserByMobile(phone);
            //通过uid查询usersQuota
            UsersQuotaExample example = new UsersQuotaExample();
            example.or().andUidEqualTo(user.getUid());
            List<UserQuota> userQuotas= quotaMapper.selectByExample(example);
            UserQuota userQuota = userQuotas==null?null:userQuotas.get(0);
            //组装数据
            Map<String,Object> map = new HashMap<>();
            map.put("uid",userQuota.getUid());
            map.put("createtime",userQuota.getCreatedAt());
            map.put("updatetime",userQuota.getUpdatedAt());
            map.put("mobile",user.getMobile());
            map.put("realname",userQuota.getRealname());
            map.put("cardid",userQuota.getIdCard());
            map.put("auth_status",userQuota.getAuthStatus());
            map.put("school_remark",userQuota.getSchoolRemark());
            List<Map<String,Object>> data = new ArrayList<>();
            data.add(map);
            //查询feedback
            UserFeedbackExample userFeedbackExample = new UserFeedbackExample();
            userFeedbackExample.or().andUidEqualTo(user.getUid());
            List<UserFeedback> feedbacks = userFeedbackMapper.selectByExample(userFeedbackExample);
            //发送回去
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("data", data);
            result.put("count", 1);
            result.put("feedback",feedbacks);
            return result;
        }else if(StringUtils.isNotBlank(feedbacktype)){
            if(page == null || page.equals("")){
                page = 1;
            }
            Integer start = (page - 1) * PageSize;
            //通过feedbacktype查出所有符合要求的
            UserFeedbackExample userFeedbackExample = new UserFeedbackExample();
            userFeedbackExample.or().andFeedbackTypeEqualTo(feedbacktype);
            List<UserFeedback> userFeedbacks = userFeedbackMapper.selectByExample(userFeedbackExample);
            //通过authStatus查出所有符合要求的
            UsersQuotaExample usersQuotaExample = new UsersQuotaExample();
            usersQuotaExample.or().andAuthStatusEqualTo(typeToAuthstatus(type));
            List<UserQuota>  userQuotas = quotaMapper.selectByExample(usersQuotaExample);
            //提取共同的uids
            List<Long> uids = new ArrayList<>();
            for (UserFeedback userFeedback : userFeedbacks) {
                for (UserQuota userQuota : userQuotas) {
                    System.out.println(userQuota.getUid() + "..." +userFeedback.getUid());
                    if(userQuota.getUid()-userFeedback.getUid()==0){
                        uids.add(userQuota.getUid());
                    }
                }
            }
            if(uids.size() == 0){
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("data", null);
                result.put("count", 0);
                result.put("feedback",null);
                return result;
            }
            for(int i = 0;i<userQuotas.size();i++){
                boolean find = false;
                for(Long uuid : uids){
                    if(uuid == userQuotas.get(i).getUid()){
                        find = true;
                    }
                }
                if(find == false && i!=userQuotas.size()){
                    userQuotas.remove(i);
                    i--;
                }
            }
            for(int i = 0;i<userFeedbacks.size();i++){
                boolean find = false;
                for(Long uuid : uids){
                    System.out.println(uuid +"  "+ userFeedbacks.get(i).getUid()+"  "+ userFeedbacks.get(i).getUid().hashCode());
                    if(uuid == userFeedbacks.get(i).getUid()){
                        find = true;
                    }
                    if(uuid - userFeedbacks.get(i).getUid() == 0){
                        find = true;
                    }
                }
                if(find == false && i!= userFeedbacks.size()){
                    userFeedbacks.remove(i);
                    i--;
                }
            }

            //通过uids查询users
            UsersExample usersExample = new UsersExample();
            usersExample.or().andUidIn(uids);
            List<User> users = mapper.selectByExample(usersExample);
            //组装数据
            List<Map<String,Object>> data = new ArrayList<>();

            for(int i = (page-1)*50;i< (page*50 < uids.size()?page*50:uids.size());i++){
                UserQuota userQuota = userQuotas.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("uid",userQuota.getUid());
                map.put("createtime",userQuota.getCreatedAt());
                map.put("updatetime",userQuota.getUpdatedAt());
                for (User user : users) {
                    if(user.getUid() == userQuota.getUid()){
                        map.put("mobile",user.getMobile());
                    }
                }
                map.put("realname",userQuota.getRealname());
                map.put("cardid",userQuota.getIdCard());
                map.put("auth_status",userQuota.getAuthStatus());
                map.put("school_remark",userQuota.getSchoolRemark());
                data.add(map);
            }
            //发送回去
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("data", data);
            result.put("count", uids.size());
            result.put("feedback",userFeedbacks);
            return result;
        }else{
            if(page == null || page.equals("")){
                page = 1;
            }
            Integer start = (page - 1) * PageSize;
            UsersQuotaExample usersQuotaExample = new UsersQuotaExample();
            UsersQuotaExample.Criteria or = usersQuotaExample.or();
            if(type != null){
                or.andAuthStatusEqualTo(typeToAuthstatus(type));
            }
            if(StringUtils.isNotEmpty(uid)){
                or.andUidEqualTo(Long.parseLong(uid));
            }
            if(StringUtils.isNotEmpty(cardid)){
                or.andIdCardEqualTo(cardid);
            }
            if(StringUtils.isNotEmpty(applytimefrom) && StringUtils.isNotEmpty(applytimeto)){
                or.andCreatedAtBetween(new Date(applytimefrom),new Date(applytimeto));
            }
            if(StringUtils.isNotEmpty(checktimefrom) && StringUtils.isNotEmpty(checktimeto)){
                or.andUpdatedAtBetween(new Date(checktimefrom),new Date(checktimeto));
            }
            //根据条件查询userQuota数据
            List<UserQuota> list = quotaMapper.certifyDataExample(usersQuotaExample,start,PageSize);
            int count = quotaMapper.countByExample(usersQuotaExample);

            List<Long> uids = new ArrayList<>();
            if(CollectionUtils.isEmpty(list)){
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("data", null);
                result.put("count", count);
                result.put("feedback",null);
                return result;
            }
            for (UserQuota userQuota : list) {
                uids.add(userQuota.getUid());
            }
            List<User> users = queryByUids(uids);

            //查询用户备注
            UserFeedbackExample example = new UserFeedbackExample();
            if(CollectionUtils.isEmpty(uids)){
                example.or().andUidEqualTo(0l);
            }else{
                example.or().andUidIn(uids);
            }

            List<Map<String,Object>> data = new ArrayList<>();
            for (int i = 0; i<list.size();i++) {
                UserQuota userQuota = list.get(i);
                Map<String,Object> map = new HashMap<>();
                map.put("uid",userQuota.getUid());
                map.put("createtime",userQuota.getCreatedAt());
                map.put("updatetime",userQuota.getUpdatedAt());
                for (User user : users) {
                    if(user.getUid() == userQuota.getUid()){
                        map.put("mobile",user.getMobile());
                    }
                }
                map.put("realname",userQuota.getRealname());
                map.put("cardid",userQuota.getIdCard());
                map.put("auth_status",userQuota.getAuthStatus());
                map.put("school_remark",userQuota.getSchoolRemark());
                data.add(map);
            }

            List<UserFeedback> feedbacks = userFeedbackMapper.selectByExample(example);


            Map<String, Object> result = new HashMap<String, Object>();
            result.put("data", data);
            result.put("count", count);
            result.put("feedback",feedbacks);
            return result;
        }
    }


    public Integer typeToAuthstatus(String type){
        if(type.equals("unsee")){
            return 1;
        }else if(type.equals("pass")){
            return 2;
        }else if(type.equals("out")){
            return -2;
        }else if(type.equals("unseeInterview")){
            return 3;
        }else if(type.equals("passInterview")){
            return 4;
        }else if(type.equals("outInterview")){
            return -3;
        }
        return 0;
    }

    public List<User> queryByUids(List<Long> uids){
        UsersExample example = new UsersExample();
        example.or().andUidIn(uids);
        return mapper.selectByExample(example);
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserService service = ac.getBean(UserService.class);
        Map<String,Object> map = service.certifyData(1,"unsee",null,null,null,null,null,null,null,"没有");
        List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
        for (Map<String, Object> stringObjectMap : data) {
            System.out.println(stringObjectMap);
        }

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

    public long getYesterdayNewUserCount() throws Exception {
        UsersExample example = new UsersExample();
        Date yesterday = DateUtil.getYesterday();
        Date today = DateUtil.getToday();
        example.or().andCreatedAtBetween(yesterday,today);
        long count = (long)mapper.countByExample(example);
        return count;
    }

    public long getYesterdayNewDevice() throws ParseException {
        DeviceExample example = new DeviceExample();
        Date yesterday = DateUtil.getYesterday();
        Date today = DateUtil.getToday();
        example.or().andFirstLoginTimeBetween(yesterday,today);

        return (long)deviceMapper.countByExample(example);
    }

    public long getYesterdayNewOrder() throws ParseException {
        Date yesterday = DateUtil.getYesterday();
        Date today = DateUtil.getToday();
        OrderInfoExample example = new OrderInfoExample();
        example.or().andCreatedAtBetween(yesterday,today);

        return (long)orderInfoMapper.countByExample(example);
    }


    public long getYesterdayNewOrderOfPay() throws ParseException{
        Date yesterday = DateUtil.getYesterday();
        Date today = DateUtil.getToday();
        OrderInfoExample example = new OrderInfoExample();
        example.or().andCreatedAtBetween(yesterday,today).andStatusEqualTo(OrderStatus.PAY_OK.getValue());
        return (long)orderInfoMapper.countByExample(example);
    }

    public long getCountHospital(){
        HospitalExample example = new HospitalExample();
        long count = hospitalMapper.countByExample(example);
        return count;
    }

    public long getCountProduct(){
        ProductExample example = new ProductExample();
        example.or().andFlagNotEqualTo(-1);
        long count = productMapper.countByExample(example);
        return count;
    }

    public long updateUserFeedbackRemark(long uid , String remark){
        UserFeedbackExample example = new UserFeedbackExample();
        example.or().andUidEqualTo(uid);
        List<UserFeedback> list = userFeedbackMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            UserFeedback userFeedback = new UserFeedback();
            userFeedback.setUid(uid);
            userFeedback.setRemark(remark);
            return userFeedbackMapper.insertSelective(userFeedback);
        }else{
            UserFeedback userFeedback = list.get(0);
            userFeedback.setRemark(remark);
            return userFeedbackMapper.updateByPrimaryKey(userFeedback);
        }
    }
    @Transactional
    public long updateUserFeedbackFeedback(long uid , String feedback){
        UserFeedbackExample example = new UserFeedbackExample();
        example.or().andUidEqualTo(uid);
        List<UserFeedback> list = userFeedbackMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            UserFeedback userFeedback = new UserFeedback();
            userFeedback.setUid(uid);
            userFeedback.setFeedback(feedback);
            return userFeedbackMapper.insertSelective(userFeedback);
        }else{
            UserFeedback userFeedback = list.get(0);
            userFeedback.setFeedback(feedback);
            return userFeedbackMapper.updateByPrimaryKey(userFeedback);
        }
    }

    @Transactional
    public long updateUserFeedbackFeedbackType(long uid, String feedback_type) {
        UserFeedbackExample example = new UserFeedbackExample();
        example.or().andUidEqualTo(uid);
        List<UserFeedback> list = userFeedbackMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            UserFeedback userFeedback = new UserFeedback();
            userFeedback.setUid(uid);
            userFeedback.setFeedbackType(feedback_type);
            return userFeedbackMapper.insertSelective(userFeedback);
        }else{
            UserFeedback userFeedback = list.get(0);
            userFeedback.setFeedbackType(feedback_type);
            return userFeedbackMapper.updateByPrimaryKey(userFeedback);
        }
    }




    public long deleteUser(Long uid) {
        mapper.deleteByPrimaryKey(uid);
        UsersQuotaExample example = new UsersQuotaExample();
        example.createCriteria().andUidEqualTo(uid);
        quotaMapper.deleteByExample(example);
        return 0;
    }

    public void userDetailForBudget(long uid, Model model) throws Exception {
        List<PayRecord> czPays = payRecordService.queryPayRecordsByUser(uid, OrderType.RECHARGE,null);
        List<PayRecord> flPays = payRecordService.queryPayRecordsByUser(uid, OrderType.REFUND, null);

        czPays.addAll(flPays);

        List<PayRecord> mnPays = payRecordService.queryPayRecordsByUser(uid, OrderType.ONLINE, null);
        List<PayRecord> fkPays = payRecordService.queryPayRecordsByUser(uid, OrderType.FREEDOM, null);

        mnPays.addAll(fkPays);


        OrderInfoExample example = new OrderInfoExample();
        example.createCriteria().andUidEqualTo(uid);
        List<OrderInfo> orderInfos = orderInfoMapper.selectByExample(example);


//        OrderFreedomExample freedomExample = new OrderFreedomExample();
//        freedomExample.createCriteria().andUidEqualTo(uid);
        List<OrderFreedom> freedoms = freedomService.selectByUid(uid);


        List<OrderModel> orderModels = toOrderModels(orderInfos,freedoms);

        List<OrderPayModel> models = toOrderPayModels(mnPays);
        model.addAttribute("pays", czPays);
        model.addAttribute("mnPays", models);
        model.addAttribute("orders", orderModels);
    }



    private List<OrderModel> toOrderModels(List<OrderInfo> infos, List<OrderFreedom> freedoms){
        List<OrderModel> models = Lists.newArrayList();

        for(OrderInfo info:infos){
            OrderModel model = new OrderModel();

            model.setOrderNo(info.getOrderNo());
            model.setPrice(String.valueOf(info.getPrice()));
            model.setCoupon(info.getCouponNum());
            model.setUid(info.getUid());
            model.setCreateTime(format.format(info.getCreatedAt()));
            model.setHospital("");

            OrderStatus orderStatus = OrderStatus.fromValue(info.getStatus());
            model.setOrderStatus(orderStatus.getName());

            model.setStatus(orderStatus.getValue());
            models.add(model);
        }

        for(OrderFreedom info:freedoms){
            OrderModel model = new OrderModel();
            model.setOrderNo(info.getOrderNo());
            model.setPrice(String.valueOf(info.getPrice()));
            model.setCoupon(info.getCouponNum());
            model.setUid(info.getUid());
            model.setCreateTime(format.format(info.getCreateTime()));
            model.setHospital("");

            OrderStatus orderStatus = OrderStatus.fromValue(info.getStatus());
            model.setOrderStatus(orderStatus.getName());
            model.setStatus(orderStatus.getValue());
            models.add(model);
        }

        return models;

    }

    private List<OrderPayModel> toOrderPayModels(List<PayRecord> pays) throws Exception {
        List<OrderPayModel> models = Lists.newArrayList();
        for(PayRecord pay: pays){
            OrderPayModel model = new OrderPayModel();

            OrderType orderType = orderService.getOrderType(pay.getOrderNo());

            Coupon coupon = couponMapper.findByCouponNum(pay.getCardNo());
            OrderInfo info = orderInfoMapper.findByOrderNo(pay.getOrderNo());


            Product product = null;

            if(info!=null){
                product = productService.findById(info.getPid());
            }

            Hospital hospital = null;
            if(product !=null) {
                hospital = hospitalMapper.selectByPrimaryKey(product.getHospitalId());
            }

            OrderFreedom freedomInfo = null;
            if(orderType == OrderType.FREEDOM){
                freedomInfo = freedomService.selectByOrderNo(pay.getOrderNo());
                if(freedomInfo != null) {
                    model.setProductName("随意单-" + freedomInfo.getProname());

                    long hid = Long.valueOf(freedomInfo.getHospitalId());
                    hospital = hospitalMapper.selectByPrimaryKey(hid);
                    coupon = couponMapper.findByCouponNum(freedomInfo.getCouponNum());


                    model.setProductMoney(String.valueOf(freedomInfo.getPrice()));
                    OrderStatus orderStatus = OrderStatus.fromValue(freedomInfo.getStatus());
                    model.setOrderStatus(orderStatus.getName());
                    model.setStatus(orderStatus.getValue());
                    model.setProductMoney(String.valueOf(freedomInfo.getPrice()));
                }
            }



            model.setOrderNo(pay.getOrderNo());
            model.setTradeNo(pay.getTradeNo());
            model.setPayAmount(String.valueOf(pay.getAmount()));
            model.setPayBalance(String.valueOf(pay.getBalance()));

            if(coupon!=null&&coupon.getMoney()!=null){
                model.setPayCoupon(String.valueOf(coupon.getMoney()));
            }

            if(pay.getCallbackAt()!=null) {
                model.setPayDate(format.format(pay.getCallbackAt()));
            }
            PayAPIType apiType = PayAPIType.fromCode(pay.getTpp());
            if(apiType != null) {
                model.setPayType(apiType.getCode());
            }

            model.setPayBlank(pay.getBankCode());

            PayStatus payStatus = PayStatus.fromValue(pay.getStatus());
            model.setPayStatus(payStatus.getDesc());

            if(product!=null){
                model.setProductName(product.getName());

                model.setProductType(product.getType().getName());
            }

            if(info != null){
                model.setProductMoney(String.valueOf(info.getOnlinePay()));
                OrderStatus orderStatus = OrderStatus.fromValue(info.getStatus());
                if(orderStatus!=null) {
                    model.setOrderStatus(orderStatus.getName());
                    model.setStatus(orderStatus.getValue());
                }
            }

            if(hospital != null){
                model.setHospital(hospital.getName());
            }


        models.add(model);



        }

        return models;
    }


}

