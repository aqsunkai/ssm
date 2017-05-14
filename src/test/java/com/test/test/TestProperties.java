package com.test.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class TestProperties {
	private static TestProperties testProperties = new TestProperties();
	public static void main(String[] args) {
		//获取properties配置文件中的值
		Properties prop = new Properties();
		try {
			prop.load(test1());//包含2种方法
			prop.load(test2());//包含2种方法
			prop.load(testProperties.test3());//包含2种方法
			//使用spring-core包封装好的方法
			prop = PropertiesLoaderUtils.loadAllProperties("test.properties");
			Enumeration<?> e = prop.propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				System.out.println(key+"="+new String(prop.getProperty(key).getBytes("ISO-8859-1"),"UTF-8"));
			}
			test4();
			test5();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 使用FileInputStream文件输入流
	 * @return
	 */
	public static InputStream test1(){
		InputStream in = null;
		try {
			//此处是相对于项目的相对路径
			//in = new FileInputStream("src/main/resources/test.properties");
			//或
			in = new BufferedInputStream(new FileInputStream("src/main/resources/test.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        return in;
	}
	/**
	 * 使用ClassLoader
	 * 默认从classPath路径下找文件
	 * @return
	 */
	public static InputStream test2(){
		//InputStream in = ClassLoader.getSystemResourceAsStream ("test.properties");
		//或
		InputStream in = testProperties.getClass().getClassLoader().getResourceAsStream("test.properties");
		return in;
	}
	/**
	 * 使用class变量的getResourceAsStream()方法
	 * 文件名前不加“/”，则表示从当前类所在的包下查找该资源
	 * 文件名前加了“/”，则表示从classPath路径下查找资源
	 * @return
	 */
	public InputStream test3(){
		//InputStream in = getClass().getResourceAsStream("/test.properties");
		//或
		InputStream in = TestProperties.class.getResourceAsStream("/test.properties");
		return in;
	}
	/**
	 * 使用java.util.ResourceBundle类的getBundle()方法
	 * Locale.getDefault()：没有提供语言和地区的资源文件是系统默认的资源文件
	 * test：不需要文件的后缀
	 */
	public static void test4(){
		try {
			ResourceBundle rb = ResourceBundle.getBundle("test", Locale.getDefault());
			Enumeration<String> e1 = rb.getKeys();
			while (e1.hasMoreElements()) {
				String key = e1.nextElement();
				System.out.println(key+"="+new String(rb.getString(key).getBytes("ISO-8859-1"),"UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 使用java.util.PropertyResourceBundle类的构造函数
	 */
	public static void test5(){
		InputStream in = ClassLoader.getSystemResourceAsStream ("test.properties");
		try {
			ResourceBundle rb = new PropertyResourceBundle(in);
			Enumeration<String> e1 = rb.getKeys();
			while (e1.hasMoreElements()) {
				String key = e1.nextElement();
				System.out.println(key+"="+new String(rb.getString(key).getBytes("ISO-8859-1"),"UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
