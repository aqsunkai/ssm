package com.cn.constant.enums;

/**
 * 枚举
 * @author sun.kai
 * 2016年4月30日
 */
public enum ENUM_THIRD_TYPE{
	EAGLE("0","身份证"),
	PASSPORT("1","护照"),
	ARMY_CARD("2","军官证"),
	CAR_CARD("3","驾照"), 
	BIRTH_CERTIFICATE_CARD("4","出生证"), 
	RESIDENCE_BOOKLET("5","户口簿"),
	GANG_AO("6","港澳居民来往内地通行证"),
	TW("7","台湾居民来往大陆通行证"),
	OTHER("8","其他"),
	DATA_CONVERT_CARD("9","数据转换证件"),
	ORG_CODE("91","组织机构代码"),
	TAX_REG_CARD("92","税务登记证"),
	BUSINESS_LICENCE_CODE("93","营业执照代码")
	;
	private String code;
  	private String label;

    public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}
	
	ENUM_THIRD_TYPE(String code,String label) {
    	this.code=code;
        this.label=label;
    }

	public static String getValueByIdType(String code){
    	for(ENUM_THIRD_TYPE enum_id_type :ENUM_THIRD_TYPE.values()){
    		if(enum_id_type.code.equals(code)){
    			return enum_id_type.label;
    		}
    	}
    	return null;
    }
	
}
