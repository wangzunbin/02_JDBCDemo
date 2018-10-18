package com.wangzunbin.day03.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.day03.dao.IStudentDAO;
import com.wangzunbin.day03.template.BeanHandler;
import com.wangzunbin.day03.template.BeanListHandler;
import com.wangzunbin.util.JdbcTemplate;
import com.wangzunbin.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO {

	public void save(Student student) {
		
		String sql = "insert  t_student (id, name, age) values(null,?,? )";
		Object[] params = {student.getName(), student.getAge()};
		JdbcTemplate.update(sql, params);
	}

	public void delete(Long id) {
		String sql = "delete from t_student  where id = ?";
		Object[] params = {id};
		JdbcTemplate.update(sql, params);
	}

	public void update(Long id, Student newStu) {
		String sql = "update  t_student set name=?, age= ? where id = ?";
		Object[] params = {newStu.getName(), newStu.getAge(), id};
		JdbcTemplate.update(sql, params);
	}

	public Student get(Long id) {
		String sql = "select * from t_student where id = ?";
		Student query = JdbcTemplate.query(sql, new BeanHandler<>(Student.class), id);
		return query;
	}

	public List<Student> list() {
		String sql = "select * from t_student";
		return JdbcTemplate.query(sql, new BeanListHandler<>(Student.class));
	}

}
