package com.kosta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.business.EmpService;
import com.kosta.model.EmpVO;

@RestController //@Controller + @ResponseBody
public class LoginController {
	
	@Autowired
	EmpService empService;
	
	
	@RequestMapping(value = "/emp/login2.do")
	public EmpVO empLogin2(int empid, String email) {
		
		EmpVO emp = empService.loginChk(empid, email);
		System.out.println(emp);
		return emp;
	}
	
	@RequestMapping(value = "/emp/emplist2.do")
	public List<EmpVO> emplist2(){
		List<EmpVO> emplist = empService.selectAll();
		return emplist;
	}
	
}
