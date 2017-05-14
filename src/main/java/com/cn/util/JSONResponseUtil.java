package com.cn.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;



/**
 * 该类实现了JSON格式的数据输出功能
 * 
 * @author ChengYao.Liang
 * 
 * @version 1.0
 */
public class JSONResponseUtil {

	private static Charset charset = Charset.forName("UTF-8");

	public static String getBasePath(HttpServletRequest req) {
		String path = req.getContextPath();
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
	}

	/**
	 * 响应xml格式的数据集
	 */
	public static void buildXmlDataResponse(HttpServletResponse response, String xmlString) {
		PrintWriter out = null;

		try {

			response.setContentType("text/xml; charset=UTF-8");
			response.setContentLength(xmlString.getBytes(charset).length);

			out = response.getWriter();
			out.write(xmlString);
		} catch (IOException e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 响应xml格式的数据集
	 */
	public static void buildListDataResponse(HttpServletResponse response, List<?> list) {
		PrintWriter out = null;

		try {
			String outString = "[";

			for (Object o : list) {
				String s = "['" + o.toString() + "'],";
				outString = outString + s;
			}

			outString = outString.substring(0, outString.length() - 1) + "]";

			response.setContentType("text/xml; charset=UTF-8");
			response.setContentLength(outString.getBytes(charset).length);

			out = response.getWriter();
			out.write(outString);
		} catch (IOException e) {
			// logger.error("输出xml数据流时发生异常:" + e.getMessage());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 响应生成Grid格式的JSON数据集 <br>
	 * 'totalCount':Grid的JSONReader的totalProperty属性必须设置为"totalCount" <br>
	 * 'rows':Grid的JSONReader的root属性必须设置为"rows"
	 */
	public static void buildJSONDataResponse(HttpServletResponse response, String jsonString, Long total) {
		PrintWriter out = null;

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("{\"totalCount\":" + total + ",\"rows\":");
			sb.append(jsonString);
			sb.append("}");

			response.setContentType("application/json; charset=UTF-8");
			response.setContentLength(sb.toString().getBytes(charset).length);

			out = response.getWriter();
			out.write(sb.toString());
		} catch (IOException e) {
			// logger.error("输出JSON数据流时发生异常:" + e.getMessage());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 响应生成String格式的数据
	 */
	public static void buildStringResponse(HttpServletResponse response, String string) {
		PrintWriter out = null;
		try {
			response.setContentLength(string.getBytes(charset).length);
			response.setContentType("text/html; charset=UTF-8");
			out = response.getWriter();
			out.write(string);
		} catch (IOException e) {
			// logger.error("输出JSON数据流时发生异常:" + e.getMessage());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	/**
	 * 响应生成JSON格式的数据
	 */
	public static void buildJSONDataResponse(HttpServletResponse response, String jsonString) {
		PrintWriter out = null;

		try {
			response.setContentType("application/json; charset=UTF-8");
			response.setContentLength(jsonString.getBytes(charset).length);
			out = response.getWriter();
			out.write(jsonString);
		} catch (IOException e) {
			// logger.error("输出JSON数据流时发生异常:" + e.getMessage());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	public static JsonNode buffer(HttpServletRequest request) {
		JsonNode checkInfo = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			// 将资料解码
			String reqBody = sb.toString();
			reqBody = URLDecoder.decode(reqBody, "utf-8");
			checkInfo = ObjectMapperTool.mapper.readTree(reqBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkInfo;
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param date
	 * @param dateFormat
	 *            默认为"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String formatDateTime(Date date, String dateTimeFormat) {
		if (StringUtils.isEmpty(dateTimeFormat)) {
			dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
		}

		SimpleDateFormat format = new SimpleDateFormat(dateTimeFormat);

		return format.format(date);
	}

	/**
	 * 判断json中有没有键key 有的话返回value 没有的话返回null
	 * */
	public static String getStringCouldNull(JsonNode json, String key) throws Exception, JsonProcessingException {
		String result = null;
		if (json.has(key)) {
			result = json.path(key).asText();
		}
		return result;
	}

	/**
	 * 与上一个方法参数顺序相反 会抛出一个ServiceException异常 与上一个方法区分 用于去别人接口取条形码数据时 别人的接口也给不出数据时
	 * */
	public static String getString(String key, JsonNode json) throws Exception, JsonProcessingException {
		String result = null;
		if (json.has(key)) {
			result = json.path(key).asText();
		} else {
			throw new Exception();
		}
		return result;
	}

	/**
	 * 响应生成Grid格式的JSON数据集 <br>
	 * JSON对象的属性可以根据需要自定义设置，通过Map添加键值即可<br>
	 * 如: Map<String,Object> map = new HashMap<String,Object>();<br>
	 * map.put("totalCount",125);<br>
	 * map.put("success",true);<br>
	 * map.put("data",list);
	 */
	public static void buildCustomJSONDataResponse(HttpServletResponse response, Map<String, ? extends Object> data) {
		PrintWriter out = null;
		try {
			String str = ObjectMapperTool.toJson(data);
			response.setContentType("application/json; charset=UTF-8");
			byte[] jsonBytes = str.getBytes(charset);
			response.setContentLength(jsonBytes.length);
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

}
