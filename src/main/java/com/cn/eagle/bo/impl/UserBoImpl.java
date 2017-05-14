package com.cn.eagle.bo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn.eagle.bo.UserBo;
import com.cn.eagle.dao.UserMapper;
import com.cn.entity.User;

@Component
public class UserBoImpl implements UserBo{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void insertSelective(User user) {
		userMapper.insertSelective(user);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void insertUsers(Map<String, Object> param) {
		userMapper.insertUsers(param);
	}
	
}
