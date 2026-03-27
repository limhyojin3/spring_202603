package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BoardService;
import com.example.demo.dao.DefaultService;
import com.google.gson.Gson;

@Controller
public class BoardController {

	
	@Autowired
	BoardService boardService; 
	
	//--------------- [board] ------------------------------------------
	
	
	@RequestMapping("/board/list.do")
	public String test2(Model model) throws Exception{
		return "/board/board-list"; 
	}
	
	@RequestMapping(value = "/board/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String test(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap = boardService.getBoardList(map);
		System.out.println(map);
		//resultMap={"list":[Board{},Board{},...], "message":...}
		
		return new Gson().toJson(resultMap); //resultMap을 담아서 ajax로 반환한다.
	} 
	
	
}
