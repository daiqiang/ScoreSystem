package com.daiq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.daiq.dao.Student_daoImpl;
import com.daiq.dto.Ss_student;

@Component("Student_service")
public class Student_service {
	
	@Resource(name="Student_dao")
	Student_daoImpl student_daoImpl;
	
	public void printStudent(){
		System.out.println("print success");
	}
	
	public boolean insertStudent(Ss_student student){
		return student_daoImpl.insertStudent(student);
	}
	
	public String selectPasswordByStudentno(String studentno){
		return student_daoImpl.selectPasswordByStudentno(studentno);
	}
}
