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

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

	
	@Autowired
	BoardService boardService; 
	
	//--------------- [view] -------------------------------------------
					//페이지에 접속
	@RequestMapping("/board/view.do")							//해시맵 넘어옴 {boardNo=14}
	public String view(HttpServletRequest request, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);  //{boardNo=14}
		
		
		
		
										//-> 매개변수로 넘어온 해시맵에서 키로 접근해서 value값 얻음
		request.setAttribute("boardNo", map.get("boardNo")); 
		//**리퀘스트 객체에 {boardNo : boardNo} 를 담아서 다음 페이지(board-view)에 전달
		
		
		return "/board/board-view"; //board-view.jsp 파일 실행됨.
		
		
		
		//페이지를 여는 목적 (do) 
		//db에서 가공된데이터를 얻기위한 목적 (dox) : ajax
		
		
		
	}
	
	
	@RequestMapping(value = "/board/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String info(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		System.out.println(map);
		resultMap = boardService.getBoard(map);
		
		
		return new Gson().toJson(resultMap); //resultMap을 담아서 ajax로 반환한다.
	} 
	
	
	//--------------- [add] --------------------------------------------
	@RequestMapping("/board/add.do")
	public String add(Model model) throws Exception{
		return "/board/board-add"; 
	}
	@RequestMapping(value = "/board/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap = boardService.addBoard(map);
		System.out.println(map);
		
		
		return new Gson().toJson(resultMap); //resultMap을 담아서 ajax로 반환한다.
	} 
	
	
	
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
	
	//--------------- [edit] ------------------------------------------
	@RequestMapping("/board/edit.do")							//해시맵 넘어옴 {boardNo=14}
	public String edit(HttpServletRequest request, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);   //HttpServletRequest request : 리퀘스트 객체 사용할거야~ 라는 뜻
		//{boardNo=14}
		
		
		
										//-> 매개변수로 넘어온 해시맵에 키로 접근해서 value값 얻음
		request.setAttribute("boardNo", map.get("boardNo")); 
		//**리퀘스트 객체에 {boardNo : boardNo} 를 담아서 다음 페이지(board-edit)에 전달
		
		
		return "/board/board-edit"; //board-edit.jsp 파일 실행됨.
		
	
	}
	
	@RequestMapping(value = "/board/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
									
	
										
	@ResponseBody
	public String edit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
									//-> ajax를 통해 map으로 넘김
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간생성
		System.out.println(map);
		
		resultMap = boardService.editBoard(map);
		
		
		return new Gson().toJson(resultMap); //resultMap을 담아서 ajax로 반환한다.
	} 
}
