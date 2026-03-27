package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper; 
	
	
	//---------------------------------------------------------------
	public HashMap<String, Object> getBoardList(HashMap<String,Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		
		try {
			List<Board> list = boardMapper.selectBoardList(map); //[Board{},Board{}..]
			
			resultMap.put("list", list); 
			resultMap.put("message", "데이터 조회 성공");
			resultMap.put("result", "success");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러!");
			resultMap.put("result", "fail");
		
		}
		
		
		//resultMap={"list":[Board{},Board{},...], "message":...}
		return resultMap;
	}
}
