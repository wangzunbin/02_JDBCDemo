package com.wangzunbin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wangzunbin.day03.dao.IResultSetHandler;

public class JdbcTemplate {
	
	public static int update(String sql, Object ... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, ps, null);
		}
		throw new RuntimeException("更新出错");
	}
	
	/**
	 * DQL操作模板
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T>T query(String sql, IResultSetHandler<T> rsh, Object... params){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			// 设置占位符参数
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i, params[i]);
			}
			rs = ps.executeQuery();
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return null;
	}
}

