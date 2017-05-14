package com.test.test;

import java.util.ArrayList;
import java.util.List;

import com.cn.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * JSONObject和JSONArray
 * @author sun.kai
 * 2016年8月7日
 */
public class TestJsonOne {

	public static void main(String[] args){
		long time1 = System.currentTimeMillis();
//		Map<String, Object> param = new HashMap<String,Object>();
//		param.put("id", 1);
//		param.put("userName", "admin管理员");
//		param.put("password", "admin123456");
//		param.put("age", 25);
//		JSONObject jsonObject = JSONObject.fromObject(param);
//		System.out.println(jsonObject);
//		JSONArray jsonArray = JSONArray.fromObject(param);
//		System.out.println(jsonArray);
		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setId(1);
		user1.setUserName("admin管理员");
		user1.setPassword("admin123456");
		user1.setAge(25);
		testJsonObject(user1);
		list.add(user1);
		User user2 = new User();
		user2.setId(2);
		user2.setUserName("海角七号");
		user2.setPassword("海角七号123456");
		user2.setAge(26);
		list.add(user2);
		testJsonArray(list);
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
	}
	@SuppressWarnings("static-access")
	public static void testJsonObject(User user){
		System.out.println("JSONObject测试");
		/*
		 * java对象转换成json对象
		 */
		JSONObject jsonObject1 = JSONObject.fromObject(user);
		/*
		 * json对象转成字符串
		 */
		String jsonsString1 = jsonObject1.toString();
		System.out.println(jsonObject1);
		System.out.println(jsonsString1);
		
		String jsonString2 = "{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"}";
		/*
		 * 字符串转成json对象
		 */
		JSONObject jsonObject2 = JSONObject.fromObject(jsonString2);
		/*
		 * json对象转成java对象
		 */
		User user2 = (User) jsonObject2.toBean(jsonObject2,User.class);
		System.out.println(user2.getUserName());
		/*
		 * 获取json对象中的json对象数组
		 */
		String jsonString3 = "{\"abc\":[{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"},"
				+ "{\"age\":26,\"id\":2,\"password\":\"海角七号123456\",\"userName\":\"海角七号\"}"
				+ "]}";
		//字符串转成json对象
		JSONObject jsonObject3 = JSONObject.fromObject(jsonString3);
		//获取json对象中的json对象数组
		JSONArray jsonArray = jsonObject3.getJSONArray("abc");
		System.out.println(jsonArray.toString());
	}
	@SuppressWarnings("unchecked")
	public static void testJsonArray(List<User> userList){
		System.out.println("JSONArray测试");
		/*
		 * java对象列表转换为json对象数组
		 */
		JSONArray jsonArray1 = JSONArray.fromObject(userList);
		/*
		 * json对象数组转成字符串
		 */
		String arrayString1 = jsonArray1.toString();
		System.out.println(jsonArray1);
		System.out.println(arrayString1);
		
		String arrayString2 = "[{\"age\":25,\"id\":1,\"password\":\"admin123456\",\"userName\":\"admin管理员\"},{\"age\":26,\"id\":2,\"password\":\"海角七号123456\",\"userName\":\"海角七号\"}]";
		/*
		 * 字符串转成json对象数组
		 */
		JSONArray jsonArray2 = JSONArray.fromObject(arrayString2);
		/*
		 * json对象数组转成java对象
		 */
		List<User> users = (List<User>) JSONArray.toCollection(jsonArray2, User.class);
		for(User user : users){
			System.out.println(user.getUserName());
		}
		/*
		 * 循环json对象数组，获取json对象
		 */
		for(int i = 0;i<jsonArray2.size();i++){
			JSONObject jsonObject = jsonArray2.getJSONObject(i);
			System.out.println(jsonObject);
		}
	}
}
