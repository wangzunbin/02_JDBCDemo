package com.wangzunbin.day01._05_smis.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.dao.IStudentDAO;
import com.wangzunbin.day01._05_smis.domain.Student;

public class StudentDAOImpl implements IStudentDAO {

	public void save(Student student) {
		String sql = "insert  t_student (id, name, age) values(null, '" + student.getName() +"', " + student.getAge() +")";
		Connection conn = null;
		Statement st = null;

		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
	}

	public void delete(Long id) {
		String sql = "delete from t_student  where id = " + id;
		Connection conn = null;
		Statement st = null;

		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
	}

	public void update(Long id, Student newStu) {
		String sql = "update  t_student set name='" + newStu.getName() +"', age= " + newStu.getAge() +" where id = " + id;
		Connection conn = null;
		Statement st = null;

		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (st != null) {
					st.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
	}

	public Student get(Long id) {
		Student student = new Student();
		String sql = "select * from t_student where id = " + id;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				student.setAge(rs.getInt("age"));
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}

		}
		return student;
	}

	public List<Student> list() {
		List<Student> list = new ArrayList<>();
		String sql = "select * from t_student";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1. 加载注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			st = conn.createStatement();
			rs = st.executeQuery(sql);
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
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
			}

		}
		return list;
	}

}
