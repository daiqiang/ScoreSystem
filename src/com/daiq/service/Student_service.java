package com.daiq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.daiq.dao.Student_daoImpl;
import com.daiq.dto.Ss_student;

@Component("Student_service")
public class Student_service {
	
	@Resource(name="Student_dao")
	Student_daoImpl student_daoImpl;
	
	public void printStudent(){
		System.out.println("print success");
	}
	
	@Transactional
	public boolean insertStudent(Ss_student student){
		student_daoImpl.insertStudent(student);
		int i = 1/0;
		return false;
	}
	
	public String selectPasswordByStudentno(String studentno){
		return student_daoImpl.selectPasswordByStudentno(studentno);
	}
}
