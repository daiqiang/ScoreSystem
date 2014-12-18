package com.daiq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;
	    HttpSession session = req.getSession(true);
	    
	    String currentURL = req.getRequestURI();//截取当前文件名用于比较
	    System.out.println("currentURL="+currentURL);

	    if(currentURL.split("\\.")[currentURL.split("\\.").length-1].equals("jsp")){ //2014-12-18 add by daiq 只对jsp做验证，否则无法引进js
	    	if("/ScoreSystem/login.jsp".equals(currentURL)){
		    	System.out.println("欢迎来到登录页面");
			    chain.doFilter(req, res);
		    }else{
		    	//从session里取的用户名信息
			    String username = (String) session.getAttribute("username");
			    
			    //判断如果没有取到用户信息,就跳转到登陆页面
			     if (username == null || "".equals(username)) {
			       //跳转到登陆页面
			    	 System.out.println("doing Filter");
			    	 System.out.println(currentURL);
			       res.sendRedirect("http://"+req.getHeader("Host")+"/ScoreSystem/login.jsp");
			     }
			     else {
			       //已经登陆,继续此次请求
			       chain.doFilter(req,res);
			     }
		    }
	    }else{
	    	chain.doFilter(req,res);
	    }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
