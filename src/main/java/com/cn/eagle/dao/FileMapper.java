package com.cn.eagle.dao;

import com.cn.entity.FileDTO;

public interface FileMapper {
    void insertSelective(FileDTO fileDTO);
}