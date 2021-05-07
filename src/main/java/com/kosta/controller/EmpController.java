package com.kosta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.model.DeptDAO;
import com.kosta.model.EmpDAO;

@Controller
public class EmpController {

	@Autowired
	EmpDAO empDAO;
	
	@Autowired
	DeptDAO deptDAO;
	
	@RequestMapping("/emp/emplist.do")
	public String emplist(Model model) {
		model.addAttribute("emp_all", empDAO.selectAll());
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/empInsert.do")
	public String empinsert(Model model) {
		model.addAttribute("jlist", empDAO.selectAllJobs());
		model.addAttribute("dlist", deptDAO.selectAll());
		model.addAttribute("mlist", deptDAO.selectAllManager());
		return "emp/empinsert";
	}
}