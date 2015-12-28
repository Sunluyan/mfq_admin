package com.mfq.admin.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(StartListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                logger.error("uncaughtException", e);
            }
        });
        //加载seo模板
        //new SeoCache().load();
        
        if (logger.isInfoEnabled()) {
            logger.info("enter 时间程序");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        
    }
}