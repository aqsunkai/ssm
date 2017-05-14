package com.cn.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cn.base.SpringContext;
import com.cn.common.service.QuartzService;

/**
 * 定时任务加载
 * @author sun.kai
 * 2016年9月17日
 */
public class QuarzInitServlet extends HttpServlet{

	private static final long serialVersionUID = 8308245370653481827L;

	private Logger logger = Logger.getLogger(getClass());
	
	public void init(){
		logger.info("定时任务加载开始");

		try {
			super.init();

			// 取spring容器中的任务调度服务bean
			QuartzService quartzService = (QuartzService) SpringContext.getBean("quartzServiceImpl");

			// 调用service初始化批处理
			quartzService.initScheduler();
		} catch (Exception e) {
			logger.info("定时任务加载失败，请手动加载！\n" ,e);
		}

		logger.info("定时任务加载结束");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		logger.info("定时任务重新调度开始");

		try {
			// 取spring容器中的任务调度服务bean
			QuartzService quartzService = (QuartzService) SpringContext.getBean("quartzServiceImpl");
			
			// 重新调度任务
			quartzService.reScheduler();
		} catch (Exception e) {
			logger.info("定时任务调度失败！\n" , e);
		}

		logger.info("定时任务重新调度结束");
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		this.doGet(req, resp);
	}
}
