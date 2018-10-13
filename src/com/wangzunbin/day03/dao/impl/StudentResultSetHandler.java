package com.wangzunbin.day03.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Student;
import com.wangzunbin.day03.dao.IResultSetHandler;

public class StudentResultSetHandler implements IResultSetHandler<List<Student>> {

	@Override
	public List<Student> handle(ResultSet rs) throws Exception {
		List<Student> list = new ArrayList<>();
		while (rs.next()) {
			Student stu = new Student();
			stu.setId(rs.getLong("id"));
			stu.setName(rs.getString("name"));
			stu.setAge(rs.getInt("age"));
			list.add(stu);
		}
		return list;
	}

}
