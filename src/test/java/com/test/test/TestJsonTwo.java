package com.test.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cn.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * jackson是spring mvc内置的json转换工具
 * @author sun.kai
 * 2016年8月7日
 */
public class TestJsonTwo {
	public static void main(String[] args) throws JsonProcessingException{
		long time1 = System.currentTimeMillis();
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setId(1);
		user1.setUserName("admin管理员");
		user1.setPassword("admin123456");
		user1.setAge(25);
		users.add(user1);
		User user2 = new User();
		user2.setId(2);
		user2.setUserName("海角七号");
		user2.setPassword("海角七号123456");
		user2.setAge(26);
		users.add(user2);
		try {
			testObjectMapper(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
	}
	
	public static void testObjectMapper(List<User> users) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		/*
		 * java对象是一个集合
		 * 转成的字符串是json对象数组
		 */
		String mapperString1 = mapper.writeValueAsString(users);
		System.out.println(mapperString1);
		/*
		 * java对象不是一个集合
		 * 转成json对象字符串
		 */
		String mapperString2 = mapper.writeValueAsString(users.get(0));
		System.out.println(mapperString2);
		/*
		 * json对象数组字符串转java对象集合
		 */
		String jsonString1 = "[{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"},{\"age\":26,\"id\":2,\"password\":\"海角七号123456\",\"userName\":\"海角七号\"}]";
		User[] userArray = mapper.readValue(jsonString1, User[].class);
		List<User> userList = Arrays.asList(userArray);
		for (User user : userList) {
			System.out.println(user.getUserName());
		}
		/*
		 * json对象字符串转java对象
		 */
		String jsonString2 = "{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"}";
		User user = mapper.readValue(jsonString2, User.class);
		System.out.println(user.getUserName());
	}
}
