package com.wangzunbin.day03.test;

import org.junit.Test;

import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.day03.dao.IStudentDAO;
import com.wangzunbin.day03.dao.impl.StudentDAOImpl;
import com.wangzunbin.util.JdbcTemplate;

/**
 * 重构
 * @author Administrator
 *
 */
public class StudentDaoTest2 {

	@Test
	public void save() {
		IStudentDAO dao = new StudentDAOImpl();
		Student student = new Student();
		student.setAge(18);
		student.setName("盖伦");
		dao.save(student);
	}
	
	@Test
	public void delte() {
		IStudentDAO dao = new StudentDAOImpl();
		dao.delete(19l);
	}
	
	@Test
	public void update() {
		IStudentDAO dao = new StudentDAOImpl();
		Student student = new Student();
		student.setAge(19);
		student.setName("盖伦kj");
		dao.update(17L, student);
	}
}
