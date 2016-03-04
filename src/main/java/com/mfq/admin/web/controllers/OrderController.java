package com.mfq.admin.web.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.constants.OrderType;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mfq.admin.web.constants.OrderStatus;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.services.FinanceBillService;
import com.mfq.admin.web.services.OrderService;
import com.mfq.admin.web.services.PayRecordService;
import com.mfq.admin.web.services.UserQuotaService;
import com.mfq.admin.web.services.UserService;
import com.mfq.admin.web.utils.DateUtil;
import com.mfq.admin.web.utils.JSONUtil;
import org.springframework.web.bind.annotation.ResponseBody;

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
            String date = DateUtil.formatShort(DateUtil.addDay(new Date(), -1));
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


	/**
	 * 财务对账用
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/order/budget/data","/order/budget/data/"} , method = {RequestMethod.GET,RequestMethod.POST})
	public  String budgetData(Model model, HttpServletRequest request,
					  @RequestParam(value = "ob", defaultValue = "") String ob,
					  @RequestParam(value = "oe", defaultValue = "") String oe,
					  @RequestParam(value = "page", defaultValue = "1")int page,
					  @RequestParam(value = "orderNo", defaultValue = "") String orderNo,
							  @RequestParam(value = "code", defaultValue = "") String code,
					  @RequestParam(value = "hid", defaultValue="0")int hid,
					  @RequestParam(value = "pname", defaultValue="")String pname,
					  @RequestParam(value = "type", defaultValue="0")int type,
					  @RequestParam(value = "mobile", defaultValue = "")String mobile,
					  @RequestParam(value = "status", defaultValue = "0")int status,
					  @RequestParam(value = "uname", defaultValue = "")String uname){

		String ret = "order/order_budget_data";
		try {
			String c = request.getParameter("c");
			String p = StringUtils.stripToEmpty(request.getParameter("p"));
			if("t".equals(p)){
				page = 1;
			}
			if(c.equals("1")){
				model.addAttribute("c",1);
				orderService.queryFinanceOrders(model, ob, oe, page, hid, pname, type, mobile, uname, status, orderNo,code);
				ret = "order/budget/order_data";
			}else if(c.equals("2")){
				model.addAttribute("c",2);
				orderService.queryFinanceBills(model, ob, oe, page, type, mobile, uname, orderNo);
				ret = "order/budget/pay_data";
			}

			model.addAttribute("ob",ob);
			model.addAttribute("oe",oe);
			model.addAttribute("page",page);
			model.addAttribute("orderNo",orderNo);
			model.addAttribute("code",code);
			model.addAttribute("hid",hid);
			model.addAttribute("pname",pname);
			model.addAttribute("type",type);
			model.addAttribute("mobile",mobile);
			model.addAttribute("status",status);
			model.addAttribute("uname",uname);
			System.out.print("......");
		}catch (Exception e){
			model.addAttribute("msg","系统错误...");
			logger.error("order data is error {}", e);
		}
		logger.info("order data is ret {}",ret);
		return ret;
	}

	/**
	 * 财务对账用
	 * @param model
     * @return
     */
	@RequestMapping(value = {"/order/budget","/order/budget/"} , method = RequestMethod.GET)
	public String budgetRe(Model model,
						    @RequestParam(value = "ob", defaultValue = "") String ob,
							@RequestParam(value = "oe", defaultValue = "") String oe,
							@RequestParam(value = "page", defaultValue = "1")int page,
							@RequestParam(value = "hid", defaultValue="0")int hid,
							@RequestParam(value = "pname", defaultValue="")String pname,
							@RequestParam(value = "type", defaultValue="0")int type,
							@RequestParam(value = "mobile", defaultValue = "")String mobile,
							@RequestParam(value = "status", defaultValue = "0")int status,
							@RequestParam(value = "uname", defaultValue = "")String uname){
		String ret = "";
		try {


			return "order/order_budget";
		}catch (Exception e){
			logger.error("order budget is error {}",e);
		}
		return "order/order_budget";
	}

	@RequestMapping(value = "/order/budgetp/", method = {RequestMethod.GET, RequestMethod.POST})
	public String budget2(Model model,
						 @RequestParam(value = "ob", defaultValue = "") String ob,
						 @RequestParam(value = "oe", defaultValue = "") String oe,
						 @RequestParam(value = "page", defaultValue = "1")int page,
						 @RequestParam(value = "hid", defaultValue="0")int hid,
						 @RequestParam(value = "pname", defaultValue="")String pname,
						 @RequestParam(value = "type", defaultValue="0")int type){
		try {
			orderService.queryFinanceOrders(model, ob, oe, page, hid, pname, type, "", "", 0, "","");
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


	/**
	 * 首先通过orderNo查出所有对应账单
	 * 通过账单号查到流水信息
	 * @param model
	 * @param orderNo
	 * @return
     */
    @RequestMapping("/order/finance/detail/")
    public String financeDetail(Model model,@RequestParam(value="order",defaultValue="")String orderNo) throws Exception{
    	if(StringUtils.isBlank(orderNo) || OrderType.ONLINE != payService.getOrderType(orderNo)) throw new Exception("订单号格式错误");

		// 1,查出所有对应账单
		List<FinanceBill> finances = financeService.selectByOrderNo(orderNo);


    	long uid = finances.get(0).getUid();
    	UserQuota quota = userQuotaService.queryUserQuota(uid);
    	User user = userService.queryUser(uid);
    	OrderInfo orderinfo = orderService.findByOrderNo(orderNo);

    	List<PayRecord> payRecords = payService.selectByOrderNo(orderinfo.getOrderNo());
    	model.addAttribute("finance",finances);
    	model.addAttribute("quota",quota);
    	model.addAttribute("user",user);
    	model.addAttribute("orderinfo",orderinfo);
    	model.addAttribute("payRecords",payRecords);
		//还有一个流水对应的还款期数 应该是通过这个这条流水对应的金额与每条账单之比
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

    	return JSONUtil.successResultJson(list);
    }
    
   

}










