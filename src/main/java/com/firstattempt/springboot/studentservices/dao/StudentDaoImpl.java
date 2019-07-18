package com.firstattempt.springboot.studentservices.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import Model.Student;
import exception.StudentException;
@Component
@Configuration
public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao{
	@Autowired
	DataSource dataSource;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@PostConstruct
	void init() {
		setDataSource(dataSource);
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}
	
	@Value("${INSERT_STUDENT}")
	protected String INSERT_STUDENT;
	@Value("${SELECT_ALL}")
	protected String SELECT_ALL;
	@Value("${SELECT_ACC_ID}")
	protected String SELECT_ACC_ID;	
	@Value("${SELECT_EMAIL}")
	protected String SELECT_EMAIL;

	@Override
	public List<Student> listStudent() {
		
		return null;
	}
	
	@Override
	public void insert(String queryString, Object[] params) {
		getJdbcTemplate().update(queryString, params);
	}
	
	@Override
	public <T> List<T> select(String queryString) {
		List list = getJdbcTemplate().queryForList(queryString);
		return (List<T>) list;
	}

	@Override
	public int getAccountId(String queryString, Object[] params) {
		try {
			int accountId = getJdbcTemplate().queryForObject(queryString, params, Integer.class);
			return accountId;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
	
	@Override
	public Student createStudentAcc(Student student) throws StudentException {		
		 List<Object> email=(getEmail(SELECT_EMAIL, new Object[]{student.getEmail()}));
		 if(!email.isEmpty()){
			 throw new StudentException("USER ALREADY EXISTS");
		 }
		insert(INSERT_STUDENT,student.getAccountObjectArray(student));
		int acc_id=getAccountId(SELECT_ACC_ID,new Object[]{student.getEmail()});
		student.setStudent_Id(acc_id);
		return student;
		}

	@Override
	public List<Object> getEmail(String queryString, Object[] params) {	
		Optional<String> email=null;
	  return selectWithParams(queryString, params);
	}	

	@Override
	public <T> List<T> selectWithParams(String queryString, Object[] params) {
		List<Map<String, Object>> list = getJdbcTemplate().queryForList(queryString, params);
		return (List<T>) list;
	}
	

}
