package com.wangzunbin.day01._05_smis.test;

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
		student.setAge(245);
		student.setName("张三");
		dao.save(student);
	}
	
	@Test
	public void update() {
		IStudentDAO dao = new StudentDAOImpl();
		Student newStu = new Student();
		newStu.setAge(41);
		newStu.setName("李四");
		dao.update(3L, newStu);
	}
	
	@Test
	public void delte() {
		IStudentDAO dao = new StudentDAOImpl();
		dao.delete(3L);
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
