package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.EmpService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@RequestMapping("/emp-list.do") 
	public String test(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/emp/emp-list";
	}
	
	@RequestMapping(value = "/emp-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String test(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		int pageSize = Integer.parseInt((String)map.get("pageSize"));
		int offSet = Integer.parseInt((String)map.get("offSet"));
		
		map.put("pageSize", pageSize);
		map.put("offSet",offSet);
		
		resultMap = empService.getEmpList(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping("/emp-add.do") 
	public String add(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		return "/emp/emp-add";
	}
	@RequestMapping(value = "/emp-add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		resultMap = empService.addEmp(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	@RequestMapping("/emp-view.do") 
	public String view(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		
		//1.pageChange해서 매개변수(해시맵) 넘겨받고
		//2.넘겨받은 해시맵을 setAttribute 한다음에 jsp 파일로 보내줘야함. 
		// 넘겨줄때 setAttribute 필수!** 중요!
		request.setAttribute("empNo", map.get("empNo")); 
		
		return "/emp/emp-view";
	}
	
	@RequestMapping(value = "/emp-view.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String view(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		resultMap = empService.getEmp(map);
		
		return new Gson().toJson(resultMap); 
	}
	
	
	@RequestMapping(value = "/emp-remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String remove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		resultMap = empService.removeEmp(map);
		
		return new Gson().toJson(resultMap); 
	}
	
}