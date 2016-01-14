package com.mfq.admin.web.controllers;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.admin.web.bean.Gender;
import com.mfq.admin.web.bean.Nurse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mfq.admin.web.services.UserQuotaService;
import com.mfq.admin.web.services.UserService;
import com.mfq.admin.web.utils.JSONUtil;
import com.mfq.admin.web.utils.POIExcelUtil;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @Resource
    UserService service;
    @Resource
    OrderController orderController;
    @Resource
    UserQuotaService quotaService;
    
    @RequestMapping(value = "/user/list/", method = { RequestMethod.GET })
    public String userList(HttpServletRequest request,
    		@RequestParam(value = "mobile", defaultValue="", required = true) String mobile,
    		@RequestParam(value = "page", defaultValue="1", required = true) long page,
    		
    		@RequestParam(value = "username",defaultValue="")String username,
    		@RequestParam(value = "realname",defaultValue="") String realname,
    		@RequestParam(value = "phone",defaultValue="") String phone,
    		@RequestParam(value = "inviteId",defaultValue="") String inviteId,
    		@RequestParam(value = "alipay",defaultValue="") String alipay,
    		@RequestParam(value = "isReal",defaultValue="") String isReal,
    		@RequestParam(value = "fromtime",defaultValue="") String fromtime,
    		@RequestParam(value = "totime",defaultValue="") String totime,
    		Model model) {
        String pagestr = request.getParameter("page");
        String [] mobiles = mobile.split(",");
        if (StringUtils.isNotBlank(pagestr)) {
            page = Long.parseLong(pagestr);
        }
        
        service.queryUsersByPage(model, mobiles, page,username,realname,phone,inviteId,alipay,isReal,fromtime,totime);
        return "/user/users";
    }
    
    /**
     * 链接到实名认证页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/certify/")
    public String certify(Model model){
    	return "/user/certify";
    }
    
    /**
     * 实名验证的操作页面
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/certify/check/")
    public String certifyCheck(Model model,
    		@RequestParam("uid")String uid
    		){
    	Map<String,Object> user = quotaService.queryCertifyQuota(Long.parseLong(uid));
    	model.addAttribute("user",user);
    	return "/user/check_certify";
    }
    
    
    /**
     * 拦截所有ajax请求，并分发到相应的method中
     * @param method
     * @return 
     */
    
    @RequestMapping(value = "/ajax")
    public @ResponseBody String ajax(
    		@RequestParam("method")String method,HttpServletRequest request
    		) throws Exception{
    	
    	if(method.equals("certifyData")){
    		return certifyData(request);
    	}
    	else if(method.equals("certifyStatus")){
    		return certifyStatus(request);
    	}
    	else if(method.equals("updateNurse")){
    		return updateNurse(request);
    	}
    	else if(method.equals("deleteNurse")){
    		return deleteNurse(request);
    	}else if(method.equals("addNurse")){
    		return addNurse(request);
    	}
    	else if(method.equals("ajaxFinanceList")){
    		return orderController.ajaxFinanceList(request);
    	}
    
    
    	return null;
    }
    
    //ajax 方法 更新护士
    public String updateNurse(HttpServletRequest request){
		String phone = null;
		String address = null;
		Integer nurseNumber = null;
		String idCard = null;
		try {
			String id = request.getParameter("id");
			if(id == null){
				throw new Exception("必须要有用户id");
			}
			phone = request.getParameter("phone");
			address = request.getParameter("address");
			idCard = request.getParameter("idCard");
			nurseNumber = Integer.parseInt(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Nurse nurse = new Nurse(nurseNumber, null, phone, null, address, idCard);
    	return service.updateNurse(nurse).toString();
    }
    
    //ajax 方法 删除护士
    public String deleteNurse(HttpServletRequest request){
    	String id = request.getParameter("id");
    	System.out.println(id);
    	if(id==null) return null;
    	Integer nurseNumber = Integer.parseInt(id);
    	return service.deleteNurse(nurseNumber).toString();
    }
    
    //ajax 方法 添加护士
    public String addNurse(HttpServletRequest request) throws Exception{
    	String name = request.getParameter("name");
    	String gender = request.getParameter("gender");
    	String phone = request.getParameter("phone");
    	String idCard = request.getParameter("idCard");
    	String address = request.getParameter("address");
    	Integer gen = null;
    	Nurse nurse = null;
		try {
			if(name==null||gender==null||phone==null||idCard==null||address==null)return null;
			gen = Integer.parseInt(gender);
			Gender GenderR = Gender.fromValue(gen);
			nurse = new Nurse(null, name, phone, GenderR, address, idCard);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return service.addNurse(nurse).toString();
    }
    
    //修改面签状态和备注
    public String certifyStatus(HttpServletRequest request){
    	
    	long uid = 0L;
    	String remark = null;
    	Integer status = null;
    	String reqUid = request.getParameter("uid");
    	String reqStatus = request.getParameter("status");
    	if(reqUid==null || reqStatus==null){
    		return null;
    	}
    	uid = Long.parseLong(reqUid);
    	remark = request.getParameter("remark");
    	status = Integer.parseInt(reqStatus);
    	Integer result = quotaService.updateAuthStatus(uid, remark, status);
    	return result.toString();
    }
    
    /**
     * 获取实名认证数据
     * @param request
     * @return
     */
    public String certifyData(HttpServletRequest request)
    {
    	Integer page = 1;
		String type = "unsee";
		String uid = null;
		String phone = null;
		String cardid = null;
		String applytimefrom = null;
		String applytimeto = null;
		String checktimefrom = null;
		String checktimeto = null;
		
		if(request.getParameter("page")!=null)page = Integer.parseInt(request.getParameter("page"));
		type = request.getParameter("type");
		uid = request.getParameter("uid");
		phone = request.getParameter("phone");
		cardid = request.getParameter("cardid");
		applytimefrom = request.getParameter("applytimefrom");
		applytimeto = request.getParameter("applytimeto");
		checktimefrom = request.getParameter("checktimefrom");
		checktimeto = request.getParameter("checktimeto");
		
    	
    	Map<String,Object> list = service.certifyData(page, type, uid, phone, cardid, applytimefrom, applytimeto, checktimefrom, checktimeto);
    	return JSONUtil.successResultJson(list);
    }
    
    
    
    
    @RequestMapping(value = "/user/upload/excel/", method = { RequestMethod.POST })
    @ResponseBody
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value = "file") MultipartFile file,
    		Model model){
    	
    	try {
    		String fileName= file.getOriginalFilename();
    		if(!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")){
    			return JSONUtil.toJson(1001, "文件格式不正确！！", null);
    		}
    		
            if (!file.isEmpty()) {
                File tmpFile = new File("E:/tmp/" + fileName);
                file.transferTo(tmpFile);
                resolveExcel(tmpFile);
            } else { // 判断是否需要更新img
            	return JSONUtil.toJson(1002, "文件空！！", null);
            }
    	}catch(Exception e){
    		logger.error("UserCpntroller upload is error {}", e);
    	}

    	return JSONUtil.successResultJson();
    }
    
    public void resolveExcel(File file) throws Exception{
    	try{
    		POIExcelUtil.readExcel(file);
    	}catch(Exception e){
    		throw new Exception("Excel解析错误");
    	}
    }
    
    /**
     * 用户列表的用户详情页
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/user/list/detail/")
    public String userDetail(Model model,
    		@RequestParam(value = "uid" ,defaultValue = "0")String uid){
    	long userid = Long.parseLong(uid);
    	Map<String,Object> data = service.queryUserDetail(userid);
    	model.addAttribute("user",data);
    	return "/user/users_detail";
    }
    
    /**
     * 面签管理页面
     * @param model
     * @return
     */
    @RequestMapping("/user/interview/")
    public String interview(Model model){
    	
    	return "/user/interview";
    }
    
    
    /**
     * 面签管理详情页
     * @return
     */
    @RequestMapping("/user/interview/detail/")
    public String interviewDetail(Model model,
    							@RequestParam("uid")String uid ){
    	if(uid==null || uid.length()<=0)return null;
    	Map<String,Object> data = service.queryInteviewUserDetail(Long.parseLong(uid));
    	model.addAttribute("user",data);
    	return "/user/interview_detail";
    }
    
    
    /**
     * 陪护人员管理
     * @param model
     * @return
     */
    @RequestMapping("/user/nurse/")
    public String nurse(Model model){
    	List<Nurse> nurses = service.getAllNurse(); 
    	model.addAttribute("nurses",nurses);
    	return "/user/nurse";
    }
    
    @RequestMapping("/user/nurse/edit/")
    public String nurseEdit(Model model,@RequestParam("id")Integer id){
    	Nurse nurse = service.getNurseById(id);
    	model.addAttribute("nurse",nurse);
    	return "/user/nurse_edit";
    }

	public static void main(String[] args) {
		System.out.println("yysssssyyy");
	}


    

}















