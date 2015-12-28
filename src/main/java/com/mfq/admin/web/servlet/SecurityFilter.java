package com.mfq.admin.web.servlet;

import static java.lang.String.format;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.admin.web.models.Menu;
import com.mfq.admin.web.security.UserDetail;
import com.mfq.admin.web.security.UserHolder;
import com.mfq.admin.web.services.SysAclCache;
import com.mfq.admin.web.utils.DnsUtil;
import com.mfq.admin.web.utils.RequestUtils;

/**
 * 权限相关控制
 * 
 * @author xingyongshan
 *
 */
public class SecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory
            .getLogger(SecurityFilter.class);

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            request.setAttribute("_server", request.getServerName());
            request.setAttribute("_siteName", "超级系统管理平台");
            request.setAttribute("_rhost", DnsUtil.getAllLocalSiteAddress());
            // 权限检测 放在这里
            if (isStaticResource(req) || isBlackUrl(req)) {
                // do nothing
            } else if (!checkAuth(req, UserHolder.currentUserDetail())) {
                // 校验资源权限失败
                String path = req.getRequestURI();
                logger.warn("User {} no right visit uri:{}",
                        UserHolder.getUserId(), path);
                if (RequestUtils.isAjaxRequest(req)) {
                    resp.getWriter().append(format(
                            "{\"msg\": \"没有访问的权限 %s\", \"code\": 9999}", path));
                    resp.getWriter().flush();
                } else {
                    request.setAttribute("msg", "没有访问的权限 " + path);
                    boolean value = System.currentTimeMillis() % 2 == 0;
                    resp.sendRedirect("/login/?showtyes=" + value);
                }
                return;
            } else { // 校验成功的情况，成功登录用户！！
                     // 资源权限校验成功,设置上下文
                _prepareConext(req, UserHolder.currentUserDetail());
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("SECURITY_FILTER_EXCEPTION", e);
        }
    }

    /**
     * do nothing now, need fix!!!!!!!!
     * 
     * @param req
     * @param _userDetail
     * @return
     */
    private boolean checkAuth(HttpServletRequest req, UserDetail _userDetail) {
        if (req == null || _userDetail == null) {
            return false;
        } else {
            String uri = req.getRequestURI();
            logger.info("Access_ADMIN_request_URI:{}", uri);
            boolean can = SysAclCache.getInstance()
                    .hasRight(_userDetail.getSysUser().getRoleId(), uri);
            if (can) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param request
     * @param _userdetail
     */
    private void _prepareConext(HttpServletRequest request,
            UserDetail _userdetail) {
        if (_userdetail == null) {
            logger.warn("_userdetail is null");
        } else {
            request.setAttribute("_user", _userdetail.getUsername());
            request.setAttribute("_userdetail", _userdetail);
            Menu menu = (Menu) request.getAttribute("_menu");
            if (menu == null && _userdetail.getSysUser() != null) {
                menu = SysAclCache.getInstance()
                        .getMenu(_userdetail.getSysUser().getRoleId());
                request.setAttribute("_menu", menu);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    /**
     * 是否是白名单的URL
     * 
     * @return
     */
    private boolean isBlackUrl(HttpServletRequest req) {
        if (StringUtils.startsWithIgnoreCase(req.getRequestURI(), "/login/")
                || StringUtils.startsWithIgnoreCase(req.getRequestURI(),
                        "/xlogin/")
                || StringUtils.startsWithIgnoreCase(req.getRequestURI(),
                        "/logout/")
                || StringUtils.startsWithIgnoreCase(req.getRequestURI(),
                        "/mobile/check")) {
            return true;
        }
        return false;
    }

    private boolean isStaticResource(HttpServletRequest req) {
        if (StringUtils.startsWithIgnoreCase(req.getRequestURI(), "/static/")
                || StringUtils.startsWithIgnoreCase(req.getRequestURI(),
                        "/upload/")
                || StringUtils.startsWithIgnoreCase(req.getRequestURI(),
                        "/favicon.ico")) {
            return true;
        }
        return false;
    }
}
