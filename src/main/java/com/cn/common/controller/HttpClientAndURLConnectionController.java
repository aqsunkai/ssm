package com.cn.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/http")
public class HttpClientAndURLConnectionController {

	@RequestMapping("/testHttp")
	public void testHttp(HttpServletRequest req,HttpServletResponse resp) {
		try {
			long s1 = System.currentTimeMillis();
			testHttpClient();
			long s2 = System.currentTimeMillis();
			testHttpURlConnection();
			long s3 = System.currentTimeMillis();
			System.out.println((s2-s1)+" "+(s3-s2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void testHttpClient() throws IOException{
		System.out.println("testHttpClient");
		//请求方法
    	String result="";
    	String url = "http://192.168.2.111:8088/era/user/getUserById";
    	HttpClient client =new HttpClient();
    	//设置连接时间
    	client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
    	//解决乱码问题
    	client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
    	PostMethod method =new PostMethod(url);
    	//传参
    	method.addParameter("userId", "100");
    	int status = client.executeMethod(method);
    	if(status == HttpStatus.SC_OK){
    		result = method.getResponseBodyAsString();
    	}
    	System.out.println(result);
    	method.releaseConnection();
    }
	public void testHttpURlConnection() throws IOException{
		System.out.println("testHttpURlConnection");
        String result = "";
        URL url = new URL("http://192.168.2.111:8088/era/user/getUserById");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30000);//设置连接主机超时（单位：毫秒）
        conn.setReadTimeout(30000);//设置从主机读取数据超时（单位：毫秒）
        conn.setRequestMethod("POST");//设定请求的方法为"POST"，默认是GET
        conn.setDoInput(true);//设置是否从httpUrlConnection读入，默认情况下是true
        conn.setDoOutput(true);//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false
        conn.setUseCaches(false);//Post请求不能使用缓存
        conn.setInstanceFollowRedirects(false);
        conn.setRequestProperty("Content-Type", "text/html; charset=utf-8");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if(responsecode == HttpURLConnection.HTTP_OK){ //对应HTTP响应中状态行的响应码
        	//操作请求流，这里对应HTTP响应中的响应正文 
        	InputStream urlStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream,"utf-8"));
            String s = "";
            while ((s = reader.readLine()) != null) {
                result += s;
            }
            reader.close();
            urlStream.close();
        }
        System.out.println(result);
        if(conn != null){
        	conn.disconnect();
        }
    }
}
