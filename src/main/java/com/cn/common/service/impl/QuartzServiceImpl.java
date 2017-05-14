//package com.cn.common.service.impl;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.cn.common.dao.QuarzMapper;
//import com.cn.common.service.QuartzService;
//import com.cn.entity.QuartzJobDTO;
//import com.cn.entity.Quarz;
//import com.cn.util.QuartzJob;
//import com.cn.util.QuartzUtil;
//@Service
//public class QuartzServiceImpl implements QuartzService{
//
//	private Logger logger = Logger.getLogger(getClass());
//	@Autowired
//	private QuarzMapper quarzMapper;
//	@Autowired
//    private QuartzUtil quartzUtil;
//	
//	@Override
//	public void initScheduler() throws Exception {
//		List<Quarz> quarzList = quarzMapper.selectAll();
//
//		//逐笔设置任务信息到quartz,并调度任务
//		if (null != quarzList && quarzList.size() > 0) {
//			for (Quarz quarzInfo : quarzList) {
//				QuartzJobDTO job = new QuartzJobDTO();
//				job.getJobDataMap().put(QuartzJob.OBJECT_NAME, quarzInfo.getObjectName());
//				job.getJobDataMap().put(QuartzJob.OBJECT_METHOD, quarzInfo.getObjectMethod());
//				//单线程方式执行任务
//				job.setJobClass(QuartzJob.class);
//				job.setCronExpression(quarzInfo.getExecuteTime());
//				String jobName = quarzInfo.getName() +"_" + quarzInfo.getId();
//				job.setJobName(jobName);
//				logger.info("----开始部署任务:" + jobName);
//				quartzUtil.scheduleCronJob(job);
//				logger.info("----成功部署任务:" + jobName);
//			}
//			logger.info("批处理提取并调度完成");
//		}
//		
//	}
//
//	@Override
//	public void reScheduler() throws Exception {
//		// 取消现有的任务
//		String[] jobNames = quartzUtil.getJobNames();
//		if (null != jobNames && jobNames.length > 0) {
//			for (String jobName : jobNames) {
//				logger.info("----开始移除任务:" + jobName);
//				quartzUtil.cancelJob(jobName);
//				logger.info("----成功移除任务:" + jobName);
//			}
//		}
//		logger.info("现有任务已全部取消");
//
//		this.initScheduler();
//	}
//
//	/**
//	 * 查询所有的定时任务
//	 */
//	@Override
//	public List<Quarz> selectAll() {
//		return quarzMapper.selectAll();
//	}
//	
//}
