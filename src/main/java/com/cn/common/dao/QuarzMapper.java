package com.cn.common.dao;

import java.util.List;

import com.cn.entity.Quarz;

public interface QuarzMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Quarz record);

    int insertSelective(Quarz record);

    Quarz selectByPrimaryKey(Integer id);
    
    List<Quarz> selectAll();

    int updateByPrimaryKeySelective(Quarz record);

    int updateByPrimaryKey(Quarz record);
}