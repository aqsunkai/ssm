package com.cn.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder={"country","province","city","county","town"})
public class Address {

	//国
	//@XmlElement(name = "country000")
	private String country;
	//省
	//@XmlElement
	private String province;
	//市
	//@XmlElement
	private String city;
	//县
	//@XmlElement
	private String county;
	//镇
	//@XmlElement(required = true)
	private String town; 
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	
	
}
