package com.cn.eagle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.eagle.bo.FileBo;
import com.cn.eagle.service.FileService;
import com.cn.entity.FileDTO;
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileBo fileBo;
	
	@Override
	public void insertSelective(FileDTO fileDTO) {
		fileBo.insertSelective(fileDTO);
		
	}

}
