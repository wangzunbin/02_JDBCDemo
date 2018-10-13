package com.wangzunbin.day02._04_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.Statement;

import org.junit.Test;

import com.wangzunbin.util.JdbcUtil;

public class BatchTest {

	// 需求: 同事向t_student插入5000条数据
	/**
	 * 使用statement完成, 没有批处理
	 * 完成时间为5846;
	 */
	@Test
	public void testSaveByStatement() throws Exception{
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_student (name, age) values('A', " + i + ")";
			st.executeUpdate(sql);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(conn, st, null);
	}
	
	/**
	 * 使用statement完成, 批处理
	 * 完成时间为5953
	 */
	@Test
	public void testBatchSaveByStatement() throws Exception{
		Connection conn = JdbcUtil.getConn();
		Statement st = conn.createStatement();
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_student (name, age) values('A', " + i + ")";
			st.addBatch(sql);
			if (i % 200 == 0) {
				st.executeBatch(); // 执行批量操作
				st.clearBatch();	// 清除批处理
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(conn, st, null);
	}
	

	/**
	 * 使用preparedStatement完成, 没有批处理
	 * 5751
	 * @throws Exception
	 */
	@Test
	public void testSaveByPrepareStatement() throws Exception{
		Connection conn = JdbcUtil.getConn();
		String sql = "insert into t_student (name, age) values('A', ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			st.setInt(1, i);
			st.executeUpdate();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(conn, st, null);
	}
	
	/**
	 * 使用preparedStatement完成, 批处理
	 * 5610
	 * @throws Exception
	 */
	@Test
	public void testBatchSaveByPrepareStatement() throws Exception{
		Connection conn = JdbcUtil.getConn();
		String sql = "insert into t_student (name, age) values('A', ?)";
		PreparedStatement st = conn.prepareStatement(sql);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			st.setInt(1, i);
			st.addBatch();
			if (i % 200 == 0) {
				st.executeBatch(); // 执行批量操作
				st.clearBatch(); // 清除批处理
				st.clearParameters(); // 清除占位符参数
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end - begin);
		JdbcUtil.close(conn, st, null);
	}
}
