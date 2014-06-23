package com.daiq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daiq.dto.Ss_student;
import com.daiq.service.Student_service;



public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ss_student student = new Ss_student();
		student.setStudentno("004");
		student.setName("xiaoming");
		student.setSex("1");
		student.setClassno("999");
		student.setPassword("12345");
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"config/beans.xml"});
		Student_service student_service = (Student_service)context.getBean("Student_service");
		student_service.insertStudent(student);
		
	}

}
