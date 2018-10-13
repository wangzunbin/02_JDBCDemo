package com.wangzunbin.day02._03_tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.wangzunbin.util.JdbcUtil;

public class TransactionTest {

	
	/**
	 * 下面是由于执行到中间, 掉电了, 提莫已经扣了钱, 但是盖伦没有收到
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		Connection conn = JdbcUtil.getConn();
		String sql = "select * from account where name = ? and balance >= ? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "提莫");
		ps.setInt(2, 1000);
		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			throw new RuntimeException("余额不足");
		}
		// 3.从提莫的账户余额中减少1000
		sql = "update account set balance = balance - ? where name = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, 1000);
		ps.setString(2, "提莫");
		ps.executeUpdate();
		/*
		 *	模拟提点
		 */
		int a = 1 / 0;
		// 4. 再给盖伦账户余额里面增加1000
		sql = "update account set balance = balance + ? where name = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, 1000);
		ps.setString(2, "盖伦");
		ps.executeUpdate();
		JdbcUtil.close(conn, ps, rs);
		
	}
	
	
	/**
	 * 增加事务管理, 会回滚代码
	 * @throws Exception
	 */

	@Test
	public void test2() throws Exception{
		Connection conn = JdbcUtil.getConn();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = "select * from account where name = ? and balance >= ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "提莫");
			ps.setInt(2, 1000);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new RuntimeException("余额不足");
			}
			// 设置取消事务的自动提交
			conn.setAutoCommit(false);
			// 3.从提莫的账户余额中减少1000
			sql = "update account set balance = balance - ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "提莫");
			ps.executeUpdate();
			/*
			 *	模拟提点
			 */
			int a = 1 / 0;
			// 4. 再给盖伦账户余额里面增加1000
			sql = "update account set balance = balance + ? where name = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1000);
			ps.setString(2, "盖伦");
			ps.executeUpdate();
			// 提交事务
			conn.commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally {
			JdbcUtil.close(conn, ps, rs);
		}
		
		
	}
}
