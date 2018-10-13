package com.wangzunbin.day03.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.wangzunbin.util.DBCPUtil;
import com.wangzunbin.util.JdbcUtil;

public class Test1 {

/*	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jdbcdemo");
		ds.setUsername("root");
		ds.setPassword("123");
		return ds;
	}*/
	
	@Test
	public void test1() throws Exception{
		Connection conn = DBCPUtil.getConn();
		String sql = "select * from t_student";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String name = rs.getString("name");
			int age = rs.getInt("age");
			System.out.println(name + ", " + age);
			
		}
		JdbcUtil.close(conn, ps, rs);
	}
}
