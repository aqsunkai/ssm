package com.cn.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Person {
//@XmlRootElement：根节点，且可以增加属性(name="NewRootElementName")，那么生成的xml串的标签别名是NewRootElementName
//@XmlAttribute：该属性作为xml的属性，且可以增加属性(name="NewAttributeName")，那么生成的xml串的标签别名是NewAttributeName
//@XmlElement：该属性作为xml的元素，且可以增加属性(name="NewElementName")，那么生成的xml串的标签别名是NewElementName
//@XmlElementWrapper:对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器。如果不加该注解，集合中的每个对象的父节点不是List了，而是Class
//@XmlType，将Java类或枚举类型映射到XML模式类型
//@XmlAccessorType(XmlAccessType.FIELD) ，控制字段或属性的序列化。
//    XmlAccessType.FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标 注）字段到XML,即使在java对象中的属性上不加注解@XmlElement等也可以转成XML对象
//    XmlAccessType.NONE表示JAXB不自动绑定属性字段到XML，所以需要在需要转换的属性上加上注解@XmlElement才能转成XML对象。
//	  XmlAccessType.PROPERTY表示只有属性(有get/set方法)才能被转换成XML中的标签,并且属性上不许有注解@XmlElement等,除非用@XmlTransient注释。
//    XmlAccessType.PUBLIC_MEMBER表示每个公共(public)获取(get)方法/设置(set)方法对和每个公共字段将会自动绑定到 XML，除非由@XmlTransient注释。
//@XmlAccessorOrder，控制JAXB 绑定类中属性和字段的排序
//@XmlJavaTypeAdapter，使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML
//对于根节点中的对象，在该对象类上用@XmlRootElement设置别名没有作用
	//姓名
	@XmlElement(name = "newName")
	private String name;
	
	//性别
	@XmlElement(name = "sex_1")
	private String sex;
	
	//年龄
	@XmlElement
	private int age;
	
	//地址
	@XmlElementWrapper(name = "Address")
	@XmlElement(name = "address")
	private List<Address> Address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<Address> getAddress() {
		return Address;
	}
	public void setAddress(List<Address> address) {
		Address = address;
	}
	
	
}
