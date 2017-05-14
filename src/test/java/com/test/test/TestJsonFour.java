package com.test.test;
import com.cn.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * google的gson
 * https://mvnrepository.com/artifact/com.google.code.gson/gson
 * @author sun.kai
 * 2016年8月7日
 */
public class TestJsonFour {
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
		testGson(users);
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
	}
	
	@SuppressWarnings("unchecked")
	public static void testGson(List<User> users){
		/*
		 * 创建Gson对象
		 */
		Gson gson = new Gson();
		/*
		 * 获得泛型参数的类型
		 */
		java.lang.reflect.Type type = new TypeToken<List<User>>() {}.getType();
		/*
		 * java对象是一个集合
		 * 转成的字符串是json对象数组
		 */
		String gsonString1 = gson.toJson(users);
		System.out.println(gsonString1);
		/*
		 * java对象不是一个集合
		 * 转成json对象字符串
		 */
		String gsonString2 = gson.toJson(users.get(0));
		System.out.println(gsonString2);
		/*
		 * json对象数组字符串转java对象集合
		 */
		String jsonString1 = "[{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"},{\"age\":26,\"id\":2,\"password\":\"海角七号123456\",\"userName\":\"海角七号\"}]";
		List<User> userList = gson.fromJson(jsonString1, type);
		for (User user : userList) {
			System.out.println(user.getUserName());
		}
		/*
		 * json对象数组字符串转Map集合
		 */
		List<Map<String, Object>> maps = gson.fromJson(jsonString1,new TypeToken<List<Map<String, Object>>>() {}.getType());
		System.out.println(maps);
		for (Map<String, Object> map : maps) {
			System.out.println(map.get("userName"));
		}
		/*
		 * json对象字符串转java对象
		 */
		String jsonString2 = "{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"}";
		User user = gson.fromJson(jsonString2, User.class);
		System.out.println(user.getUserName());
		/*
		 * json对象字符串转Map
		 */
		Map<String, Object> map = gson.fromJson(jsonString2, Map.class);
		System.out.println(map);
		System.out.println(map.get("userName"));
	}
}
