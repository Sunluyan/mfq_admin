package com.mfq.admin.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mfq.admin.web.bean.SysPassport;
import com.mfq.admin.web.bean.SysUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.mfq.admin.web.cache.SysUserCache;
import com.mfq.admin.web.constants.AdminConstants;
import com.mfq.admin.web.security.UserDetail;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.utils.Base62;
import com.mfq.admin.web.utils.CookieUtils;
import com.mfq.admin.web.utils.DnsUtil;
import com.mfq.admin.web.utils.RequestUtils;
import com.mfq.admin.web.utils.UserTraceLogger;

public class UserTraceFilter implements Filter {

    private static final UserTraceLogger userTrace = UserTraceLogger
            .getLogger(UserTraceFilter.class);
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        long startTime = System.currentTimeMillis();

        UserHolder.setCurrentIpAddress(getIp(req));

        SysUser sysUser = null;
        SysPassport cookiePassport = CookieUtils.readPassport(req);
        try {
            sysUser = SysUserCache.validateUser(cookiePassport, true);
        } catch (Exception e) {
            userTrace.error("UserValidate PassportCookie Exception", e);
        }
        String traceId = null;
    	userTrace.info("*************sysUser:{}"+sysUser);
        if (sysUser != null && sysUser.getId() > 0) {
        	userTrace.info("*************sysUser:{}"+sysUser.toString());
            UserDetail userDetail = new UserDetail(sysUser); // need fix me!
            UserHolder.setUserDetail(userDetail);
            request.setAttribute(AdminConstants.CURRENT_USER_ATTRIBUTE,
                    userDetail);
            request.setAttribute(AdminConstants.CURRENT_XID_ATTRIBUTE,
                    Base62.encode(sysUser.getId()));
            traceId = cookiePassport.getTicket();
        } else if (cookiePassport != null
                && StringUtils.isNotBlank(cookiePassport.getTicket())) { // guest访问
            traceId = cookiePassport.getTicket();
        } else {
            CookieUtils.setGuestCookie(req, resp);
        }
        UserTraceLogger.setTraceId(traceId);
        MDC.put("traceid", traceId);

        // 处理cookie的逻辑 ,request 中放入userId
        String logParams = "";
        if (userTrace.isInfoEnabled() && isNeedLog(req)) {
            logParams = RequestUtils.formatRequestParameters(req);
            userTrace.info("traceId=" + traceId + ", userId="
                    + UserHolder.getUserId() + ", ip="
                    + UserHolder.currentIpAddress() + ", request="
                    + req.getRequestURI() + ", params=" + logParams);
        }
        
        try {
            chain.doFilter(request, response);
        } catch (IOException e) {
            logError(e);
            throw e;
        } catch (ServletException e) {
            logError(e);
            throw e;
        } catch (RuntimeException e) {
            logError(e);
            throw e;
        } finally {
            if (userTrace.isInfoEnabled() && isNeedLog(req)) {
                userTrace.info("traceId=" + traceId + ", userId="
                        + UserHolder.getUserId() + ", ip="
                        + UserHolder.currentIpAddress() + ", request="
                        + req.getRequestURI() + ", params=" + logParams
                        + " Request End, Time="
                        + (System.currentTimeMillis() - startTime));
            }
            UserHolder.clear();
            MDC.remove("traceid");
        }
        
    }

    private boolean isNeedLog(HttpServletRequest req) {
        String uri = req.getRequestURI();

        if ("/favicon.ico".equals(uri) || uri.endsWith(".css")
                || uri.endsWith(".js") || uri.endsWith(".jpg")
                || uri.endsWith(".png")
                || uri.startsWith("/healthcheck.html")) {
            return false;
        }

        return true;
    }

    private String getIp(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip)
                || StringUtils.equalsIgnoreCase("unknown", ip)) {
            ip = req.getRemoteAddr();
        }
        if (StringUtils.equalsIgnoreCase("127.0.0.1", ip)) {
            ip = DnsUtil.getHostAddress();
        }
        if (StringUtils.length(ip) > 15 && StringUtils.indexOf(ip, ",") > 0) {
            ip = StringUtils.substring(ip, 0, StringUtils.indexOf(ip, ","));
        }
        return ip;
    }

    private void logError(Exception e) {
        userTrace.error("Request Error", e);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {
    	
    }
}