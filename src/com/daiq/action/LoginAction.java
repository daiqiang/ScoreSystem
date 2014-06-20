package com.daiq.action;

import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		//���session�Ƿ�������session
		HttpSession session = ServletActionContext.getRequest().getSession();
		String studentno = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(studentno);
		
		/**
		 * ������δ�������hibernate�����־ò���з�װ���������û����ڹ�˾���������
		 * ��ý�庯����ֲ�������Ǹ���Ŀ���õ�iBATIS�������Ǳ�hibernate���õĶ࣬����
		 * ���룬��Ȼ��˾�����õ�iBATIS����Ϊʲô�����ء����ǣ�������δ���ͱ�ע���ˡ�
		 * ÿһ�д��붼���Լ��ֶ�д�����ģ�������ע�;Ͳ�ɾ��
		 */
		/*
		//����4�仰��hibernate�Ĺ̶�д����ֻΪ�õ�session�����session���������ݿ��session
		Configuration configuration = new Configuration().configure();//Hibernate������ȡ�����ļ�������Configuration
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();//������4.0֮��ȡ��session�ķ��������ı䣬hibernate������һ��ע���ServiceRegistryBuilder �ࡣҪ������һ��ע�������Ȼ�����е�����SessionFactory�Ķ���Ҫ��ע���ע��һ������
		SessionFactory sessionFactory =configuration.buildSessionFactory(serviceRegistry);
		Session hibernatesession = sessionFactory.openSession();
		
		SQLQuery query = hibernatesession.createSQLQuery("select * from ss_student s where s.studentno=?");
		query.setParameter(0,username);
		//List<Ss_student> lists = (List<Ss_student>)query.addEntity("s", Ss_student.class).list();
		List lists = query.addScalar("password",StringType.INSTANCE).addScalar("studentno",StringType.INSTANCE).list(); //�����ȷ��ֻ����һ�����ݣ��������ӾͲ���uniqueResult()
		Object[] result = null;
		
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				result = (Object[])lists.get(i);//��~~~~,д�������һ����Ѫ�籡������������������40�����Ӳ�����ͣ�����ڷ��֣�ֻ���list.get(i)ǿת��Object[]���ܰ����ݱ���ȡ�����ˡ�
				System.out.println(result[0]);
			}
		}
		
		if(result != null){ //����û�����
			if(result[0].equals(password)){ //���������ȷ
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
			reader = Resources.getResourceAsReader("config/sqlMapConfig.xml");
			SqlMapClient sqlMap=SqlMapClientBuilder.buildSqlMapClient(reader);
			System.out.println(sqlMap.getDataSource().getConnection());
			result = (String) sqlMap.queryForObject("Ss_studentSqlMap.getStudentPassword",studentno);
			
			System.out.println("--------");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(result != null){ //����û�����
			if(result.equals(password)){ //���������ȷ
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
