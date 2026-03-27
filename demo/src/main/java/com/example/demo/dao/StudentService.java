package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;

//Service도 어노테이션 필수
//서비스가 많은일을 한다고 보면된다. 

@Service
public class StudentService {
	
	@Autowired
	StudentMapper studentMapper; 
	//-------------------------------------------------
	
	public HashMap<String, Object> getStudent(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();  //공간생성
		
		
		
		Student info = studentMapper.selectStudent(map);
		
		if(info != null) {
			resultMap.put("message", "이미 사용중인 학번입니다.");
			resultMap.put("result", "fail");
		} else {
			resultMap.put("message", "사용 가능한 학번입니다.");
			resultMap.put("result", "success");
		}
		
		
		
		return resultMap;
		
		//retultMap={"message":"사용가능한 학번입니다.", "result":"success"}
	}
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------
	public HashMap<String, Object> getStudentList(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();  //공간생성
		
		
		
		List<Student> list = studentMapper.selectStudentList(map);
		
		resultMap.put("list", list);
		resultMap.put("message", "데이터 조회 성공");
		resultMap.put("result", "success");
		
		// hashmap = {"list":[!@#!], "message" :"데이터조회성공", "result":"success"}
		
		return resultMap;
	}
	//-------------------------------------------------
	
												//map에는 ajax에서 파라미터로 보낸 stuNo이 있음
	public HashMap<String, Object> removeStudent(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		
		int cnt = studentMapper.deleteStudent(map);
		
		if (cnt > 0){
			resultMap.put("message", "데이터 삭제 성공");
		} else {
			resultMap.put("message", "데이터 삭제 실패");
		}
		
		resultMap.put("result", "success");
		
		//hashmap = {"message":"데이터삭제성공", "result":"success"}
		
		return resultMap;  //x001
	}
	
	
	public HashMap<String, Object> addStudent(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		
		
		
		try {
			int cnt = studentMapper.insertStudent(map);
			
			resultMap.put("message", "추가되었습니다!");
			resultMap.put("result", "success");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			resultMap.put("message", "서버 에러 발생!!");
			resultMap.put("result", "fail");
		}
		
		//hashmap(resultMap)={"message":"추가되었습니다!", "result":"success"}
		
		return resultMap;  
	}
}
