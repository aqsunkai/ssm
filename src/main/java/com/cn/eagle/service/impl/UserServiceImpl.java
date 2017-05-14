package com.cn.eagle.service.impl;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cn.eagle.bo.UserBo;
import com.cn.eagle.service.UserService;
import com.cn.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserBo userBo;

	@Cacheable(value="common",key="'id_'+#id")
	public User selectByPrimaryKey(Integer id) {
		return userBo.selectByPrimaryKey(id);
	}
	
	//添加缓存
	//@CachePut(value="common",key="#user.getUserName()")
	public void insertSelective(User user) {
		userBo.insertSelective(user);
	}

	//删除缓存
	//@CacheEvict(value="common",key="'id_'+#id")
	public void deleteByPrimaryKey(Integer id) {
		userBo.deleteByPrimaryKey(id);
	}

	public void insertUsers(Map<String, Object> param){
		userBo.insertUsers(param);
	}

	@Override
	public void test() {
		System.out.println("测试成功");
	}
}
