package com.test.test;
import com.cn.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
/**
 * fastjson是阿里做的国有开源Java工具包
 * 比
 * @author sun.kai
 * 2016年8月7日
 */
public class TestJsonThree {
	
	public static void main(String[] args){
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
		testFastjson(users);
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
	}
	
	@SuppressWarnings("unchecked")
	public static void testFastjson(List<User> users){
		/*
		 * java对象是一个集合
		 * 转成的字符串是json对象数组
		 */
		String fastjsonString1 = JSON.toJSONString(users);
		System.out.println(fastjsonString1);
		/*
		 * java对象不是一个集合
		 * 转成json对象字符串
		 */
		String fastjsonString2 = JSON.toJSONString(users.get(0));
		System.out.println(fastjsonString2);
		/*
		 * json对象数组字符串转java对象集合
		 */
		String jsonString1 = "[{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"},{\"age\":26,\"id\":2,\"password\":\"海角七号123456\",\"userName\":\"海角七号\"}]";
		List<User> userList = JSON.parseArray(jsonString1, User.class);
		for (User user : userList) {
			System.out.println(user.getUserName());
		}
		/*
		 * json对象数组字符串转Map集合
		 */
		List<Map<String, Object>> maps = JSON.parseObject(jsonString1,new TypeReference<List<Map<String,Object>>>(){});
		System.out.println(maps);
		for (Map<String, Object> map : maps) {
			System.out.println(map.get("userName"));
		}
		/*
		 * json对象字符串转java对象
		 */
		String jsonString2 = "{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"}";
		User user = JSON.parseObject(jsonString2, User.class);
		System.out.println(user.getUserName());
		/*
		 * json对象字符串转Map
		 */
		Map<String, Object> map = JSON.parseObject(jsonString2, Map.class);
		System.out.println(map);
		System.out.println(map.get("userName"));
	}
}
