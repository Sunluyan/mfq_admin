package com.mfq.admin.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mfq.admin.web.constants.OrderStatus;
import com.mfq.admin.web.models.PayRecord;
import com.mfq.admin.web.models.order.FinanceBill;
import com.mfq.admin.web.models.order.OrderInfo;
import com.mfq.admin.web.models.user.User;
import com.mfq.admin.web.models.user.UserQuota;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.services.FinanceBillService;
import com.mfq.admin.web.services.OrderService;
import com.mfq.admin.web.services.PayRecordService;
import com.mfq.admin.web.services.UserQuotaService;
import com.mfq.admin.web.services.UserService;
import com.mfq.admin.web.utils.DateUtil;
import com.mfq.admin.web.utils.JSONUtil;

/**
 * Order模块
 */
@Controller
public class OrderController extends BaseController {

    @Resource
    OrderService orderService;
    @Resource
    UserService userService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    FinanceBillService financeService;
    @Resource
    PayRecordService payService;
    
    /**
     * 订单管理
     * 
     * @param page
     * @param orderNo
     * @param status
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/list/", method = RequestMethod.GET)
    public String orderList(
            @RequestParam(defaultValue = "1", required = false) Long page,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "status", defaultValue = "100") int status,
            @RequestParam(value = "mobile", defaultValue = "") String mobile,
            @RequestParam(value = "securityCode", defaultValue = "") String securityCode,
            @RequestParam(value = "ob", required = false) String ob,
            @RequestParam(value = "oe", required = false) String oe,
            Model model) {
    	
        if (StringUtils.isBlank(ob)) {
            String date = DateUtil.formatShort(DateUtil.addDay(new Date(), -365));
            Date d = DateUtil.convertShort(date);
            ob = DateUtil.formatLong(d);
        }

        if (StringUtils.isBlank(oe)) {
            oe = DateUtil.formatLong(new Date());
        }

        orderService.findByPage(orderNo, mobile, securityCode, status, page, ob,
                oe, model);

        return "order/order_list";
    }

    @RequestMapping(value = "/order/consult/", method = RequestMethod.GET)
    public String orderConsult(
            @RequestParam(defaultValue = "1", required = false) Long page,
            @RequestParam(value = "orderNo", required = false) String orderNo,
            @RequestParam(value = "mobile", defaultValue = "") String mobile,
            @RequestParam(value = "securityCode", defaultValue = "") String securityCode,
            Model model) {

        orderService.findByPage(orderNo, mobile, securityCode,
                OrderStatus.NONE.getValue(), page, null, null, model);

        return "order/consult_list";
    }

    /**
     * 订单查看
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/view/", method = RequestMethod.GET)
    public String orderView(
            @RequestParam(defaultValue = "0", required = false) Long id,
            Model model) {

        try {
            OrderInfo order = orderService.findById(id);
            orderView(model, order);
            
        } catch (Exception e) {
            logger.error("Exception_ORDER_VIEW", e);
        }
        return "order/order_view";
    }
    
    
    @RequestMapping(value = "/order/view/", method = RequestMethod.POST)
    public String orderSubmit(
            @RequestParam(defaultValue = "", required = false) String orderNo,
            Model model) {
    	try{
            OrderInfo order = orderService.findByOrderNo(orderNo);
            
            long result = orderService.updateOrder(order.getId(), OrderStatus.PAY_OK.getValue(), OrderStatus.ORDER_OK.getValue());
            
            if(result > 0){
            	model.addAttribute("msg", "订单已使用。。。");
            	model.addAttribute("success", true);
            	order = orderService.findByOrderNo(orderNo);
            }else{
            	model.addAttribute("msg", "订单不满足条件或已完成。");
            	model.addAttribute("success", false);
            }
            orderView(model, order);
            
    	}catch (Exception e){
    		logger.error("Exception_Order_SubmitOrder", e);
    	}
    	return "order/order_view";
    }
    
    
	private void orderView(Model model, OrderInfo order) {
		User user = userService.queryUser(order.getUid());
		UserQuota userQuota = userQuotaService
		        .queryUserQuota(order.getUid());
		// hasright
		int hasright = 0;
		if (UserHolder.currentUserDetail().getSysUser().getRoleId() == 1
		        || UserHolder.currentUserDetail().getSysUser()
		                .getRoleId() == 6) { // 医院财务
		    if(OrderStatus.PAY_OK.getValue() == order.getStatus()){ // 且订单是支付成功的状态
		        hasright = 1;
		    }
		}
		
		model.addAttribute("hasright", hasright);
		model.addAttribute("mobile", user.getMobile());
		model.addAttribute("contact", userQuota.getContact());
		model.addAttribute("realname", userQuota.getRealname());
		String result= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userQuota.getCreatedAt());
		model.addAttribute("createdAt", result);
		model.addAttribute("order", order);
		model.addAttribute("statusname",
		        OrderStatus.fromValue(order.getStatus()).getName());
	}


	@RequestMapping(value = {"/order/finance","/order/finance/"} , method = RequestMethod.GET)
	public String orderL(
			@RequestParam(defaultValue = "1", required = false) Long page,
			@RequestParam(value = "orderNo", defaultValue = "") String orderNo,
			@RequestParam(value = "mobile", defaultValue = "") String mobile,
			@RequestParam(value = "status", defaultValue = "100") int status,
			@RequestParam(value = "hospitalName", defaultValue = "") String hospitalName,
			@RequestParam(value = "payType", defaultValue="-1") int payType,
			@RequestParam(value = "payApi", defaultValue = "") String payApi,
			@RequestParam(value = "ob", required = false) String ob,
			@RequestParam(value = "oe", required = false) String oe,
			Model model) {
		try{
			//中文编码
    		hospitalName=new String(hospitalName.getBytes("ISO-8859-1"),"UTF-8");

			if (StringUtils.isBlank(ob)) {
				String date = DateUtil.formatShort(DateUtil.addDay(new Date(), -7));
				Date d = DateUtil.convertShort(date);
				ob = DateUtil.formatLong(d);
			}

			if (StringUtils.isBlank(oe)) {
				oe = DateUtil.formatLong(new Date());
			}
			orderService.bindFinanceMode(model, page, orderNo, mobile, hospitalName, payType, payApi, status, ob, oe);
		}catch(Exception e){

		}
		return "order/order_finance";
	}

	@RequestMapping(value = "/order/budget/", method = RequestMethod.GET)
	public String budget(Model model,
						 @RequestParam(value = "ob", defaultValue = "") String ob,
						 @RequestParam(value = "oe", defaultValue = "") String oe,
						 @RequestParam(value = "page", defaultValue = "1")int page){
		try {
			orderService.getFinanceUserByTime(model, ob, oe, page);
			return "order/order_budget";
		}catch (Exception e){
			logger.error("order budget is error {}",e);
		}
		return "order/order_budget";
	}

    @RequestMapping(value = "/order/edit/", method = RequestMethod.GET)
    public String orderEdit(
            @RequestParam(defaultValue = "0", required = false) Long id,
            Model model) {

//         orderService.buildEditModel(id, model);

        return "order/order_edit";
    }
    
    
    
    /**
     * 前往保单管理
     * @param model
     * @return
     */
    @RequestMapping("/order/ins/")
    public String order_ins(Model model){
    	return "/order/order_ins";
    }
    
    /**
     * 前往保单详情
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/order/ins/detail/")
    public String order_ins_detail(Model model,String id){
    	return "/order/order_ins_view";
    }
    
    /**
     * 前往分期管理
     * @return
     */
    @RequestMapping("/order/finance/list/")
    public String financeList(Model model){
    	return "/order/finance_list";
    }

    @RequestMapping("/order/finance/detail/")
    public String financeDetail(Model model,@RequestParam(value="id",defaultValue="0")long id){
    	if(id==0)return null;
    	FinanceBill finance = financeService.selectByPrimaryKey(id);
    	long uid = finance.getUid();
    	UserQuota quota = userQuotaService.queryUserQuota(uid);
    	User user = userService.queryUser(uid);
    	OrderInfo orderinfo = orderService.findByOrderNo(finance.getOrderNo());
    	List<PayRecord> payRecords = payService.selectByOrderNo(finance.getOrderNo());
    	
    	model.addAttribute("finance",finance);
    	model.addAttribute("quota",quota);
    	model.addAttribute("user",user);
    	model.addAttribute("orderinfo",orderinfo);
    	model.addAttribute("payRecords",payRecords);
    	
    	return "/order/finance_detail";
    }
    /**
     * Integer page,Integer size,long uid,String realname,
			String phone,String idcard,String applytimefrom,String applytimeto,Integer type
     * @param request
     * @return
     */
    public String ajaxFinanceList(HttpServletRequest request){
    	Integer page = 1;
    	Integer size = 50;
    	long uid = 0;
    	String realname = null;
    	String phone = null;
    	String idcard = null;
    	String applytimefrom = null;
    	String applytimeto = null;
    	Integer type = null;
    	String count = null;
    	
    	if(request.getParameter("page")!=null)page=Integer.parseInt(request.getParameter("page"));
    	if(request.getParameter("uid")!=null && !request.getParameter("uid").equals(""))uid=Long.parseLong(request.getParameter("uid"));
    	if(request.getParameter("realname")!=null)realname = request.getParameter("realname");
    	if(request.getParameter("phone")!=null)phone = request.getParameter("phone");
    	if(request.getParameter("idcard")!=null)idcard = request.getParameter("idcard");
    	if(request.getParameter("applytimefrom")!=null)applytimefrom = request.getParameter("applytimefrom");
    	if(request.getParameter("applytimeto")!=null)applytimeto = request.getParameter("applytimeto");
    	if(request.getParameter("type")!=null && !request.getParameter("type").equals("") )type=Integer.parseInt(request.getParameter("type"));
    	if(request.getParameter("count")!=null && !request.getParameter("count").equals("") )count=request.getParameter("type");
    	
    	List<Map<String,Object>> list = orderService.selectFinanceByPage(page, size, uid, realname, phone, idcard, applytimefrom, applytimeto, type,count);
    	for (Map<String, Object> map : list) {
			System.out.println(map);
		}
    	
    	return JSONUtil.successResultJson(list);
    }
    
   

}










