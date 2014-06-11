package com.daiq.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ss_student")
public class Ss_student {
	String studentno;
	String name;
	String sex;
	String classno;
	String password;
	
	@Id
	public String getStudentno() {
		return studentno;
	}
	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getClassno() {
		return classno;
	}
	public void setClassno(String classno) {
		this.classno = classno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
