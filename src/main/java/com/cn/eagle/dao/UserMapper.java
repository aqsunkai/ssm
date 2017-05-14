package com.cn.eagle.dao;

import java.util.Map;

import com.cn.entity.User;

public interface UserMapper {
    void deleteByPrimaryKey(Integer id);

    int insert(User record);

    void insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    void insertUsers(Map<String, Object> param);
}