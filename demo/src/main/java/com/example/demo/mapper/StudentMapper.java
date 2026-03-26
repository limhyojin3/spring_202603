package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Student;


//Mapper 역할하려면 어노테이션 필수!

@Mapper
public interface StudentMapper {
	
	public List<Student> selectStudentList();
	
	public int deleteStudent(HashMap<String, Object> map); 
							//파라미터로 넘어가는타입이 hashmap
}
