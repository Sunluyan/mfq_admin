package com.mfq.admin.web.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Maps;
import com.mfq.admin.web.bean.*;
import com.mfq.admin.web.cache.SysUserCache;
import com.mfq.admin.web.constants.AuthStatus;
import com.mfq.admin.web.constants.SysOperationType;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.services.*;
import com.mfq.admin.web.services.SysOperationService;
import com.mfq.admin.web.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Resource
    SysOperationService sysOperationService;
    @Resource
    ProductService productService;

    @RequestMapping(value = "/user/list/", method = {RequestMethod.GET})
    public String userList(HttpServletRequest request,
                           @RequestParam(value = "mobile", defaultValue = "", required = true) String mobile,
                           @RequestParam(value = "page", defaultValue = "1", required = true) long page,
                           @RequestParam(value = "username", defaultValue = "") String username,
                           @RequestParam(value = "realname", defaultValue = "") String realname,
                           @RequestParam(value = "phone", defaultValue = "") String phone,
                           @RequestParam(value = "inviteId", defaultValue = "") String inviteId,
                           @RequestParam(value = "alipay", defaultValue = "") String alipay,
                           @RequestParam(value = "isReal", defaultValue = "") String isReal,
                           @RequestParam(value = "fromtime", defaultValue = "") String fromtime,
                           @RequestParam(value = "totime", defaultValue = "") String totime,
                           Model model) {
        String pagestr = request.getParameter("page");
        String[] mobiles = mobile.split(",");
        if (StringUtils.isNotBlank(pagestr)) {
            page = Long.parseLong(pagestr);
        }

        service.queryUsersByPage(model, mobiles, page, username, realname, phone, inviteId, alipay, isReal, fromtime, totime);
        return "/user/users";
    }

    /**
     * 链接到实名认证页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/certify/")
    public String certify(Model model) {
        return "/user/certify";
    }

    /**
     * 实名验证的操作页面
     *
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/certify/check/")
    public String certifyCheck(Model model,
                               @RequestParam("uid") String uid
    ) {
        Map<String, Object> user = quotaService.queryCertifyQuota(Long.parseLong(uid));
        logger.info("user in UserController at certifyCheck() : {}", user.toString());
        model.addAttribute("user", user);
        return "/user/check_certify";
    }

    /**
     * 拦截所有ajax请求，并分发到相应的method中
     * @param method
     * @return
     */

    @RequestMapping(value = "/ajax")
    public
    @ResponseBody
    String ajax(@RequestParam("method") String method, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        if (method.equals("certifyData")) {
            return certifyData(request);
        } else if (method.equals("certifyStatus")) {
            return certifyStatus(request);
        } else if (method.equals("updateNurse")) {
            return updateNurse(request);
        } else if (method.equals("deleteNurse")) {
            return deleteNurse(request);
        } else if (method.equals("addNurse")) {
            return addNurse(request);
        } else if (method.equals("ajaxFinanceList")) {
            return orderController.ajaxFinanceList(request);
        } else if (method.equals("editUserFeedback")) {
            return editUserFeedback(request);
        } else if (method.equals("addRemark")) {
            return addRemark(request, response);
        } else if (method.equals("addInterviewRemark")) {
            return addInterviewRemark(request);
        } else if(method.equals("delInterview")){
            return delInterview(request);
        } else if(method.equals("interViewChek")){
            return interViewChek(request);
        }

        return null;
    }



    /**
     * 面签审核....
     * @param request
     * @return
     */
    public String interViewChek(HttpServletRequest request){
        String ret = "";
        try {
            long LoginUid = UserHolder.getUserId();
            if(LoginUid <1){
                return JSONUtil.toJson(1001,"请重新登录...",null);
            }

            String reason = StringUtils.stripToEmpty(request.getParameter("reason"));
            long uid = Long.parseLong(request.getParameter("uid"));
            int type = Integer.parseInt(request.getParameter("t"));

            Map<String, Object> operation = Maps.newHashMap();
            Map<String,Object> parameters = Maps.newHashMap();
            parameters.put("reason",reason);
            parameters.put("uid", uid);
            parameters.put("type",type);
            parameters.put("sysUid",LoginUid);

            operation.put("parameters",parameters);
            operation.put("msg","面签审核操作...");

            String context = JSONUtil.writeToJson(operation);

            //记录操作记录
            SysOperationRecord record = sysOperationService.saveToData(uid, SysOperationType.UPDATE_INTERVIEW_CHECK, context);

            if(record == null){
                logger.error("操作记录保存失败....面签审核 {}|{}|{}|{}", LoginUid, uid, reason, type );
            }

            if(type == 1){
                float fcredit = Float.parseFloat(reason);
                BigDecimal credit = BigDecimal.valueOf(fcredit);
                ret = quotaService.endowCredit(uid, LoginUid, credit);
            }else if(type == 2){
                ret = quotaService.refuseCredit(uid, LoginUid, reason);
            }
            ret = JSONUtil.successResultJson("提交成功....");

        }catch (Exception e){
            logger.error("interView check is error {}",e);
        }
        return ret;
    }

    /**
     * 添加用户面签资料备注
     *
     * @param request
     * @return
     */
    public String addInterviewRemark(HttpServletRequest request) {
        try {
            String uidStr = request.getParameter("uid");
            String desc = request.getParameter("desc");
            String remark = request.getParameter("remark");
            String idStr = request.getParameter("id");
            String img = request.getParameter("img");

            if(StringUtils.isBlank(idStr)){
                idStr = "0";
            }else if(StringUtils.isBlank(uidStr)){
                return JSONUtil.toJson(9983, "参数错误", null);
            }
            Long id = Long.parseLong(idStr);
            Long uid = Long.parseLong(uidStr);
            return service.addInterviewInfo(uid,id,desc,remark,img);

        } catch (Exception e) {
            logger.info("添加面签出错" + e);
            return JSONUtil.toJson(9983, e.getMessage(), null);
        }
    }

    public String delInterview(HttpServletRequest request){
        try{
            String id = request.getParameter("id");

            return service.delInterview(Long.parseLong(id));
        }catch(Exception e){
            logger.info("删除面签出错" + e);
            return JSONUtil.toJson(9983, e.getMessage(), null);
        }
    }

    /**
     * 添加用户备注,用户备注以&&&&分开每个,****分开每条
     * 添加完用户备注之后添加客服操作记录
     *
     * @param request
     * @return
     */
    private String addRemark(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uid = request.getParameter("uid");
            String data = request.getParameter("data");
            long time = new Date().getTime();
            SysPassport cookiePassport = CookieUtils.readPassport(request);
            String sysName = "";
            SysUser sysUser = SysUserCache.validateUser(cookiePassport, true);
            sysName = sysUser.getRealname();
            String newRemark = service.updateUserFeedbackRemark(Long.parseLong(uid), data, sysName, time);
            response.getWriter().append(newRemark);
        } catch (Exception e) {
            logger.info("添加备注出错" + e);
            return JSONUtil.toJson(9983, e.getMessage(), null);
        }
        return null;
    }

    /**
     * 修改或添加认证反馈或备注
     *
     * @param request
     * @return
     */
    private String editUserFeedback(HttpServletRequest request) {
        long uid = 0;
        String feedback ,feedback_type ,interest;
        try {
            uid = Long.parseLong(request.getParameter("uid"));
            if (uid == 0) {
                return null;
            }
            if (request.getParameter("feedback") != null) {
                feedback = request.getParameter("feedback");
                long count = service.updateUserFeedbackFeedback(uid, feedback);
                if (count != 1) {
                    return JSONUtil.toJson(9999, "插入或更新出错", null);
                } else {
                    return JSONUtil.successResultJson();
                }
            } else if (request.getParameter("feedback_type") != null) {
                feedback_type = request.getParameter("feedback_type");
                long count = service.updateUserFeedbackFeedbackType(uid, feedback_type);
                if (count != 1) {
                    return JSONUtil.toJson(9999, "插入或更新出错", null);
                } else {
                    return JSONUtil.successResultJson();
                }
            } else if(request.getParameter("interest") != null){
                interest = request.getParameter("interest");
                long count = service.updateUserFeedbackInterest(uid,interest);
                if (count != 1) {
                    return JSONUtil.toJson(9999, "插入或更新出错", null);
                } else {
                    return JSONUtil.successResultJson();
                }
            }

        } catch (Exception e) {
            return e.toString();
        }
        return null;
    }

    //ajax 方法 更新护士
    public String updateNurse(HttpServletRequest request) {
        String phone = null;
        String address = null;
        Integer nurseNumber = null;
        String idCard = null;
        try {
            String id = request.getParameter("id");
            if (id == null) {
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
    public String deleteNurse(HttpServletRequest request) {
        String id = request.getParameter("id");
        System.out.println(id);
        if (id == null) return null;
        Integer nurseNumber = Integer.parseInt(id);
        return service.deleteNurse(nurseNumber).toString();
    }

    //ajax 方法 添加护士
    public String addNurse(HttpServletRequest request) throws Exception {
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String idCard = request.getParameter("idCard");
        String address = request.getParameter("address");
        Integer gen = null;
        Nurse nurse = null;
        try {
            if (name == null || gender == null || phone == null || idCard == null || address == null) return null;
            gen = Integer.parseInt(gender);
            Gender GenderR = Gender.fromValue(gen);
            nurse = new Nurse(null, name, phone, GenderR, address, idCard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service.addNurse(nurse).toString();
    }

    //修改面签状态或备注
    public String certifyStatus(HttpServletRequest request) {

        long uid = 0L;
        String remark = null;
        Integer status = null;
        String reqUid = request.getParameter("uid");
        String reqStatus = request.getParameter("status");
        if (reqUid == null || reqStatus == null) {
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
     *
     * @param request
     * @return
     */
    public String certifyData(HttpServletRequest request) {
        Integer page = 1;
        String type = "unsee";
        String uid = null;
        String phone = null;
        String cardid = null;
        String applytimefrom = null;
        String applytimeto = null;
        String checktimefrom = null;
        String checktimeto = null;
        String feedbackType = null;

        if (request.getParameter("page") != null) page = Integer.parseInt(request.getParameter("page"));
        type = request.getParameter("type");
        uid = request.getParameter("uid");
        phone = request.getParameter("phone");
        cardid = request.getParameter("cardid");
        applytimefrom = request.getParameter("applytimefrom");
        applytimeto = request.getParameter("applytimeto");
        checktimefrom = request.getParameter("checktimefrom");
        checktimeto = request.getParameter("checktimeto");
        feedbackType = request.getParameter("feedbackType");

        Map<String, Object> list = service.certifyData(page, type, uid, phone, cardid, applytimefrom, applytimeto, checktimefrom, checktimeto, feedbackType);
        return JSONUtil.successResultJson(list);
    }


    @RequestMapping(value = "/user/upload/excel/", method = {RequestMethod.POST})
    @ResponseBody
    public String uploadExcel(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "file") MultipartFile file,
                              Model model) {

        try {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xls") && !fileName.endsWith(".xlsx")) {
                return JSONUtil.toJson(1001, "文件格式不正确！！", null);
            }

            if (!file.isEmpty()) {
                File tmpFile = new File("/tmp/" + fileName);
                file.transferTo(tmpFile);
                resolveExcel(tmpFile);
            } else { // 判断是否需要更新img
                return JSONUtil.toJson(1002, "文件空！！", null);
            }
        } catch (Exception e) {
            logger.error("UserCpntroller upload is error {}", e);
        }

        return JSONUtil.successResultJson();
    }

    public void resolveExcel(File file) throws Exception {
        try {
            POIExcelUtil.readExcel(file);
        } catch (Exception e) {
            throw new Exception("Excel解析错误");
        }
    }

    /**
     * 用户列表的用户详情页
     *
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/user/list/detail/")
    public String userDetail(Model model,
                             @RequestParam(value = "uid", defaultValue = "0") String uid) {
        long userid = Long.parseLong(uid);
        Map<String, Object> data = service.queryUserDetail(userid);
        model.addAttribute("user", data);
        return "/user/users_detail";
    }


    @RequestMapping("/user/budget/")
    public String BudgetDetail(Model model, @RequestParam(value = "uid", defaultValue = "0") long uid) {
        try {

            if (uid == 0) {
                return "order/users_budge";
            }
            Map<String, Object> data = service.queryUserDetail(uid);
            model.addAttribute("user", data);

            service.userDetailForBudget(uid, model);

        } catch (Exception e) {
            logger.error("user budget is error {}", e);
        }


        return "order/users_budget";
    }

    /**
     * 面签管理页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/interview/")
    public String interview(Model model) {

        return "/user/interview";
    }


    /**
     * 面签管理详情页
     *
     * @return
     */
    @RequestMapping("/user/interview/detail/")
    public String interviewDetail(Model model,
                                  @RequestParam("uid") String uid) {
        if (uid == null || uid.length() <= 0) return null;
        Map<String, Object> data = service.queryInteviewUserDetail(Long.parseLong(uid));
        model.addAttribute("user", data);

        model.addAttribute("authStatus", AuthStatus.AuthStatuses());
        return "/user/interview_detail";
    }


    /**
     * 陪护人员管理
     *
     * @param model
     * @return
     */
    @RequestMapping("/user/nurse/")
    public String nurse(Model model) {
        List<Nurse> nurses = service.getAllNurse();
        model.addAttribute("nurses", nurses);
        return "/user/nurse";
    }

    @RequestMapping("/user/nurse/edit/")
    public String nurseEdit(Model model, @RequestParam("id") Integer id) {
        Nurse nurse = service.getNurseById(id);
        model.addAttribute("nurse", nurse);
        return "/user/nurse_edit";
    }

    @RequestMapping("/user/interview/uploadimg/")
    public
    @ResponseBody
    String addInterviewInfo(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws Exception {
        String url = "";
        if (!file.isEmpty()) {
            File tmpFile = new File("/tmp/" + UUID.randomUUID().toString());
            file.transferTo(tmpFile);
            url = QiniuManipulater.qiniuUploadProdImg(tmpFile);
        }
        logger.info(url);
        return url;
    }


    @RequestMapping("/user/finance/")
    public String userDetails(Model model, HttpServletRequest request){

        try {
            long uid = Long.parseLong(request.getParameter("uid"));

            service.queryUserDetailAndOrderPay(model, uid);


        }catch (Exception e){
            logger.error(" user finance is error   {}",e);
        }

        return "/user/detail";

    }




}
