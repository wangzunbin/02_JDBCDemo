package com.wangzunbin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Student;

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
	
	public static List<Student> query(String sql, Object... params){
		
	}
}

