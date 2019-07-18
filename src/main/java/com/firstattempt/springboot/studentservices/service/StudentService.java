package com.firstattempt.springboot.studentservices.service;

import org.springframework.stereotype.Service;

import Model.Student;
import exception.StudentException;

@Service
public interface StudentService {

	public Student createStudent(Student student) throws StudentException;
	
}
