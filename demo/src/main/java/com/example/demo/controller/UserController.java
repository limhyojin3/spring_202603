package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.UserService;
import com.google.gson.Gson;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	
	//-------------- [login] ------------------------------------------
	@RequestMapping("/login.do") //브라우저
	public String test(Model model) throws Exception{
		return "/user/login"; //.jsp
	}

	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
									//ajax가 파라미터로 보낸값 map =>{userId=user02, pwd=pw02}
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		//System.out.println(map); //{userId=user02, pwd=pw02}
		
		
		resultMap = userService.login(map); //x001
		
		return new Gson().toJson(resultMap); 
	}
	
	//----------------- [join] ----------------------------------------
	@RequestMapping("/join.do")
	public String join(Model model) throws Exception{
		return "/user/sign-up";
	}
	@RequestMapping(value = "/join.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
									//ajax가 파라미터로 보낸값 map =>{userId=user02, pwd=pw02}
	@ResponseBody
	public String join(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		//System.out.println(map); //{userId=user02, pwd=pw02}
		
		
		resultMap = userService.addUser(map); //x001
		
		return new Gson().toJson(resultMap); 
	}
	
	
	
	
	
	
	
	
	
	
	//--------------- [check] -----------------------------------------
	
	@RequestMapping(value = "/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
									//ajax가 파라미터로 보낸값 map
	@ResponseBody
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		//System.out.println(map); //{userId=user99, pwd=pw99, userName=규}
		
		
		resultMap = userService.checkUser(map); //x001
		
		return new Gson().toJson(resultMap); 
	}
	
	
}
