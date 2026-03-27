package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.DefaultService;
import com.google.gson.Gson;

@Controller
public class DefaultController {

	
	@Autowired
	DefaultService defaultService; //디폴트서비스
	
	//------------ [default] -------------------------------------------
	
	@RequestMapping("/default.do")
	public String test(Model model) throws Exception{
		return "/default";
	}
	@RequestMapping(value = "/default.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		return new Gson().toJson(resultMap); 
	}
	//--------------- [test] ------------------------------------------
	
	
	//http://localhost:8080/test.do
	@RequestMapping("/test.do")
	public String test2(Model model) throws Exception{
		return "/test";  //"webapp/WEB-INF/test.jsp" 를 의미한다 !
	}
	
	@RequestMapping(value = "/test.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String test(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = defaultService.getUserList();
		
		return new Gson().toJson(resultMap); 
	}
	
	
}
