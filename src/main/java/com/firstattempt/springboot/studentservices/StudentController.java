package com.firstattempt.springboot.studentservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.firstattempt.springboot.studentservices.service.StudentService;

import Model.Student;
import Model.StudentResponse;
import exception.StudentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Student Service")
@RequestMapping("/api/v1")
@RestController
public class StudentController {
	
	@Autowired
	StudentService studentservice;

	@RequestMapping(value = "/createStudent", method = RequestMethod.POST, produces = "application/json") 
	public ResponseEntity<Object> StudentCreate(@RequestBody Student student){
		try{
		studentservice.createStudent(student);		
		StudentResponse response=new StudentResponse();
		response.setMessage("ACCOUNT CREATED");
		response.setCode("200");		
		return new ResponseEntity<Object>(student,HttpStatus.OK);}
		
		catch(StudentException e){
			StudentResponse response=new StudentResponse();
			response = returnStudentResponse(response, e.getMessage(), "", "FAILURE");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			
		}
		catch(Exception e){
			StudentResponse response=new StudentResponse();
			response.setMessage("unknown error");
			response.setCode("500");
			response.setStatus("FAILURE");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			
		}
	}
		
	@RequestMapping(value = "/getStudent", method = RequestMethod.POST, produces = "application/json") 
	public ResponseEntity<Object> GetStudentId(@RequestBody String student_ID){
		
		
		
		
		return null;
	}

	private StudentResponse returnStudentResponse(StudentResponse response, String message, String code,
			String status) {
		response.setMessage(message);
		response.setCode(code);
		response.setStatus(status);
		return response;

	}
			

}
