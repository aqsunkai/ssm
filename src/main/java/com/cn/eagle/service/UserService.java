package com.cn.eagle.service;

import java.util.Map;
import com.cn.entity.User;

public interface UserService {

	 User selectByPrimaryKey(Integer id);  
	 void insertSelective(User user);
	 void deleteByPrimaryKey(Integer id);
	 void insertUsers(Map<String, Object> param);
	 void test();
}
