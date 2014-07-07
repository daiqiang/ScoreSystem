package com.daiq.dao;

import com.daiq.dto.Ss_student;

public interface Student_dao {
	public boolean insertStudent(Ss_student student); 
	public String selectPasswordByStudentno(String studentno);
}
