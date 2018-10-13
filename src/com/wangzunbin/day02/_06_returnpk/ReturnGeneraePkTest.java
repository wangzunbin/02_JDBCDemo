package com.wangzunbin.day02._06_returnpk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.wangzunbin.util.JdbcUtil;

/**
 * 获取输入数据的主键
 * @author Administrator
 *
 */
public class ReturnGeneraePkTest {

	@Test
	public void testStatement() throws SQLException {
		String sql = "insert into t_student(name, age) values('小倩', 19)";
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = st.getGeneratedKeys(); // 获取自动生产成的主键
		if (rs.next()) {
			long pk = rs.getLong(1);
			System.out.println(pk);
		}
		JdbcUtil.close(conn, st, rs);
	}
	
	@Test
	public void testPreParedStatement() throws SQLException {
		String sql = "insert into t_student(name, age) values(?, ?)";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, "宁cai成");
		ps.setInt(2, 23);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			long pk = rs.getLong(1);
			System.out.println("返回的主键:" + pk);
		}
		JdbcUtil.close(conn, ps, rs);
	}
}
