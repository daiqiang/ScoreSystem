package com.daiq.dao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import com.daiq.dto.Ss_student;

//@Component
public class Student_daoImpl extends SqlMapClientDaoSupport implements Student_dao{

	public boolean insertStudent(Ss_student student) {
		getSqlMapClientTemplate().insert("Ss_studentSqlMap.insertStudent",student);
		System.out.println("insert success");
		return true;
	}

}
