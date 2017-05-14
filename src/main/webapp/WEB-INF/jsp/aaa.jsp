<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'aaa.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">

  </head>
  
  <body>
    <div class="container">
	  <div class="jumbotron">
	    <h1>我的第一个 Bootstrap 页面</h1>
	    <p>重置窗口大小，查看响应式效果！</p> 
	  </div>
	  <div class="row">
	    <div class="col-sm-4">
	      <h3>Column 1</h3>
	      <p>学的不仅是技术，更是梦想！</p>
	      <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
	    </div>
	    <div class="col-sm-4">
	      <h3>Column 2</h3>
	      <p>学的不仅是技术，更是梦想！</p>
	      <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
	    </div>
	    <div class="col-sm-4">
	      <h3>Column 3</h3> 
	      <p>学的不仅是技术，更是梦想！</p>
	      <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
	    </div>
	  </div>
	</div>
  </body>
  <script src="<%=basePath%>resources/js/jquery-12.1.js"></script>
  <script type="text/javascript" src="<%=basePath%>resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  
</html>
