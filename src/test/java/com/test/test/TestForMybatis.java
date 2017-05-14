package com.test.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cn.eagle.service.UserService;
import com.cn.entity.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")  
public class TestForMybatis{
	private Logger logger = Logger.getLogger(getClass());
	 //private ApplicationContext ac = null;  
	@Autowired  
    private UserService userService;
	/* @Before  
	 public void before() {  
	      ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");  
	      userService = (UserService) ac.getBean("userService");  
	}  */
	@Test
    public void test1() {  
        User user = userService.selectByPrimaryKey(1);
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
        logger.info(JSON.toJSONString(user));  
    }  
}
