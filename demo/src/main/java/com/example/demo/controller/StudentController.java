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
	
	//--------------- [stu-add] ------------------------------------
	
	@RequestMapping("/stu-add.do") 
	public String add(Model model) throws Exception{
		return "/student/stu-add"; 
	}
	
	@RequestMapping(value = "/stu-add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간만 생성
		
		resultMap = studentService.addStudent(map); //stuNo을 넘겨준다
		//hashmap(resultMap)={"message":"추가되었습니다!", "result":"success"}
		
		
		
		
		return new Gson().toJson(resultMap); //데이터반환도 해시맵 형태인거임
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------- [stu-check] ------------------------------------
	
	@RequestMapping(value = "/stu-check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	
								//@RequestParam map => ajax에서 파라미터로 넘어온값(stuNo)
	@ResponseBody
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간만 생성
		//System.out.println(map); //{stuNo=1234}
		
		
		resultMap = studentService.getStudent(map); //stuNo을 넘겨준다
		//retultMap={"message":"사용가능한 학번입니다.", "result":"success"}
		
		return new Gson().toJson(resultMap); 
	}
	
	
	//**@RequestMapping (ajax .dox) 랑 @ResponseBody 는 한묶음이다.
	//   => 위치상 바로 위아래에 있어야함.
	
	
	
	
	
	
	
	
	
	
	
	
	
	//------------- [stu-list] ----------------------------------------
					//브라우저로 접속할 주소
	@RequestMapping("/stu-list.do") 
	public String test(Model model) throws Exception{
		return "/student/stu-list"; //"WEB-INF/student/stu-list.jsp" 주소를 의미함 !
	}
	
	
					//ajax로 호출할 주소
	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간생성
		
		resultMap = studentService.getStudentList(map); //x002
		
		return new Gson().toJson(resultMap); 
	}
	
	
	//---------------- [stu-remove] --------------------------------------
	
	
	@RequestMapping(value = "/stu-remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	
									//@RequestParam map => ajax에서 파라미터로 넘어온값(stuNo)
	@ResponseBody
	public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간만 생성
		//System.out.println(map);
		
		resultMap = studentService.removeStudent(map);//x001
		
		return new Gson().toJson(resultMap); 
	}
	
}
