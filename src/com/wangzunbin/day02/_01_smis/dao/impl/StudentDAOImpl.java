package com.wangzunbin.day02._01_smis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.dao.IStudentDAO;
import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO {

	public void save(Student student) {
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

	public void delete(Long id) {
		String sql = "delete from t_student  where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}

	public void update(Long id, Student newStu) {
		String sql = "update  t_student set name=?, age= ? where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			ps.setString(1, newStu.getName());
			ps.setInt(2, newStu.getAge());
			ps.setLong(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
	}

	public Student get(Long id) {
		Student student = new Student();
		String sql = "select * from t_student where id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				student.setAge(rs.getInt("age"));
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			JdbcUtil.close(conn, ps, rs);

		}
		return student;
	}

	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		String sql = "select * from t_student";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 2. 获取连接对象
			conn = JdbcUtil.getConn();
			// 3.创建语句对象
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setAge(rs.getInt("age"));
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				list.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}

}
