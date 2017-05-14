package com.cn.base;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.cn.constant.enums.ENUM_THIRD_TYPE;

public abstract class PlatformBaseClass {

	protected Logger logger = Logger.getLogger(getClass());
	
	protected ENUM_THIRD_TYPE thirdType;

	@Autowired
	protected Properties properties;
}
