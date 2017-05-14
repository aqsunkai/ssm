package com.cn.eagle.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cn.eagle.bo.FileBo;
import com.cn.eagle.dao.FileMapper;
import com.cn.entity.FileDTO;
@Component
public class FileBoImpl implements FileBo{
	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public void insertSelective(FileDTO fileDTO) {
		fileMapper.insertSelective(fileDTO);
	}

}
