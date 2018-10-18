package com.wangzunbin.day03.test;

import java.util.List;

import org.junit.Test;

import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.day03.dao.IStudentDAO;
import com.wangzunbin.day03.dao.impl.StudentDAOImpl;
import com.wangzunbin.day03.template.BeanListHandler;
import com.wangzunbin.day03.template.HibernateTemplate;
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
	
	@Test
	public void querySingle() {
		IStudentDAO dao = new StudentDAOImpl();
		Student stu = dao.get(19613L);
		System.out.println(stu.toString());
	}
	
	@Test
	public void queryAll() {
		IStudentDAO dao = new StudentDAOImpl();
		List<Student> list = dao.list();
		System.out.println(list.toString());
	}
	
	/*@Test
	public void saveStuForHibernate() throws Exception {
		System.out.println(JdbcTemplate.query("SELECT * FROM t_student", new BeanListHandler<>(Student.class)));;
	}*/
}
