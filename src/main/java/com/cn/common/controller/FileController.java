package com.cn.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cn.base.PlatformBaseClass;
import com.cn.eagle.service.FileService;
import com.cn.entity.FileDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/file")
public class FileController extends PlatformBaseClass{
	@Autowired
	private FileService fileService;
	
	@RequestMapping("/downLoadFile")
	public void downLoadFile(HttpServletRequest req,HttpServletResponse resp,FileDTO fileDTO) throws IOException{
		logger.info("/file/downLoadFile");
		PrintWriter pw = null;
		resp.setContentType("text/html,charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		try {
			String path = req.getSession().getServletContext().getRealPath("/files")+ File.separator +fileDTO.getFilePath();
			File dir = new File(path);
			if (!dir.exists()) {
				pw=resp.getWriter();
				pw.print("<script>alert('抱歉,文件不存在');location.href='javascript:history.go(-1)';</script>);");
				return;
			}
			FileInputStream in = new FileInputStream(path);
			OutputStream fos = resp.getOutputStream();
			resp.reset();
			resp.setContentType("application/x-download");
			resp.setHeader("Content-disposition", "attachment;filename="+getFileName(req,fileDTO.getFileName()));
			byte[] b = new byte[2048];
			int read;
			while ((read = in.read(b)) != -1) {
				fos.write(b,0,read);
			}
			fos.flush();
			in.close();
			fos.close();
		 } catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			pw=resp.getWriter();
			pw.print("<script>alert('抱歉,文件下载失败');location.href='javascript:history.go(-1)';</script>);");
	     }
	}
	public String getFileName(HttpServletRequest req,String channelFileName) throws UnsupportedEncodingException{
		String fileName="";
		String userAgent = req.getHeader("User-Agent"); 
		 //针对IE或者以IE为内核的浏览器,加上win10自带的Edge浏览器
		 if (userAgent.contains("MSIE")||userAgent.contains("Trident")||userAgent.contains("Edge")) {
		 fileName = java.net.URLEncoder.encode(channelFileName, "UTF-8");
		} else {
		 //非IE浏览器的处理：
		 fileName = new String(channelFileName.getBytes("UTF-8"),"ISO-8859-1");
		}
		 return fileName;
	}
	
    @RequestMapping("/uploadFile")
	public void uploadFile(
			@RequestParam(value = "uploadify", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileName = file.getOriginalFilename();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//本机测试路径
		String path = request.getSession().getServletContext().getRealPath("files");
		String uuid = UUID.randomUUID().toString();
		String filePath = uuid + fileName.substring(fileName.lastIndexOf("."));
		File targetFile = new File(path, filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFileName(fileName);
			fileDTO.setFilePath(filePath);
			fileService.insertSelective(fileDTO);
			dataMap.put("filePath", filePath);
			dataMap.put("fileName", fileName);
			//jsonMap.put("data", dataMap);
			logger.info("文件上传成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//JSONResponseUtil.buildCustomJSONDataResponse(response, jsonMap);
		ObjectMapper mapper = new ObjectMapper();
		String data =mapper.writeValueAsString(dataMap);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(data);
	}
    public static void main(String [] args) {  
    	try {
    		/*
    		 * File类
    		 */
	    	/*String directory = "D:/Workspaces/abc"; //指定文件路径  
	        File filedirectory = new File(directory); //创建文件对象  
	        if(!filedirectory.exists()){
	        	filedirectory.mkdirs();//创建文件目录
	        }
	        File file = new File(filedirectory,"123.txt");
	        if(!file.exists()){
				file.createNewFile();//创建文件
	        }*/
    		/*
    		 * Files类
    		 * import java.nio.file.Files;
			 * import java.nio.file.Path;
			 * import java.nio.file.Paths;
    		 */
    		String directory = "D:/Workspaces/abc"; //指定文件路径  
	        Path filedirectory = Paths.get(directory); //创建文件对象  
	        if(Files.exists(filedirectory)){
	        	Files.createDirectories(filedirectory);//创建文件目录
	        }
	        filedirectory = filedirectory.resolve("123.txt");//到这一步还没有在本地生成文件
	        File file = new File("D:/Workspaces/abc/123.txt");
	        if(file.exists()){
	        	System.out.println("文件名称：" + file.getName());  
		        System.out.println("文件是否存在：" + file.exists());  
		        System.out.println("文件是否隐藏："+file.isHidden());
		        System.out.println("路径是否是绝对路径："+file.isAbsolute());
		        System.out.println("文件的相对路径：" + file.getPath());  
		        System.out.println("文件的绝对路径：" + file.getAbsolutePath());  
		        System.out.println("文件可以执行："+file.canExecute());
		        System.out.println("文件可以读取：" + file.canRead());  
		        System.out.println("文件可以写入：" + file.canWrite());  
		        System.out.println("文件上级路径：" + file.getParent());  
		        System.out.println("上级文件名：" + file.getParentFile().getName());  
		        System.out.println("文件大小：" + file.length() + "B");  
		        System.out.println("文件最后修改时间：" + new Date(file.lastModified()));  
		        System.out.println("是否是文件类型：" + file.isFile());  
		        System.out.println("是否是文件夹类型：" + file.isDirectory());
	        }
	        File file2 = new File("D:/Workspaces/1111.txt"); //该文件已存在
	        //FileCopyUtils.copy(file2, file);
	        FileUtils.copyFile(file2, file);
	        System.out.println("文件大小：" + file.length() + "B");
	        file.renameTo(new File("D:/Workspaces/abc/321.txt"));
	        File file3 = new File(directory);
	        File[] file4 = file3.listFiles();
	        for(File file5:file4){
	        	System.out.println(file5.getName());
	        	file5.delete();
	        }
	        file3.delete();
	        ListRoots();
	        //File f =new File("d:\\Workspaces");
	        //showDir(f);
	        fileFilter();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    //列出磁盘下的文件和文件夹
    public static void ListRoots(){
    	 File[] files =File.listRoots();
	     for(File file:files){
	         System.out.println(file);
	         if(file.length()>0){
	             String[] filenames =file.list();
	             for(String filename:filenames){
	                 System.out.println(filename);
	             }
	         }
	     }
    }
    //利用递归列出全部文件
    public static void showDir(File dir){
        System.out.println(dir);
        File[] files =dir.listFiles();
        for(File file:files){
            if(file.isDirectory())
                showDir(file);
            else
                System.out.println(file);
        }
    }
    //文件过滤,列出磁盘根目录下的txt文件
    public static void fileFilter(){
	     File[] files =File.listRoots();
	     for(File file:files){
	         System.out.println(file);
	         if(file.length()>0){
	             String[] filenames =file.list(new FilenameFilter(){
	                 //file 过滤目录 name 文件名
	                 public boolean accept(File file,String filename){
	                     return filename.endsWith(".txt");
	                 }
	             });
	             for(String filename:filenames){
	                 System.out.println(filename);
	             }
	          }
	      }
     }

}
