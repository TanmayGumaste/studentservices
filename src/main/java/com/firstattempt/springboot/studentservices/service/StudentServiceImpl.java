package com.firstattempt.springboot.studentservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstattempt.springboot.studentservices.dao.StudentDao;

import Model.Student;
import exception.StudentException;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentDao studentDAO;
	

	@Override
	public Student createStudent(Student student) throws StudentException {		
		studentDAO.createStudentAcc(student);		
		return student;
	}


	

}
