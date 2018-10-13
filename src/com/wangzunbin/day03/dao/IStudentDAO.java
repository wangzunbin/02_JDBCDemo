package com.wangzunbin.day03.dao;

import java.util.List;

import com.wangzunbin.day01._05_smis.domain.Student;

public interface IStudentDAO {

	void save(Student student);
	
	void delete(Long id);
	
	void update(Long id, Student newStu);
	
	Student get(Long id);
	
	List<Student> list();
}
