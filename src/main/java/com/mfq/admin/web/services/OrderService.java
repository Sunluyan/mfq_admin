package com.mfq.admin.web.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.admin.web.constants.OrderStatus;
import com.mfq.admin.web.constants.PayAPIType;
import com.mfq.admin.web.constants.PayType;
import com.mfq.admin.web.dao.FinanceBillMapper;
import com.mfq.admin.web.dao.OrderInfoMapper;
import com.mfq.admin.web.dao.UserMapper;
import com.mfq.admin.web.models.Product;
import com.mfq.admin.web.models.SysUser;
import com.mfq.admin.web.models.order.FinanceBill;
import com.mfq.admin.web.models.order.OrderInfo;
import com.mfq.admin.web.models.user.User;
import com.mfq.admin.web.models.user.UserQuota;
import com.mfq.admin.web.security.UserHolder;

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

    long PageSize = 30;
    public void findByPage(String orderNo, String mobile, String securityCode,
            int status, long page, String ob, String oe, Model model) {

        logger.info("order_choose_params:{}|{}|{}|{}|{}|{}|{}", orderNo, mobile,
                securityCode, status, page, ob, oe);
        if(orderNo==null){
        	orderNo="";
        }
        orderNo = orderNo.trim();
        mobile = mobile.trim();
        securityCode = securityCode.trim();
        
        if (page == 0) {
            page = 1;
        }
        long start = (page - 1) * PageSize;
        User user = userService.queryUserByMobile(mobile);
        long uid = user != null&&user.getUid() == null ? 0 : user.getUid();
        long size = mapper.findCount(orderNo, uid, securityCode,
                status, ob, oe, start, PageSize);
        // 订单列表
        SysUser LoginUser = sysUserService.querySysUser(UserHolder.getUserId());
        List<OrderInfo> orders = null;
        if(LoginUser.getHospitalId() > 0){
        	orders = mapper.findByPageByHospital(orderNo, uid,
                    securityCode, status, ob, oe, start, LoginUser.getHospitalId(),  PageSize);
        }else{
        	orders = mapper.findByPage(orderNo, uid,
                securityCode, status, ob, oe, start, PageSize);
        }
        
           
        
        Map<Long, String> productMap = getProdNames(orders);
        Map<Long, String> userMap = getUserNames(orders);
        Map<Integer, String> statusmap = Maps.newHashMap();
        for (OrderStatus os : OrderStatus.values()) {
            statusmap.put(os.getValue(), os.getName());
        }

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


    private Map<Long, String> getProdNames(List<OrderInfo> orders) {
        Map<Long, String> map = Maps.newHashMap();
        if (CollectionUtils.isEmpty(orders)) {
            return map;
        }
        List<Long> ids = Lists.newArrayList();
        for (OrderInfo order : orders) {
            ids.add(order.getPid());
        }
        List<Product> list = productService.findByIds(ids);
        for (Product p : list) {
            map.put(p.getId(), p.getName());
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
        if(orderNo==null){
        	orderNo="";
        }
        orderNo = orderNo.trim();
        mobile = mobile.trim();
        
        if (page == 0) {
            page = 1;
        }
        
        int [] status_s = new int []{status, 100, 100};
        if(status != 1 && status !=2 && status !=8){
        	status_s[0] = 1;
        	status_s[1] = 2;
        	status_s[2] = 8;
        }
        
        long start = (page - 1) * PageSize;

        long size = mapper.findCountByFinanceOrder(orderNo, mobile, hospitalName, payType,
        		payApi, status, ob, oe, start, PageSize);

        //PayType[] pays= PayType.values();
        // 订单列表
        
        List<Map<String,Object>> orders = mapper.findByFinancePage(orderNo,
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
    		@Param("size")Integer size,
    		@Param("uid")long uid,
    		@Param("realname")String realname,
    		@Param("phone")String phone,
    		@Param("idcard")String idcard,
    		@Param("endTime")String endTime,
    		@Param("applytimefrom")String applytimefrom,
    		@Param("applytimeto")String applytimeto,
    		@Param("type") String type);
	 * @param page
	 * @return
	 */
	public List<Map<String,Object>> selectFinanceByPage(Integer page,Integer size,long uid,String realname,
			String phone,String idcard,String applytimefrom,String applytimeto,Integer type,String count){
		Integer start = (page-1)*size;
		Date date = new Date();
		date = new Date(date.getTime()-1000*60*60*24*3);
		String endTime = null;
		if(type !=null && type == 0)endTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		
		
		List<FinanceBill> list = financeMapper.selectFinanceByPage(start, size, uid, realname, phone, idcard,
				endTime, applytimefrom, applytimeto, type,count);
		List<Map<String,Object>> result = new ArrayList<>();
		for (FinanceBill financeBill : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			
			User user = userService.queryUser(financeBill.getUid());
			UserQuota userQuota = userQuotaService.queryUserQuota(financeBill.getUid());
			OrderInfo orderInfo = findByOrderNo(financeBill.getOrderNo());
			
			map.put("realname", userQuota.getRealname());
			map.put("mobile", user.getMobile());
			map.put("user_type", userQuota.getUserType());
			map.put("due_at", financeBill.getDueAt());
			map.put("price", orderInfo.getPrice());
			map.put("period_pay", financeBill.getNewBalance());
			map.put("period", financeBill.getCurPeriod());
			map.put("all_period", financeBill.getAllPeriod());
			map.put("new_balance", financeBill.getNewBalance());
			map.put("bill_at", financeBill.getBillAt());
			map.put("id", financeBill.getId());
			map.put("uid", financeBill.getUid());
			map.put("pay_at", financeBill.getPayAt());
			result.add(map); 
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		ApplicationContext ac =  new ClassPathXmlApplicationContext("spring/spring.xml");
		FinanceBillMapper mapper = ac.getBean(FinanceBillMapper.class);
		List<FinanceBill> list = mapper.selectFinanceByPage(0, 50, 0, null, null, null, null, null, null, null, null);
		for (FinanceBill financeBill : list) {
			System.out.println(financeBill);
		}
	}
	
	
}






