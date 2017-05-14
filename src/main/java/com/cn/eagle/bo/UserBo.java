package com.cn.eagle.bo;

import java.util.Map;

import com.cn.entity.User;

public interface UserBo {

	User selectByPrimaryKey(Integer id);  
	
	void insertSelective(User user);
	
	void deleteByPrimaryKey(Integer id);
	
	void insertUsers(Map<String, Object> param);
}
