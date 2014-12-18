<%@ page language="java" import="java.util.*,javax.servlet.http.Cookie" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//记住用户名、密码 
//String flag = (String)session.getAttribute("flag")==null?"":(String)session.getAttribute("flag");
String name = "";
String password = "";
Cookie[] cookies = request.getCookies();
if(cookies!=null){
	for(int i=0;i<cookies.length;i++){
	    if(cookies[i].getName().equals("cookie_user")){
		    String value =  cookies[i].getValue();
		    if(value!=null && !"".equals(value) && value.split("^").length>0){
		    	String[] values = value.split("\\^");
		        name=values[0];
		        if(values[1]!=null && !values[1].equals("null")){
		        	password=values[1];
		    	}
		    }
	    }
	}
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="commonJs/jquery-1.8_min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
     		$("#username").val('<%=name%>');
     		$("#password").val('<%=password%>');
		});
		
		function isRemember(flag){
			if(flag.checked){
				flag.value="1";
			}else{
				flag.value="0";
			}
		}
	</script>
  </head>
  
  <body>
    This is login page. <br>
    <form id="form" action="loginAction" method="post">
    	学号/工号：<input id="username" name="username" type="text"/><br/>
    	密码：<input id="password" name="password" type="password"/><br/>
    	<label><input type="checkbox" name="flag" id="flag" onclick="isRemember(this)" checked  value ="1" />记住我的登陆状态</label><br/><br/>
    	<input type="submit" value="提交"/>
    </form>
  </body>
</html>
