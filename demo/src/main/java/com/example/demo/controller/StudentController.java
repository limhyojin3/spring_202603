package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.StudentService;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.google.gson.Gson;

//주소의 역할 하려면 Controller 있어야함

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
					//브라우저로 접속할 주소
	@RequestMapping("/stu-list.do") 
	public String test(Model model) throws Exception{
		return "/student/stu-list"; //"WEB-INF/student/stu-list.jsp" 주소를 의미함 !
	}
	//-----------------------------------------------------------
					//ajax로 호출할 주소
	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	//--------------------------------------------------------------
	
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간생성
		
		resultMap = studentService.getStudentList(); //x002
		
		return new Gson().toJson(resultMap); 
	}
	//--------------------------------------------------------------
	//--------------------------------------------------------------
	
	
	
	@RequestMapping(value = "/stu-remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	//--------------------------------------------------------------
									//@RequestParam map => ajax에서 파라미터로 넘어온값(stuNo)
	@ResponseBody
	public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간만 생성
		//System.out.println(map);
		
		resultMap = studentService.removeStudent(map);//x001
		
		return new Gson().toJson(resultMap); 
	}
	
}
