package com.example.demo.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;
	
	
	//--------------------------------------------------------------
	public HashMap<String, Object> login(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		
		User user = userMapper.selectUser(map); //유저가 있거나 없거나(null)
		
		
		if(user != null) {
			
			//ooo 님 환영합니다 !
			
			if(user.getPwd().equals(map.get("pwd"))) {
				resultMap.put("message", user.getUserName() + "님 환영합니다 !");
			} else {
				resultMap.put("message", "비밀번호를 확인해주세요.");
			}
			
			
			
		} else {
			resultMap.put("message", "없는 아이디 입니다.");
		}
		
		resultMap.put("result", "success");
		
		//hashmap(resultMap) = {"message":"ooo 님 환영합니다 !", "result":"success"}
		
		return resultMap;  //x001
	}
	//--------------------------------------------------------------
	public HashMap<String, Object> addUser(HashMap<String, Object> map){
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
		
		try {
			
			int cnt = userMapper.insertUser(map); //영향받은 행의갯수
			
			//** try에서 에러터지면 바로 catch로 간다. 
			//그래서 resultMap.put("message", "회원가입 실패. 다시 시도해주세요."); 이 실행될수없음!
			
			if(cnt > 0) {
				resultMap.put("message", "회원가입 축하!");
			} else {
				resultMap.put("message", "회원가입 실패. 다시 시도해주세요.");
			}
			
			resultMap.put("result", "success");
			
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러 발생! \n 잠시후 다시 시도해주세요.");
			resultMap.put("result", "fail");
		}
		
		
		return resultMap;  //x001
		//hashmap(resultMap)={"message":"서버에러발생!", "result":"fail"}
		//hashmap(resultMap)={"message":"회원가입 축하!", "result":"success"}
	}
	//--------------------------------------------------------------
		public HashMap<String, Object> checkUser(HashMap<String, Object> map){
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>(); //공간 생성
			
			
			try {
				
				User user = userMapper.selectUser(map); //유저가 있거나 없거나(null)
				
				
				if(user != null) {
					resultMap.put("message", "이미 사용중인 아이디 입니다.");
					resultMap.put("result", false);
				} else {
					resultMap.put("message", "사용가능한 아이디 입니다.");
					resultMap.put("result", true);
				}
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				resultMap.put("message", "서버 에러.");
				
				
			}
			//hashmap(resultMap)={"message":"이미 사용중인 아이디 입니다.", "result":false}
			
			return resultMap;  //x001
		}
}
