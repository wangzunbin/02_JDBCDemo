package com.wangzunbin.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {


	private static Properties p = new Properties();
	
	static {
	
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("db.properties");
			p.load(stream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName(p.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("数据库驱动加载失败", e);
		}
	}
	
	public static Connection getConn() {
		try {
		
			return DriverManager.getConnection(p.getProperty("url"),
					p.getProperty("username"), 
					p.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("数据库连接异常");
	}
	
	public static void close(Connection conn, Statement st, ResultSet rs) {
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
}
