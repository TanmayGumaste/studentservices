package com.firstattempt.springboot.studentservices.dao;

import java.util.List;
import java.util.Optional;

import Model.Student;
import exception.StudentException;

public interface StudentDao {

	
	public List<Student> listStudent();
	
	public Student createStudentAcc(Student student) throws StudentException;


	public <T> List<T> select(String queryString);

	void insert(String queryString, Object[] params);
	
	public int getAccountId(String queryString, Object[] params);
	public <T> List<T> selectWithParams(String queryString, Object[] params);	
	public List<Object> getEmail(String queryString,Object[] params);
}
