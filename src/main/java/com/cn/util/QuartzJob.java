package com.cn.util;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cn.base.SpringContext;


/**
 * 工具类：调用具体批处理业务类,串行执行
 * @author zhangzh
 *
 */
public class QuartzJob extends QuartzJobBean implements StatefulJob {

	private static Logger logger = Logger.getLogger(QuartzJob.class.getName());
	
	/**
	 * key 对象标识
	 */
	public static final String OBJECT_NAME = "beanName";
	
	/**
	 * key 对象方法
	 */
	public static final String OBJECT_METHOD = "methodName";
	
	/**
	 * 调用jobmap中的配置的bean.method
	 */
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		
		String beanName = (String) context.getMergedJobDataMap().get(OBJECT_NAME);
		String methodName = (String) context.getMergedJobDataMap().get(OBJECT_METHOD);
		try {
			ReflectionUtil.invokeMethod(SpringContext.getBean(beanName),
					methodName, null, null);
		} catch (Exception e) {
			 logger.warn("批处理调用失败: " + beanName +"." + methodName + "\n" , e);
		}
	}

}
