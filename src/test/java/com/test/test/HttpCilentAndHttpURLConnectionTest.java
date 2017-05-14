package com.test.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpCilentAndHttpURLConnectionTest {

	public static void main(String[] args) {
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
	public static void testHttpClient() throws IOException{
		System.out.println("testHttpClient");
		//请求方法
    	String result="";
    	String url = "http://www.baidu.com";
    	HttpClient client =new HttpClient();
    	//设置连接时间
    	client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
    	//解决乱码问题
    	client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
    	PostMethod method =new PostMethod(url);
    	//传参
    	//method.addParameter("data", "");
    	int status = client.executeMethod(method);
    	System.out.println(method.getStatusLine().toString());
    	System.out.println(method.getStatusCode());
    	if(status == HttpStatus.SC_OK){
    		result = method.getResponseBodyAsString();
    	}
    	System.out.println(result);
    	method.releaseConnection();
    }
	public static void testHttpURlConnection() throws IOException{
		System.out.println("testHttpURlConnection");
        String result = "";
        URL url = new URL("http://www.baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        //conn.setReadTimeout(CommonValues.ReadTimeOut);
        //doPost请求
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        conn.connect();
        int responsecode = conn.getResponseCode();
        if(responsecode == HttpURLConnection.HTTP_OK){ //对应HTTP响应中状态行的响应码
        	//操作请求流，这里对应HTTP响应中的响应正文 
        	InputStream urlStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlStream));
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
