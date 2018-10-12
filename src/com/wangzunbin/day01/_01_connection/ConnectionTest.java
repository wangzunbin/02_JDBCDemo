package com.wangzunbin.day01._01_connection;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class ConnectionTest {

	// 需求: 创建一张t_student表: id/name/age
	@Test
	public void testCreateTable() throws Exception {
		String sql = "CREATE TABLE t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), age INT)";
		// 1. 加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2. 获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
		// 3.创建语句对象
		Statement statement = connection.createStatement();
		// 4. 执行sql
		int rows = statement.executeUpdate(sql);
		System.out.println(rows);
		statement.close();
		connection.close();

	}

	// 1. 正常处理异常和关闭资源
	@Test
	public void testHandleException() {
		String sql = "CREATE TABLE t_student(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), age INT)";
		Connection connection = null;
		Statement statement = null;
		// 1. 加载注册驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 获取连接对象
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
			// 3.创建语句对象
			statement = connection.createStatement();
			// 4. 执行sql
			int rows = statement.executeUpdate(sql);
			System.out.println(rows);
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		}

	}

	// DML操作: 仅仅只是sql不同
	@Test
	public void testInsert() throws Exception {
		// DMl
		String sql = "INSERT INTO t_student(name, age) VALUES('王尊斌', 33)";
		String sql2 = "UPDATE t_student SET name = '王尊斌', age = 34 WHERE id = 2";
		String sql3 = "DELETE FROM t_student WHERE id = 1";

		// 1.加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
		// 3.创建语句对象
		Statement st = connection.createStatement();
		// 4. 执行sql
		int rows = st.executeUpdate(sql);
//		int rows = st.executeUpdate(sql2);
//		int rows = st.executeUpdate(sql3);
		System.out.println("成功操作:   " + rows);
		// 5.释放资源
		st.close();
		connection.close();
	}

	// 查询t_student表中有多少个数据
	@Test
	public void testGetCount() throws Exception {
		String sql = "select COUNT(*) from t_student";
		// 1.加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
		// 3.创建语句对象
		Statement st = connection.createStatement();
		ResultSet resultSet = st.executeQuery(sql);
		System.out.println(resultSet);
		if (resultSet.next()) {
			long totalCount = resultSet.getLong(1);
//			long totalCount = resultSet.get;
			System.out.println(totalCount);
		}
		resultSet.close();
		st.close();
		connection.close();
	}

	// 查询某个数据
	@Test
	public void testQueryAll() throws Exception {
		String sql = "select * from t_student";
		// 1.加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
		// 3.创建语句对象
		Statement st = connection.createStatement();
		ResultSet resultSet = st.executeQuery(sql);
		while (resultSet.next()) {
			String name = resultSet.getString("name");
			long id = resultSet.getLong("id");
			int age = resultSet.getInt("age");
			System.out.println(name + ", " + id + ", " + age);
		}
		resultSet.close();
		st.close();
		connection.close();
	}

	// 查询某个数据
	@Test
	public void testQuerySingle() throws Exception {
		String sql = "select * from t_student where id = 2";
		// 1.加载注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		// 2.获取连接对象
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdemo", "root", "123");
		// 3.创建语句对象
		Statement st = connection.createStatement();
		ResultSet resultSet = st.executeQuery(sql);
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			long id = resultSet.getLong("id");
			int age = resultSet.getInt("age");
			System.out.println(name + ", " + id + ", " + age);
		}
		resultSet.close();
		st.close();
		connection.close();
	}
}
