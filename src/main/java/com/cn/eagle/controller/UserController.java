package com.cn.eagle.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cn.base.PlatformBaseClass;
import com.cn.common.jmsProducer.QueueProducer;
import com.cn.common.jmsProducer.TopicProducer;
import com.cn.constant.enums.ENUM_THIRD_TYPE;
import com.cn.eagle.service.UserService;
import com.cn.entity.User;
import com.cn.util.POIUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用户controller
 * @author sun.kai
 * 2016年4月30日
 */
@Controller
@RequestMapping("/user")
public class UserController extends PlatformBaseClass{
	{
		thirdType = ENUM_THIRD_TYPE.EAGLE;
	}
	
	@Autowired
	private UserService userService;
	@Autowired
	private QueueProducer queueProducer;
	@Autowired
	private TopicProducer topicProducer;
	@Value("#{configProperties['local']}")
	private String localAdress;
	/**
	 * 根据ID查询user表
	 * @param request
	 * @return
	 */
    @RequestMapping("/showUser")  
    public ModelAndView toIndex(HttpServletRequest request){  
    	ModelAndView model = new ModelAndView();
    	
    	//验证log4j
    	logger.info("哈哈哈");
    	//验证枚举
    	//System.out.println(thirdType.getCode());
    	String label = ENUM_THIRD_TYPE.getValueByIdType("0");
    	//System.out.println(label);
    	logger.debug(thirdType.getCode());
    	logger.debug(label);
    	//验证全局配置文件
    	/*System.out.println(properties.getProperty("csdn.url"));*/
    	
        int userId = Integer.parseInt(request.getParameter("id"));  
        User user = this.userService.selectByPrimaryKey(userId);
        model.addObject("user", user);
        model.setViewName("eagle/showUser");
        return model;  
    }  
//    @RequestMapping("/showUser")  
//    public Map<String, Object> toIndex(HttpServletRequest request){  
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	int userId = Integer.parseInt(request.getParameter("id"));  
//    	User user = this.userService.getUserById(userId);  
//    	map.put("v", user);
//    	return map;  
//    }  
    
    @RequestMapping("/insertSelective")
    public void insertSelective(User user,HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	try {
    		userService.insertSelective(user);
    		response.getWriter().print(1);
		} catch (Exception e) {
			// TODO: handle exception
			response.getWriter().print(0);
		}
    	
    }
    
    @RequestMapping("/deleteByPrimaryKey")
    public void deleteByPrimaryKey(Integer id,HttpServletResponse response) throws IOException{
    	response.setContentType("text/html;charset=UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	try {
    		userService.deleteByPrimaryKey(id);
    		response.getWriter().print(1);
		} catch (Exception e) {
			// TODO: handle exception
			response.getWriter().print(0);
		}
    }
    /**
     * ModelAndView跳转(重定向)
     */
    @RequestMapping("alipayforward1")
    public ModelAndView alipayforward1(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	String contNo =req.getParameter("contNo1"); //保单号
    	logger.info("访问/downloadRequestElecCont.action");
    	String url = "redirect:"+localAdress+"contNo="+contNo;
    	return new ModelAndView(url);
    }
    /**
     * String跳转(重定向)/@RequestParam注解传参
     */
    @RequestMapping("alipayforward2")
    public String alipayforward2(@RequestParam("contNo2") String contNo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	logger.info("访问/downloadRequestElecCont.action");
    	return "redirect:"+localAdress+"contNo="+contNo;
    }
    /**
     * Response跳转(重定向)/@PathVariable注解传参
     */
    @RequestMapping("alipayforward3/{contNo}")
    public void alipayforward3(@PathVariable("contNo") String contNo, HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	logger.info("访问/downloadRequestElecCont.action");
    	resp.sendRedirect(localAdress+"contNo="+contNo);
    }

    @RequestMapping("alipayforward4")
    public void alipayforward4(User user, HttpServletRequest req) throws Exception {
    	System.out.println(user.getPassword());
    	String modelMap = req.getParameter("modelMap");
    	System.out.println(modelMap);
    }
    @RequestMapping("goA")
    public void goA(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	resp.sendRedirect("http://wxdat.zdlife.com:9090/wap/yzds/goodsDetail.html?ADTAG=a");
    }
    @RequestMapping("goB")
    public void goB(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	resp.sendRedirect("http://wxdat.zdlife.com:9090/wap/yzds/goodsDetail.html?ADTAG=b");
    }
    @RequestMapping("/showElecContDownloadAddress")
    public void showElecContDownloadAddress(HttpServletRequest req, HttpServletResponse resp) throws HttpException, IOException{
    	resp.setContentType("text/html;charset=UTF-8");
    	resp.setCharacterEncoding("UTF-8");
    	//请求方法
    	String data=new String();
    	String url = "http://localhost:8080/eservice_mysql/showElecContDownloadAddress.action?ajax=true";
    	HttpClient client =new HttpClient();
    	//设置连接时间
    	client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
    	//解决乱码问题
    	client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
    	
    	PostMethod method =new PostMethod(url);
    	method.addParameter("contNo", req.getParameter("contNo"));
    	int status = client.executeMethod(method);
    	System.out.println(method.getStatusLine().toString());
    	System.out.println(method.getStatusCode());
    	if(status == HttpStatus.SC_OK){
    		data = method.getResponseBodyAsString();
    	}
    	method.releaseConnection();
    	resp.getWriter().print(data);
    }
    @RequestMapping("/getUserById")
    public void getUserById(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	resp.setContentType("text/html;charset=utf-8");
    	resp.setCharacterEncoding("UTF-8");
    	int userId;
    	if(null == req.getParameter("userId")){
    		userId = 100;
    	}else{
    		userId = Integer.parseInt(req.getParameter("userId"));
    	}
        User user = this.userService.selectByPrimaryKey(userId);
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        resp.getWriter().print(result);
    }
    /**
     * 读取excel文件中的用户信息，保存在数据库中
     * @param excelFile
     */
    @RequestMapping("/readExcel")
    public void readExcel(@RequestParam(value = "excelFile") MultipartFile excelFile,HttpServletRequest req,HttpServletResponse resp){
    	Map<String, Object> param = new HashMap<String, Object>();
    	List<User> allUsers = new ArrayList<User>();
    	try {
			List<String[]> userList = POIUtil.readExcel(excelFile);
			for(int i = 0;i<userList.size();i++){
				String[] users = userList.get(i);
				User user = new User();
				user.setUserName(users[0]);
				user.setPassword(users[1]);
				user.setAge(Integer.parseInt(users[2]));
				allUsers.add(user);
			}
		} catch (IOException e) {
			logger.info("读取excel文件失败", e);
		}
    	param.put("allUsers", allUsers);
		this.userService.insertUsers(param);
    }
    
    /**
     * 
     * @param req
     * @param resp
     */
    @RequestMapping("/writeExcel")
    public void writeExcel(HttpServletRequest req,HttpServletResponse resp){
    	//第一步：创建一个workBook，对应一个excel文件
    	HSSFWorkbook wb = new HSSFWorkbook();
    	//第二步：在workBook中创建一个工作表，对应一个sheet
    	HSSFSheet sheet = wb.createSheet("用户信息表");
    	//第三部：创建单元格字体样式
    	Font font = wb.createFont();
    	//颜色
    	font.setColor(HSSFFont.COLOR_RED);
    	//粗体
    	font.setBoldweight(Font.BOLDWEIGHT_BOLD);
    	
    	//创建单元格样式，并设置单元格样式居中
    	HSSFCellStyle style = wb.createCellStyle();
    	//水平居中
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	//垂直居中
    	style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    	
    	
    	
    	
    	
    	
    }
    @RequestMapping("/sendMessage")
    public void send(@RequestParam("message")String message,@RequestParam("name")String name,HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	PrintWriter writer = resp.getWriter();
    	try {
			if(name.equals("queue")){
				queueProducer.send(message);
			}else{
				topicProducer.send(message);
			}
			writer.print("1");
		} catch (Exception e) {
			writer.print(e.getCause().toString());
		}
    }
}
