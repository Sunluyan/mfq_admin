package com.mfq.admin.web.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ExecutionError;
import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.bean.coupon.Coupon;
import com.mfq.admin.web.bean.example.OrderInfoExample;
import com.mfq.admin.web.bean.example.PayRecordExample;
import com.mfq.admin.web.constants.*;
import com.mfq.admin.web.dao.CouponMapper;
import com.mfq.admin.web.dao.FinanceBillMapper;
import com.mfq.admin.web.dao.OrderInfoMapper;
import com.mfq.admin.web.dao.UsersQuotaMapper;
import com.mfq.admin.web.models.view.FinanceOrder;
import com.mfq.admin.web.models.view.FinancePay;
import com.mfq.admin.web.models.view.FinanceUser;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.utils.JSONUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderService.class);

    @Resource
    OrderInfoMapper mapper;
    @Resource
    FinanceBillMapper financeMapper;
    @Resource
    ProductService productService;
    @Resource
    UserService userService;
    @Resource
    SysUserService sysUserService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    PayRecordService payRecordService;
    @Resource
    HospitalService hospitalService;
    @Resource
    CouponMapper couponMapper;
    @Resource
    OrderFreedomService freedomService;

    @Resource
    SysOperationService sysOperationService;

    Integer PageSize = 30;

    public void findByPage(String orderNo, String mobile, String securityCode,
                           int status, long page, String ob, String oe, Model model) throws Exception {

        logger.info("order_choose_params:{}|{}|{}|{}|{}|{}|{}", orderNo, mobile,
                securityCode, status, page, ob, oe);
        if (orderNo == null) {
            orderNo = "";
        }
        orderNo = orderNo.trim();
        mobile = mobile.trim();
        securityCode = securityCode.trim();

        if (page == 0) {
            page = 1;
        }
        long start = (page - 1) * PageSize;
        long begin = System.currentTimeMillis();
        User user = userService.queryUserByMobile(mobile);
        long end = System.currentTimeMillis();
        System.out.println(end - begin+"毫秒运行完queryUserByMobile");
        Long uid = user != null && user.getUid() == null ? 0 : user.getUid();
        if("".equals(StringUtils.stripToEmpty(mobile))){
            uid = null;
        }
        logger.info("search uid is {}",uid);
        long size = mapper.findCount(orderNo, uid, securityCode,
                status, ob, oe, start, PageSize);
        // 订单列表
        SysUser LoginUser = sysUserService.querySysUser(UserHolder.getUserId());
        if(LoginUser == null || LoginUser.getId() < 1){
            throw new Exception("请登录...");
        }
        List<OrderInfo> orders = null;
        if (LoginUser.getHospitalId() > 0) {
            orders = mapper.findByPageByHospital(orderNo, uid,
                    securityCode, status, ob, oe, start, LoginUser.getHospitalId(), PageSize);
        } else {
            orders = mapper.findByPage(orderNo, uid,
                    securityCode, status, ob, oe, start, PageSize);
        }


        Map<Long, Product> productMap = getProducts(orders);
        Map<Long, String> userMap = getUserNames(orders);
        Map<Integer, String> statusmap = Maps.newHashMap();
        for (OrderStatus os : OrderStatus.values()) {
            statusmap.put(os.getValue(), os.getName());
        }

        model.addAttribute("hospitals", hospitalService.findAll());
        model.addAttribute("size", size);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("mobile", mobile);
        model.addAttribute("securityCode", securityCode);
        model.addAttribute("orders", orders);
        model.addAttribute("pmap", productMap);
        model.addAttribute("umap", userMap);
        model.addAttribute("statusmap", statusmap);
        model.addAttribute("status", status);
        model.addAttribute("ob", ob);
        model.addAttribute("oe", oe);
        model.addAttribute("page", page);
    }


    private Map<Long, Product> getProducts(List<OrderInfo> orders) {
        Map<Long, Product> map = Maps.newHashMap();
        if (CollectionUtils.isEmpty(orders)) {
            return map;
        }
        List<Long> ids = Lists.newArrayList();
        for (OrderInfo order : orders) {
            ids.add(order.getPid());
        }
        List<Product> list = productService.findByIds(ids);
        for (Product p : list) {
            map.put(p.getId(), p);
        }
        return map;
    }

    private Map<Long, String> getUserNames(List<OrderInfo> orders) {
        Map<Long, String> map = Maps.newHashMap();
        if (CollectionUtils.isEmpty(orders)) {
            return map;
        }
        List<Long> ids = Lists.newArrayList();
        for (OrderInfo order : orders) {
            if (!ids.contains(order.getUid())) {
                ids.add(order.getUid());
            }
        }
        List<User> list = userService.queryUsers(ids);
        for (User u : list) {
            map.put(u.getUid(), u.getNick());
        }
        return map;
    }

    public OrderInfo findById(long id) {
        return mapper.findById(id);
    }

    public OrderInfo findByOrderNo(String orderNo) {
        return mapper.findByOrderNo(orderNo);
    }

    public long updateOrder(long id, int oldStatus, int newStatus) {
        return mapper.updateOrderStatusSafe(id, oldStatus, newStatus);
    }

    public void bindFinanceMode(Model model, long page, String orderNo, String mobile, String hospitalName, int payType,
                                String payApi, int status, String ob, String oe) {

        logger.info("order_finance_params:{}|{}|{}|{}|{}|{}|{}|{}", orderNo, mobile,
                hospitalName, payType, payApi, page, ob, oe);
        if (orderNo == null) {
            orderNo = "";
        }
        orderNo = orderNo.trim();
        mobile = mobile.trim();

        if (page == 0) {
            page = 1;
        }

        int[] status_s = new int[]{status, 100, 100};
        if (status != 1 && status != 2 && status != 8) {
            status_s[0] = 1;
            status_s[1] = 2;
            status_s[2] = 8;
        }

        long start = (page - 1) * PageSize;

        long size = mapper.findCountByFinanceOrder(orderNo, mobile, hospitalName, payType,
                payApi, status, ob, oe, start, PageSize);

        // 订单列表

        List<Map<String, Object>> orders = mapper.findByFinancePage(orderNo,
                mobile, hospitalName, payType, payApi, status_s, ob, oe, start, PageSize);

        model.addAttribute("payApis", PayAPIType.values());
        model.addAttribute("pays", PayType.values());
        model.addAttribute("status_id", status);
        model.addAttribute("size", size);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("mobile", mobile);
        model.addAttribute("hospitalName", hospitalName);
        model.addAttribute("orders", orders);
        model.addAttribute("pay_api", payApi);
        model.addAttribute("pay_type", payType);
        model.addAttribute("ob", ob);
        model.addAttribute("oe", oe);
        model.addAttribute("page", page);
    }

    /**
     * List<Map<String,Object>> selectFinanceByPage(@Param("start")Integer start,
     *
     * @param page
     * @return
     * @Param("size")Integer size,
     * @Param("uid")long uid,
     * @Param("realname")String realname,
     * @Param("phone")String phone,
     * @Param("idcard")String idcard,
     * @Param("endTime")String endTime,
     * @Param("applytimefrom")String applytimefrom,
     * @Param("applytimeto")String applytimeto,
     * @Param("type") String type);
     */
    public List<Map<String, Object>> selectFinanceByPage(Integer page, Integer size, long uid, String realname,
                                                         String phone, String idcard, String applytimefrom, String applytimeto, Integer type, String count) {
        Integer start = (page - 1) * size;
        Date date = new Date();
        date = new Date(date.getTime() - 1000 * 60 * 60 * 24 * 3);
        String endTime = null;
        if (type != null && type == 0) endTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

        List<FinanceBill> list = financeMapper.selectFinanceByPage(start, size, uid, realname, phone, idcard,
                endTime, applytimefrom, applytimeto, type, count);
        List<Map<String, Object>> result = new ArrayList<>();
        for (FinanceBill financeBill : list) {
            Map<String, Object> map = new HashMap<>();

            User user = userService.queryUser(financeBill.getUid());
            UserQuota userQuota = userQuotaService.queryUserQuota(financeBill.getUid());
            OrderInfo orderInfo = findByOrderNo(financeBill.getOrderNo());

            map.put("realname", userQuota.getRealname());
            map.put("mobile", user.getMobile());
            map.put("user_type", userQuota.getUserType());
            map.put("due_at", financeBill.getDueAt());
            map.put("price", orderInfo.getPrice());
            map.put("period_pay", orderInfo.getPeriodPay());
            map.put("period", financeBill.getCurPeriod());
            map.put("all_period", financeBill.getAllPeriod());
            map.put("new_balance", financeBill.getNewBalance());
            map.put("bill_at", financeBill.getBillAt());
            map.put("id", financeBill.getId());
            map.put("uid", financeBill.getUid());
            map.put("pay_at", financeBill.getPayAt());
            map.put("orderNo", financeBill.getOrderNo());
            result.add(map);
        }
        return result;

    }

    /**
     * 财务订单(finance控制)
     * @param model
     * @param ob
     * @param oe
     * @param page
     * @param hid
     * @param pname
     * @param type
     * @throws Exception
     */
    public void queryFinanceOrders(Model model, String ob, String oe, Integer page, long hid, String pname, int type, String mobile, String uname, int status, String orderNo, String code) throws Exception{
        Integer start = ((page - 1) * PageSize);

        Date b =null, e = null;

        SimpleDateFormat fmt =  new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotBlank(ob)&&StringUtils.isNotBlank(oe)) {
            b = fmt.parse(ob);
            e = fmt.parse(oe);
        }


        List<Long> uids = Lists.newArrayList();
        if(StringUtils.isNotBlank(mobile) || StringUtils.isNotBlank(uname)) {
            uids = userService.queryUidsByNameOrMobile(mobile, uname);
        }


        List<Long> pids = Lists.newArrayList();

        if(!(hid==0&&"".equals(pname))) {
            List<Product> products = productService.findByHidAndName(hid, pname);
            for (Product p : products) {
                pids.add(p.getId());

            }
        }

        OrderType orderType = OrderType.fromId(type);

        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();
        if(b!=null && e!= null){
            criteria.andCreatedAtBetween(b, e);
        }
        if(pids.size() > 0){
            criteria.andPidIn(pids);
        }
        if(orderType != null){
            criteria.andPayTypeEqualTo(type);
        }
        if(uids.size() > 0){
            criteria.andUidIn(uids);
        }
        if(StringUtils.isNotBlank(orderNo)){
            criteria.andOrderNoLike("%"+orderNo+"%");
        }
        if(status != 0){
            criteria.andStatusEqualTo(status);
        }
        if(StringUtils.isNotBlank(code)){
            criteria.andSecurityCodeLike("%"+code+"%");
        }

        criteria.andStatusNotEqualTo(OrderStatus.BOOK_OK.getValue());
        criteria.andStatusNotEqualTo(OrderStatus.CANCEL_OK.getValue());

        example.setOrderByClause("created_at desc");

        example.setStart(start);
        example.setSize(PageSize);


        List<OrderInfo> orderInfos = mapper.selectByExample(example);

        logger.info("order size is {}",orderInfos.size());

        List<FinanceOrder> orders = createFinanceOrders(orderInfos);


        //医院列表
        List<Hospital> hospitals = hospitalService.findAll();

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("orders", orders);

        List<Map<String,Object>> orderstatus = Lists.newArrayList();

        for(OrderStatus status1 : OrderStatus.values()) {

            Map<String,Object> tt = Maps.newHashMap();
            tt.put("id",status1.getValue());
            tt.put("name",status1.getName());
            tt.put("desc",status1.getDesc());
            orderstatus.add(tt);
        }
        model.addAttribute("order_status", orderstatus);
        model.addAttribute("page",page);

        model.addAttribute("hid",hid);
        model.addAttribute("ob", b);
        model.addAttribute("oe", e);
        model.addAttribute("hid", hid);
        model.addAttribute("pname", pname);
        model.addAttribute("type", type);
        
        
    }


    /**
     * 根据订单 查询订单信息  用户  订单支付信息
     * @param lists
     * @return
     * @throws Exception
     */
    public List<FinanceOrder> createFinanceOrders(List<OrderInfo> lists) throws Exception {

        List<FinanceOrder> data = Lists.newArrayList();
        logger.info(lists.size()+" 个 OrderInfo");
        for (OrderInfo info : lists){
            User user = userService.queryUser(info.getUid());
            UserQuota quota = userQuotaService.queryUserQuota(info.getUid());
            PayRecord payRecord = payRecordService.queryPayRecordByOrderNo(info.getOrderNo());
            if(payRecord == null){
                payRecord = new PayRecord();
                payRecord.setId(232L);
            }
            logger.info(payRecord == null?"payrecord == null":payRecord.toString());
            if(user==null || quota==null ||payRecord == null){
                logger.info("finance order is null {}|{}|{}", info.getUid(), info.getOrderNo(),info.getId());
            }

            Product product= productService.findById(info.getPid());

            if(product==null){product=new Product();product.setHospitalId(0l);}
            Hospital hospital = hospitalService.findById(product.getHospitalId());

            if(hospital == null){hospital = new Hospital();hospital.setId(0l);}
//            Coupon coupon = couponMapper.findByCouponNum(payRecord.getCardNo());
//            if(coupon == null){coupon = new Coupon();}


            FinanceOrder order = new FinanceOrder(user, quota, info, hospital, product, payRecord, new Coupon());
            data.add(order);

        }
        logger.info(data.size()+"  个 FinanceOrder");


        return data;
    }


    /**
     * 财务订单(finance控制) 之前
     * @param model
     * @param ob
     * @param oe
     * @param page
     * @param hid
     * @param pname
     * @param type
     * @throws Exception
     */
    public void queryFinance2(Model model, String ob, String oe, Integer page, long hid, String pname, int type) throws Exception{
        Integer start = ((page - 1) * PageSize);

        Date b =null, e = null;

        SimpleDateFormat fmt =  new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotBlank(ob)&&StringUtils.isNotBlank(oe)) {
            b = fmt.parse(ob);
            e = fmt.parse(oe);
        }



        List<Long> pids = Lists.newArrayList();

        if(!(hid==0&&"".equals(pname))) {
            List<Product> products = productService.findByHidAndName(hid, pname);
            for (Product p : products) {
                pids.add(p.getId());
            }
        }

        OrderType orderType = OrderType.fromId(type);
        PayAPIType apiType= null;
        long size = payRecordService.countFinanceByPrames(b, e, orderType, apiType, pids);
        List<PayRecord> payRecords = payRecordService.queryFinanceByPrames(b, e, orderType, apiType, start, PageSize, pids);


        List<FinanceUser> users = genFinanceUsersByPayRecords(payRecords);

        //医院列表
        List<Hospital> hospitals = hospitalService.findAll();

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("users",users);

        model.addAttribute("page", page);
        model.addAttribute("size",size);
        model.addAttribute("total",size);

        model.addAttribute("hid",hid);
        model.addAttribute("ob", b);
        model.addAttribute("oe", e);
        model.addAttribute("hid", hid);
        model.addAttribute("pname", pname);
        model.addAttribute("type", type);


    }


    /**
     * 组织financeUser by payRecords
     * @param payRecords
     * @return
     * @throws Exception
     */
    private List<FinanceUser> genFinanceUsersByPayRecords(List<PayRecord> payRecords) throws Exception{
    	List<FinanceUser> users = Lists.newArrayList();
    	
    	Map<Long,List<PayRecord>> uMaps = PayRecordsGroupByUid(payRecords);
    	
    	for (Map.Entry<Long, List<PayRecord>> entry : uMaps.entrySet()) {  
    		  
    	    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
    	    
    	    FinanceUser user = createFinanceUser(entry.getValue(), entry.getKey());
    	    users.add(user);
    	}
    	
    
    	return users;
    }
    
    /**
     * 分组
     * @param payRecords
     * @return
     */
    private Map<Long,List<PayRecord>> PayRecordsGroupByUid(List<PayRecord> payRecords){
    	Map<Long,List<PayRecord>> uMaps = Maps.newHashMap();
    	
    	for(PayRecord p:payRecords){
    		List<PayRecord> ps = Lists.newArrayList();
    		if(uMaps.containsKey(p.getUid())){
    			ps = uMaps.get(p.getUid());
    		}else{
    			uMaps.put(p.getUid(), ps);
    		}
    		ps.add(p);
    	}
    	return uMaps;
    }
    
    
    private FinanceUser createFinanceUser(List<PayRecord> list, long uid) throws Exception{
    	
    	User user = userService.queryUser(uid);
        UserQuota uq = userQuotaService.queryUserQuota(uid);
        
    	 Map<String, Object> counts = countOfPayRecords(list, null, null);

         if(uq==null)uq=new UserQuota();

         //组织用户实体
         FinanceUser financeUser = new FinanceUser();
         financeUser.setUid(uid);
         financeUser.setName(uq.getRealname());
         financeUser.setMobile(user.getMobile());
         financeUser.setBlance(uq.getBalance());
         financeUser.setQuotaAll(uq.getQuotaAll());
         financeUser.setQuotaLeft(uq.getQuotaLeft());
         
         financeUser.setOrderMoney((BigDecimal)counts.get("orderMoney"));
         financeUser.setRecharge((BigDecimal)counts.get("recharge"));
         
         financeUser.setCz_count((Integer)counts.get("czCount"));
         financeUser.setMn_count((Integer)counts.get("mnCount"));
         financeUser.setFl_count((Integer)counts.get("flCount"));
         financeUser.setCz_count_at((Integer)counts.get("czCountAt"));
         financeUser.setMn_count_at((Integer)counts.get("mnCountAt"));
         financeUser.setFl_count_at((Integer)counts.get("flCountAt"));
         

         
         
         financeUser.setOrders(createFinanceByPayRecords(list));
         
         
        return financeUser;
    }
    
    private List<FinanceOrder> createFinanceByPayRecords(List<PayRecord> list){
    	List<FinanceOrder> data = Lists.newArrayList();
    	for(PayRecord p: list){
    		OrderInfo info = mapper.findByOrderNo(p.getOrderNo());
    		if(info==null){info=new OrderInfo();info.setId(0);}
    		
    		Product product = productService.findById(info.getPid());
    		if(product==null){product=new Product();product.setHospitalId(0l);}
    		
    		Hospital hospital= hospitalService.findById(product.getHospitalId());
    		if(hospital==null)hospital=new Hospital();
    		
    		Coupon coupon = couponMapper.findByCouponNum(p.getCardNo());
    		if(coupon==null)coupon=new Coupon();

    		FinanceOrder order = new FinanceOrder();
    		
    		order.setId(p.getId());
    		order.setOrderNo(p.getOrderNo());
    	    
    	    order.setOrderType(0);
    	    order.setTradeNo(p.getTradeNo());
    	    order.setUseAmount(p.getAmount());
    	    order.setUseBalance(p.getBalance());
    	    order.setUsePresent(p.getPresent());
    	    order.setUseCard(coupon.getMoney());
    	    order.setTpp(p.getTpp());
    	    order.setPid(info.getPid());
    	    order.setpName(product.getName());
    	    order.setBankCode(p.getBankCode());
    	    order.setCardType(p.getCardType());
    	    order.setCardNo(p.getCardNo());
    	    order.setPayStatus(p.getStatus());
    	    order.setCallbackAt(p.getCallbackAt());
    	    
    	    data.add(order);
    	}
    	
    	return data;
    }
    
    /**
     * 统计订单数
     * @param list
     * @param ob
     * @param oe
     * @return
     * @throws Exception
     */
    public Map<String, Object> countOfPayRecords(List<PayRecord> list, Date ob, Date oe) throws Exception {
        Map<String, Object> data = Maps.newHashMap();
        int czCount = 0, mnCount = 0, flCount = 0, czCountAt = 0, mnCountAt = 0, flCountAt = 0;
        
        BigDecimal useBalance, recharge, orderMoney, couponMoney;
        useBalance = BigDecimal.valueOf(0);
        recharge = BigDecimal.valueOf(0);
        orderMoney = BigDecimal.valueOf(0);
        couponMoney = BigDecimal.valueOf(0);
        
        for (PayRecord p : list) {
            OrderType orderType = payRecordService.getOrderType(p.getOrderNo());
            Date upTime = p.getUpdatedAt();
            if (orderType == OrderType.RECHARGE) {    //充值
                czCount += 1;
                recharge.add(p.getAmount());
            } else if (orderType == OrderType.ONLINE) {   //订单
                mnCount += 1;
                orderMoney.add(p.getAmount());
                useBalance.add(p.getBalance()).add(p.getPresent());
            } else if (orderType == OrderType.REFUND) {  //还款
                flCount += 1;
            }
            if(ob!=null && oe!=null) {
                if (upTime.after(ob) && upTime.before(oe) && orderType == OrderType.RECHARGE) czCountAt += 1;
                if (upTime.after(ob) && upTime.before(oe) && orderType == OrderType.ONLINE) mnCountAt += 1;
                if (upTime.after(ob) && upTime.before(oe) && orderType == OrderType.REFUND) flCountAt += 1;
            }else{
                czCountAt = czCount;
                mnCountAt = mnCount;
                flCountAt = flCount;
            }
        }
        data.put("czCount", czCount);
        data.put("mnCount", mnCount);
        data.put("flCount", flCount);
        data.put("mnCountAt", mnCountAt);
        data.put("czCountAt", czCountAt);
        data.put("flCountAt", flCountAt);
        
        data.put("useBalance", useBalance);
        data.put("recharge", recharge);
        data.put("orderMoney", orderMoney);
        data.put("couponMoney", couponMoney);
        
        return data;

    }


    public OrderType getOrderType(String billNo) throws Exception {
        if (StringUtils.startsWithIgnoreCase(billNo, Constants.RECHARGE_ORDER_PREFIX)) {
            return OrderType.RECHARGE;
        } else if (StringUtils.startsWithIgnoreCase(billNo, Constants.ONLINE_ORDER_PREFIX)) {
            return OrderType.ONLINE;
        } else if (StringUtils.startsWithIgnoreCase(billNo, Constants.REFUND_ORDER_PREFIX)) {
            return OrderType.REFUND;
        } else if (StringUtils.startsWithIgnoreCase(billNo, Constants.FREEDOM_ORDER_PREFIX)) {
            return OrderType.FREEDOM;
        } else {
            throw new Exception("不支持的订单类型！billNo=" + billNo);
        }
    }

    public String getOrderPrefixByOrderType(OrderType type) throws Exception {
        if(type == OrderType.RECHARGE){
            return Constants.RECHARGE_ORDER_PREFIX;
        }else if(type == OrderType.ONLINE){
            return Constants.ONLINE_ORDER_PREFIX;
        }else if(type == OrderType.REFUND){
            return Constants.REFUND_ORDER_PREFIX;
        }else if(type == OrderType.FREEDOM){
            return Constants.FREEDOM_ORDER_PREFIX;
        }else {
            throw new Exception("不支持的订单类型!");
        }
    }

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserQuotaService service = ac.getBean(UserQuotaService.class);
        String fuck = "368,373,392,394,423,425,430,451,452,491,513,522,537,537,544,549,555,577,600,613,617,619,637,682,733,1266,1787,2164,2182,2191,2192,2193,2194,2197,2199,2214,2217,2221,2243,2253,2254,2264,2265,2266,2269,2270,2271,2272,2273,2274,2278,2279,2287,2291,2292,2299,2366,2371,2527,2798,2804,2815,2821,2828,2839,2840,2840,2930,2930,2936,2950,2951,2952,2952,2957,2964,2966,2973,2992,2995,2995,3029,3044,3050,3050,3053,3053,3053,3073,3073,3074,3075,3084,3085,3091,3134,3168,3214,3225,3244,3486,3532,3598,3622,3686";
        for(int i = 0;i<fuck.split(",").length;i++){
            UserQuota userQuota = service.queryUserQuota(Long.parseLong(fuck.split(",")[i]));
            if(userQuota == null)System.out.println(fuck.split(",")[i]);
        }

    }

    /**
     * 充值订单 ...
     * @param model
     * @param ob
     * @param oe
     * @param page
     * @param type
     * @param mobile
     * @param uname
     * @throws Exception
     */
    public void queryFinanceBills(Model model, String ob, String oe, int page, int type, String mobile, String uname, String orderNo) throws Exception {

        Integer start = ((page - 1) * PageSize);

        Date b =null, e = null;

        SimpleDateFormat fmt =  new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotBlank(ob)&&StringUtils.isNotBlank(oe)) {
            b = fmt.parse(ob);
            e = fmt.parse(oe);
        }

        List<Long> uids = userService.queryUidsByNameOrMobile(mobile, uname);

        PayRecordExample example = new PayRecordExample();
        PayRecordExample.Criteria criteria = example.createCriteria();

        if(b != null && e != null){
            criteria.andUpdatedAtBetween(b , e);
        }
        if(uids.size() >0){
            criteria.andUidIn(uids);
        }
        if(StringUtils.isNotBlank(orderNo)){
            criteria.andOrderNoLike(orderNo+"%");
        }
        criteria.andStatusEqualTo(PayStatus.PAID.getValue());
        criteria.andOrderNoLike("cz%");

        example.setSize(PageSize);
        example.setStart(start);
        example.setOrderByClause("updated_at desc");

        List<PayRecord> pays = payRecordService.mapper.selectByExample(example);
        List<FinancePay> pas = createFinancePayByPays(pays);

        model.addAttribute("pays", pas);
        model.addAttribute("page", page);

        model.addAttribute("ob", b);
        model.addAttribute("oe", e);
        model.addAttribute("type", type);

    }

    public List<FinancePay> createFinancePayByPays(List<PayRecord> pays) {
        List<FinancePay> data = Lists.newArrayList();

        for(PayRecord p : pays){
            FinancePay app = new FinancePay();
            User user = userService.queryUser(p.getUid());
            UserQuota quota = userQuotaService.queryUserQuota(p.getUid());


            app.setId(p.getId());
            app.setUid(p.getUid());
            app.setOrderNo(p.getOrderNo());
            app.setUsername(quota.getRealname());
            app.setMobile(user.getMobile());
            app.setAmount(p.getAmount());
            app.setStatus(p.getStatus());
            app.setCallbackAt(p.getCallbackAt());


            data.add(app);
        }

        return data;

    }

    @Transactional
    public OrderInfo updateOrderPriceByService(String orderNo, float addPrice, long sysUid) throws Exception {
        OrderInfo info = mapper.findByOrderNo(orderNo);
        if(info==null){
            return null;
        }

        int type = 0;

        BigDecimal price = BigDecimal.valueOf(addPrice);
        if(info.getStatus() == OrderStatus.BOOK_OK.getValue()){
            type = 1;
        }else if(info.getStatus() == OrderStatus.PAY_OK.getValue()){
            price = info.getPrice().add(price);
            type =2;
        }


        //记录操作记录

        Map<String, Object> operation = Maps.newHashMap();
        Map<String,Object> parameters = Maps.newHashMap();
        parameters.put("orderNo",orderNo);
        parameters.put("uid", info.getUid());
        parameters.put("type",type);
        parameters.put("sysUid",UserHolder.getUserId());
        parameters.put("order",info);

        operation.put("parameters",parameters);
        operation.put("msg","修改订单价格...");

        String context = JSONUtil.writeToJson(operation);

        //记录操作记录
        SysOperationRecord record = sysOperationService.saveToData(info.getUid(), SysOperationType.UPDATE_ORDER_PRICE, context);


        long result = mapper.updateOrderPrice(orderNo, price, "");
        if(result > 0){
            info = mapper.findByOrderNo(orderNo);
        }else {
            info = null;
        }

        return info;
    }
}






