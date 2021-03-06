package com.daiq.action;

import java.io.Reader;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.daiq.service.Student_service;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

@Component("LoginAction_id")
@Scope("prototype")
public class LoginAction extends ActionSupport{
	
	@Resource(name="Student_service")
	private Student_service student_service;
	
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//这个session是服务器的session
		HttpSession session = ServletActionContext.getRequest().getSession();
		String studentno = request.getParameter("username");
		String password = request.getParameter("password");
		String flag = request.getParameter("flag");
		System.out.println(studentno);
		
		/**
		 * 下面这段代码是用hibernate来做持久层进行封装，做完这个没多久在公司里面接手了
		 * 多媒体函数移植的需求，那个项目是用的iBATIS，发现是比hibernate好用的多，后来
		 * 想想，既然公司都是用的iBATIS，我为什么不用呢。于是，下面这段代码就被注释了。
		 * 每一行代码都是自己手动写出来的，所以能注释就不删。
		 */
		/*
		//下面4句话是hibernate的固定写法，只为拿到session，这个session是连接数据库的session
		Configuration configuration = new Configuration().configure();//Hibernate用来获取配置文件的类是Configuration
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();//升级到4.0之后取得session的方法有所改变，hibernate增加了一个注册机ServiceRegistryBuilder 类。要先生成一个注册机对象，然后所有的生成SessionFactory的对象要象注册机注册一下再用
		SessionFactory sessionFactory =configuration.buildSessionFactory(serviceRegistry);
		Session hibernatesession = sessionFactory.openSession();
		
		SQLQuery query = hibernatesession.createSQLQuery("select * from ss_student s where s.studentno=?");
		query.setParameter(0,username);
		//List<Ss_student> lists = (List<Ss_student>)query.addEntity("s", Ss_student.class).list();
		List lists = query.addScalar("password",StringType.INSTANCE).addScalar("studentno",StringType.INSTANCE).list(); //这里很确定只返回一条数据，但是老子就不用uniqueResult()
		Object[] result = null;
		
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				result = (Object[])lists.get(i);//噗~~~~,写到这里，我一口老血喷薄而出，洋洋洒洒吐了40几分钟不曾消停。终于发现，只需把list.get(i)强转成Object[]就能把数据遍历取出来了。
				System.out.println(result[0]);
			}
		}
		
		if(result != null){ //如果用户存在
			if(result[0].equals(password)){ //如果密码正确
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30);
				return SUCCESS;
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
		*/
		
		Reader reader = null;
		String result = null;
		try {
			if(flag!=null && flag.equals("1")){
				Cookie cookie = new Cookie("cookie_user", studentno+"^"+password);
				cookie.setMaxAge(180); //cookie 保存的秒数
				cookie.setPath("/");
				response.addCookie(cookie);
			}else{
				//清空cookie
				Cookie[] cookies = request.getCookies();
				if(cookies!=null){
					for(int i=0;i<cookies.length;i++){
					    if(cookies[i].getName().equals("cookie_user")){
					    	//Cookie cookie = new Cookie(cookies[i].getName(), null);  
					    	cookies[i].setMaxAge(0); //设置cookie为过期
					    	cookies[i].setPath("/");
					    	response.addCookie(cookies[i]);
					    }
					}
				}
			}
			/* 把ibatis交给spring管理起来之后就不用这样去读取配置文件了 modified by daiq 2014-6-24
			reader = Resources.getResourceAsReader("config/sqlMapConfig.xml");
			SqlMapClient sqlMap=SqlMapClientBuilder.buildSqlMapClient(reader);
			System.out.println(sqlMap.getDataSource().getConnection());
			result = (String) sqlMap.queryForObject("Ss_studentSqlMap.getStudentPassword",studentno);
			*/
			result = student_service.selectPasswordByStudentno(studentno);
			
			System.out.println("--------");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(result != null){ //如果用户存在
			if(result.equals(password)){ //如果密码正确
				session.setAttribute("username", studentno);
				session.setMaxInactiveInterval(30);
				return SUCCESS;
			}else{
				return "fail";
			}
		}else{
			return "fail";
		}
	}
}
