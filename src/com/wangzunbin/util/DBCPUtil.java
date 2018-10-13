package com.wangzunbin.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBCPUtil {

	private static DataSource dataSource;
	static {
		try {
			Properties p = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream inputStream = loader.getResourceAsStream("dbcp.properties");
			p.load(inputStream);
//			dataSource = BasicDataSourceFactory.createDataSource(p);
			dataSource = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			throw new RuntimeException("加载classpath根路径下的dbcp资源文件失败", e);
		}
	}

	public static Connection getConn() {
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new RuntimeException("数据库连接异常");
	}
}
