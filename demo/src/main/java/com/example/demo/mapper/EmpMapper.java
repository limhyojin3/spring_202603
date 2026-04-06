package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Emp;
import com.example.demo.model.User;

@Mapper
public interface EmpMapper {
	
	// 여러개 리턴 -> selectXXXList
	public List<Emp> selectEmpList(HashMap<String, Object> map);
	// 한개 리턴 -> selectXXX
	public Emp selectEmp(HashMap<String, Object> map);
	
	public int checkMgrExists(String mgrNo);
	
	public int checkDeptExists(String deptNo);
	
	public int selectEmpCount(HashMap<String, Object> map);
	
	
	
	
	// 삭제 
	public int deleteEmp(HashMap<String, Object> map);
	// 수정
	public int updateEmp(HashMap<String, Object> map);
	// 삽입 
	public int insertEmp(HashMap<String, Object> map);
}