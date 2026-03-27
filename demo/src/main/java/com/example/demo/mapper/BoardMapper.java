package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Board;

@Mapper
public interface BoardMapper {

	public List<Board> selectBoardList(HashMap<String,Object> map); //[Board{},Board{}..]
	
}
