package com.cn.common.service;

import java.util.List;

import com.cn.entity.Quarz;

/**
 * 定时任务调度service
 * @author sun.kai
 * 2016年9月17日
 */
public interface QuartzService {

	void initScheduler() throws Exception;
	
	void reScheduler()  throws Exception;
	
	/**
	 * 查询所有的定时任务
	 * @return
	 */
	List<Quarz> selectAll();
}
