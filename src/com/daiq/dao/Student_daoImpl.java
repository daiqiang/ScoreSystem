package com.daiq.dao;

import org.springframework.stereotype.Component;

import com.daiq.dto.Ss_student;

@Component("Student_dao")
public class Student_daoImpl extends baseDao implements Student_dao {

	public boolean insertStudent(Ss_student student) {
		super.getClient().insert("Ss_studentSqlMap.insertStudent",student);
		System.out.println("insert success");
		return true;
	}
	
	public String selectPasswordByStudentno(String studentno){
		System.out.println("select success");
		return (String) super.getClient().queryForObject("Ss_studentSqlMap.getStudentPassword",studentno);
	}
}
