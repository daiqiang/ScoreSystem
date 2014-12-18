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
	    
	    String currentURL = req.getRequestURI();//��ȡ��ǰ�ļ������ڱȽ�
	    System.out.println("currentURL="+currentURL);

	    if(currentURL.split("\\.")[currentURL.split("\\.").length-1].equals("jsp")){ //2014-12-18 add by daiq ֻ��jsp����֤�������޷�����js
	    	if("/ScoreSystem/login.jsp".equals(currentURL)){
		    	System.out.println("��ӭ������¼ҳ��");
			    chain.doFilter(req, res);
		    }else{
		    	//��session��ȡ���û�����Ϣ
			    String username = (String) session.getAttribute("username");
			    
			    //�ж����û��ȡ���û���Ϣ,����ת����½ҳ��
			     if (username == null || "".equals(username)) {
			       //��ת����½ҳ��
			    	 System.out.println("doing Filter");
			    	 System.out.println(currentURL);
			       res.sendRedirect("http://"+req.getHeader("Host")+"/ScoreSystem/login.jsp");
			     }
			     else {
			       //�Ѿ���½,�����˴�����
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
