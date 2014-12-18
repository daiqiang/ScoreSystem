package com.daiq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.daiq.dao.Student_dao;
import com.daiq.dto.Ss_student;

@Component("Student_service")
public class Student_service {
	
	@Resource(name="Student_dao")
	Student_dao student_daoImpl;
	
	/*
	@Resource(name="txTemplate")
	org.springframework.transaction.support.TransactionTemplate txTemplate;
	*/
	
	@Resource(name="transactionManager")
	org.springframework.jdbc.datasource.DataSourceTransactionManager txManager;
	
	@Resource(name="txDefinition")
	org.springframework.transaction.support.DefaultTransactionDefinition txDefinition;
	
	public void printStudent(){
		System.out.println("print success");
	}
	
	
	//@Transactional(rollbackFor=Exception.class) 
	//@Transactional
	public boolean insertStudent(Ss_student student){
		
		TransactionStatus status = txManager.getTransaction(txDefinition);
		
		try {
			student_daoImpl.insertStudent(student);
			int i = 1/0;
			//student_daoImpl.insertStudent(student);
			System.out.println("insertStudent success");
		} catch (RuntimeException e) {
			txManager.rollback(status);
			e.printStackTrace();
			return false;
		}
		txManager.commit(status);
		return true;
	}
	
	public String selectPasswordByStudentno(String studentno){
		return student_daoImpl.selectPasswordByStudentno(studentno);
	}
}
