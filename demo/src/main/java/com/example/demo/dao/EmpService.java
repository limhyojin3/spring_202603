package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.model.Emp;

@Service
public class EmpService {
	@Autowired 
	EmpMapper empMapper;
	
	// 조회 -> get, 수정 -> edit, 삽입 -> add, 삭제 -> remove
	// ex) 학생목록 : getStudentList, 학생수정 -> editStudent
	
	// === Mapper 호출 시 === 
	// 여러개 리턴 -> selectXXXList
	//	List<User> list = defaultMapper.selectUserList();
	// 한개 리턴 -> selectXXX
	//	User info = defaultMapper.selectUser();
	// 수정, 삭제, 삽입 -> updateXXX, deleteXXX, insertXXX
	//	int result = defaultMapper.updateXXX();
	
	public HashMap<String, Object> getEmpList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Emp> list = empMapper.selectEmpList(map);
			int totalCount = empMapper.selectEmpCount(map); 
			
			
//			User info = defaultMapper.selectUser(map);
//			int result = defaultMapper.updateXXX(map);
			
			
			resultMap.put("list", list);
			resultMap.put("totalCount", totalCount);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_SEARCH);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> addEmp(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();//공간생성
		
		
		
		try {
			int mgrCnt = empMapper.checkMgrExists((String) map.get("mgrNo"));
			int deptCnt = empMapper.checkDeptExists((String) map.get("deptNo"));
			
			if(mgrCnt > 0 && deptCnt > 0) {
				int result = empMapper.insertEmp(map);
				
				resultMap.put("result", "success");
				resultMap.put("message", Message.MSG_ADD);
			} else {
				throw new Exception(Message.MSG_SERVER_ERR);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	
	
}