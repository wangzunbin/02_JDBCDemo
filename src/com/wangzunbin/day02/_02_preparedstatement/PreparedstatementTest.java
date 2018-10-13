package com.wangzunbin.day02._02_preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com.wangzunbin.util.JdbcUtil;

public class PreparedstatementTest {

	//如果不是预编译的话, 会出现sql 注入
	/**
	 * 下面的是测试, 如果你修改什么数据, 都可以登陆成功
	 * @throws Exception
	 */
	@Test
	public void testLoginByStatement() throws Exception{
		String sql = "select * from t_student where name = '' or 1=1 or '' AND age = '1'";
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next()) {
			System.out.println("登陆成功");
		} else {
			System.out.println("登陆失败");
		}
		JdbcUtil.close(conn, st, rs);
	}
	
	// 下面的由于采用PrepareStatement, 就笔面试上面的问题
	@Test
	public void testLoginByPrepareStatement() throws Exception{
		String sql = "select * from t_student where name = ? AND age = ?";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "'or 1 = 1 pr'");
		ps.setInt(2, 1);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("登陆成功");
		} else {
			System.out.println("登陆失败");
		}
		JdbcUtil.close(conn, ps, rs);
	}
}
