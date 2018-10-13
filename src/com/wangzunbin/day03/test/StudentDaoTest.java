package com.wangzunbin.day03.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.junit.Test;

import com.wangzunbin.day01._05_smis.dao.IStudentDAO;
import com.wangzunbin.day01._05_smis.dao.impl.StudentDAOImpl;
import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.util.JdbcUtil;

public class StudentDaoTest {

	@Test
	public void save() {
		IStudentDAO dao = new StudentDAOImpl();
		Student student = new Student();
		student.setAge(18);
		student.setName("盖伦");
		
		String sql = "insert  t_student (id, name, age) values(null,?,? )";
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.executeUpdate(); //这里是没有参数
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}
	
	@Test
	public void delte() {
		String sql = "delete from t_student  where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			ps.setLong(1, 5L);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}

}
