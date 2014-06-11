package com.daiq.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		
		if("dai".equals(username)&&"123".equals(password)){
			return SUCCESS;
		}else{
			return "fail";
		}
		
		
	}
}
