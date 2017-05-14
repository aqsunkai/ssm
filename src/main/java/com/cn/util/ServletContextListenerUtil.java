package com.cn.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ServletContextListenerUtil implements ServletContextListener{

	//监听器的初始化
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("监听器ServletContextListenerUtil初始化");
	}

	//监听器销毁
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("监听器ServletContextListenerUtil销毁");
	}

}
