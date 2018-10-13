package com.wangzunbin.day02._05_blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.stream.FileImageInputStream;

import org.junit.Test;

import com.wangzunbin.util.JdbcUtil;

public class BlobTest {

	// 把二进制数据存储到表中
	@Test
	public void testWrite() throws Exception{
		String sql = "insert into t_image(content) values(?)";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setBlob(1, new FileInputStream("e:/pic1.png"));
		ps.executeUpdate();
	}
	
	//把额二进制的二进制读取出来
	@Test
	public void testRead() throws Exception{
		String sql = "select * from t_image where id = ?";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, 1);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Blob blob = rs.getBlob("content");
			InputStream in = blob.getBinaryStream();
			Files.copy(in, Paths.get("e:/123.png"));
		}
	}
}
