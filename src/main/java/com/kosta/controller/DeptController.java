package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.business.DeptServiceImpl;
import com.kosta.model.DeptDAO;
import com.kosta.model.DeptVO;
import com.kosta.model.EmpDAO;

@Controller
public class DeptController {

	@Autowired
	@Qualifier("deptService")
	DeptServiceImpl service;
	
	@RequestMapping("/dept/dept_retrieve.do")
	public String deptlist(Model mydata) {
		mydata.addAttribute("deptall", service.findAll());
		return "dept/dept_retrieve";
	}
	//insert
	@RequestMapping("/dept/deptInsert.do")
	public String deptInsertGet(Model mydata) {
		mydata.addAttribute("mlist", service.findAllManager());
		mydata.addAttribute("loclist", service.findAllLocation());
		
		return "dept/deptInsert";
	}
	
	@RequestMapping(value="/dept/deptInsert.do", method = RequestMethod.POST)
	public String deptInsertPost(DeptVO dept) {
		service.insert(dept);
		return "redirect:/dept/dept_retrieve.do";
	}
	
	
	@RequestMapping("/dept/deptDetail.do")
	public String deptDetailGet(Model model, int deptid) {
		model.addAttribute("dept", service.findById(deptid));
		return "dept/deptDetail";
	}
	
	@RequestMapping(value="/dept/deptDetail.do", method = RequestMethod.POST)
	public String deptDetailPost(DeptVO dept) {
		service.update(dept);
		return "redirect:/dept/dept_retrieve.do";
	}
	
	@RequestMapping("/dept/deptDelete.do")
	public String deptDelete(int deptid) {
		service.delete(deptid);
		return "redirect:/dept/dept_retrieve.do";
	}
	
	@RequestMapping("dept/transactionTest.do")
	@ResponseBody
	public String insertUpdate() {
		DeptVO newDept = new DeptVO(17, "kkk", 100, 1700);
		DeptVO oldDept = new DeptVO(1, "test2", 777, 1700);
		service.insertUpdate(newDept, oldDept);
		return "dept/transactionTest.do....OK";
	}
	
}



