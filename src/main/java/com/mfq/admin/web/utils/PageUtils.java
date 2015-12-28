package com.mfq.admin.web.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

/**
 * 分页工具类
 * 
 */
public class PageUtils {

    public static String buildPageUrl(int page, HttpServletRequest request) {
        return buildPageUrl(page, request.getRequestURI(), request.getQueryString());
    }

    public static String buildPageUrl(int page, String uri, String queryString) {
        Pattern pagePattern = Pattern.compile("/(page[0-9]+/?)$");
        uri = pagePattern.matcher(uri).replaceAll("/");
        StringBuilder url = new StringBuilder(uri);
        if (!uri.endsWith("/"))
            url.append("/");
        if (page > 1)
            url.append("page").append(page).append("/");
        if (StringUtils.isNotBlank(queryString)) {
            url.append("?").append(queryString);
        }
        return url.toString();
    }

    public static void buildPageParameters(Model model, int page, int totalPage, HttpServletRequest request) {
        buildPageParameters(model, page, totalPage, request.getRequestURI(), request.getQueryString());
    }

    public static void buildPageParameters(HttpServletRequest request, int page, int totalPage) {
        buildPageParameters(request, page, totalPage, request.getRequestURI(), request.getQueryString());
    }

    public static void buildPageParameters(HttpServletRequest request, int page, int totalPage, String uri, String queryString) {
        buildPageParameters(request, page, totalPage, uri, queryString, null, null);
    }

    public static void buildPageParameters(HttpServletRequest request, int page, int totalPage, String uri, String queryString, Integer maxPage,
            Integer maxItems) {
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("uri", uri);
        request.setAttribute("queryString", queryString);
        if (maxPage != null)
            request.setAttribute("maxPage", maxPage);
        if (maxItems == null)
            request.setAttribute("maxItems", maxItems);
    }

    public static void buildPageParameters(Model model, int page, int totalPage, String uri, String queryString) {
        buildPageParameters(model, page, totalPage, uri, queryString, null, null);
    }

    public static void buildPageParameters(Model model, int page, int totalPage, String uri, String queryString, Integer maxPage, Integer maxItems) {
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("uri", uri);
        model.addAttribute("queryString", queryString);
        if (maxPage != null)
            model.addAttribute("maxPage", maxPage);
        if (maxItems == null)
            model.addAttribute("maxItems", maxItems);
    }
}
