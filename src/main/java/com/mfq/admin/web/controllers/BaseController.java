
package com.mfq.admin.web.controllers;

import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.google.common.collect.Maps;
import com.mfq.admin.web.constants.AdminConstants;
import com.mfq.admin.web.dao.OperationLogMapper;
import com.mfq.admin.web.helper.SignHelper;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.utils.HttpUtil;
import com.mfq.admin.web.utils.JSONUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 基础控制类
 * 
 */
public class BaseController {

    protected String[] formatDate = new String[]{"yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd hh:mm:ss", "yyyy-MM-dd hh:mm"};

    protected final String APPLICATION_JSON_MIME_TYPE = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    @Resource
    protected OperationLogMapper operationLogDao;
    
    @Resource
    protected FreeMarkerConfig freeMarkerConfigurer;
    
    private static final AtomicBoolean init = new AtomicBoolean(false);

    Logger logger = LoggerFactory.getLogger(getClass());

    public String currentUser() {
        return UserHolder.currentUser();
    }

    protected Map<String, Object> getResultMap() {
        return new LinkedHashMap<String, Object>();
    }

    protected String toJson(int code, String msg) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", code);
        map.put("msg", msg);
        return JSONUtil.writeToJson(map);
    }

    protected String toJson(int code, String msg, Object data) {
        Map<String, Object> result = getResultMap();
        result.put("code", code);
        result.put("msg", msg);
        if (data != null) {
            result.put("data", data);
        }

        return JSONUtil.writeToJson(result);
    }

    protected String getResultJson(int code, String msg) {

        return toJson(code, msg, null);
    }

    protected String getSuccessResultJson() {
        return toJson(AdminConstants.SUCCESS, "success", null);
    }

    protected String getSuccessResultJson(Map<String, ?> data) {
        return toJson(AdminConstants.SUCCESS, "成功", data);
    }

    protected void opLog(String type, String desc) {
        opLog(currentUser(), type, desc, UserHolder.currentIpAddress());
    }
    /** opLog2("delete_user_sign", "删除用户标识：%s => %s", uid, sign); */
    protected void opLog2(String type, String desc, Object... args) {
        opLog(currentUser(), type, desc, UserHolder.currentIpAddress(), args);
    }

    protected void opLog(String user, String type, String desc, String ip) {
        opLog(user, type, desc, ip, new Object[0]);
    }

    protected void opLog(String user, String type, String desc, String ip, Object... args) {
        String message = args == null || args.length == 0 ? desc : String.format(desc, args);
        type = StringUtils.substring(type, 0, 20);
        message = StringUtils.substring(message, 0, 8100);
        operationLogDao.addLog(user, type, message, ip);
    }

    @PostConstruct
    private void init() {
        if (init.compareAndSet(false, true)) {
            int delay = Boolean.getBoolean("product") ? Integer.MAX_VALUE : 0;
            freeMarkerConfigurer.getConfiguration().setTemplateUpdateDelay(delay);
            try {
                //初始化的时候 加载一个统一的im相关的js css
                //freeMarkerConfigurer.getConfiguration().setSharedVariable("imJs", tupleDao.query("im-js"));
                //freeMarkerConfigurer.getConfiguration().setSharedVariable("imCss", tupleDao.query("im-css"));
            } catch (Exception e) {
                //nothing
            }
        }
    }

    protected String getFragment(String ftlName, Map<String, Object> data) throws Exception {

        Configuration cfg = freeMarkerConfigurer.getConfiguration();
        Template template = cfg.getTemplate(ftlName);
        StringWriter out = new StringWriter();
        /* 模板数据 */
        template.process(data, out);
        out.flush();
        return out.toString();
    }

    //fixme 提取为统一的封装
    /**
     * 发送短信
     * 
     * @param mobile
     *            手机号
     * @param message
     *            短信内容
     * @return 发送的结果（code/msg结果字符串)
     * @throws Exception
     *             任何网络异常
     */
    protected String sendSms(String mobile, String message) throws Exception {
        Map<String, Object> map = Maps.newHashMap();
        map.put("mobile", mobile);
        map.put("message", message);
        String sign = SignHelper.makeSign(map);
        map.put("sign", sign);
        String body = JSONUtil.writeToJson(map);
        return HttpUtil.post(AdminConstants.WEB_SITE+"/sms/send/", body, true);
    }

    protected String send404() {
        return "forward:/error/404/";
    }

    protected String sendErrorMsg(Model model, String msg) {
        model.addAttribute("msg", msg);
        return "error/index";
    }
}