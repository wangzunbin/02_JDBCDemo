package com.wangzunbin.day02._01_smis.test;

import java.util.List;

import org.junit.Test;

import com.wangzunbin.day01._05_smis.dao.IStudentDAO;
import com.wangzunbin.day01._05_smis.dao.impl.StudentDAOImpl;
import com.wangzunbin.day01._05_smis.domain.Student;

public class StudentDaoTest {

	@Test
	public void save() {
		IStudentDAO dao = new StudentDAOImpl();
		Student student = new Student();
		student.setAge(18);
		student.setName("盖伦");
		dao.save(student);
	}
	
	@Test
	public void update() {
		IStudentDAO dao = new StudentDAOImpl();
		Student newStu = new Student();
		newStu.setAge(41);
		newStu.setName("新的");
		dao.update(6L, newStu);
	}
	
	@Test
	public void delte() {
		IStudentDAO dao = new StudentDAOImpl();
		dao.delete(5L);
	}
	
	@Test
	public void querysingle() {
		IStudentDAO dao = new StudentDAOImpl();
		Student student = dao.get(2L);
		System.out.println(student);
	}

	@Test
	public void queryAll() {
		IStudentDAO dao = new StudentDAOImpl();
		List<Student> list = dao.list();
		System.out.println(list);
	}
}
