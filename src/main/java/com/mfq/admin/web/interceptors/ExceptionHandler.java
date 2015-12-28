package com.mfq.admin.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Service
public class ExceptionHandler implements HandlerExceptionResolver {
    
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public ExceptionHandler() {
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error(ex.getMessage(), ex);
        logger.error("REQUEST=> " + request.getRequestURL());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            logger.error("[ERROR]" + handlerMethod.getMethod());
            if (handlerMethod.getMethodAnnotation(ResponseBody.class) != null) {
                return new ModelAndView("/error/json");
            }
        }
        return new ModelAndView("/error/index");
    }
}