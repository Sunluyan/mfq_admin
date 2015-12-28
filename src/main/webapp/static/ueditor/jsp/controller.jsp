<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.baidu.ueditor.ActionEnter,
	org.slf4j.Logger,
	org.slf4j.LoggerFactory,
	com.mfq.admin.web.utils.RequestUtils,
	com.mfq.admin.web.services.QiniuManipulater"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
    Logger logger = LoggerFactory.getLogger("UEDITOR_JSP_LOGGER");

    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");

    String rootPath = application.getRealPath("/");

    logger.info("operation_req_params_is:{}",
            RequestUtils.formatRequestParameters(request));

    String resp = new ActionEnter(request, rootPath).exec();

    resp = QiniuManipulater.ueditorPlusQiniu(request, resp, rootPath);

    logger.info("operation_resp_info_is:{}", resp);

    out.write(resp);
%>