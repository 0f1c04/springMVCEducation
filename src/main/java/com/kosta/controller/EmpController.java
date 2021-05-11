package com.kosta.controller;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpServiceInterface;
import com.kosta.model.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpController {

	@Autowired
    EmpServiceInterface empService;
	
	@Autowired
	DeptServiceInterface deptService;
	
	@RequestMapping("/emp/emplist.do")
	public String emplist(Model model) {
		model.addAttribute("emp_all", empService.selectAll());
		return "emp/emplist"; //forward
	}
	
	@RequestMapping("/emp/empInsert.do")
	public String empinsert(Model model) {
		model.addAttribute("jlist", empService.selectAllJobs());
		model.addAttribute("dlist", deptService.findAll());
		model.addAttribute("mlist", deptService.findAllManager());
		return "emp/empinsert"; //forward
	}

	@RequestMapping(value = "/emp/empInsert.do", method = RequestMethod.POST)
	public String empinsertPost(EmpVO emp) {
		System.out.println(emp);
		empService.insertEmp(emp);
		return "redirect:/emp/emplist.do"; //재요청
	}

	@RequestMapping("/emp/empDetail.do")
	public String empDetailGet(Model model, int empid) {
		model.addAttribute("emp", empService.selectById(empid));
		model.addAttribute("jlist", empService.selectAllJobs());
		model.addAttribute("dlist", deptService.findAll());
		model.addAttribute("mlist", deptService.findAllManager());

		return "emp/empdetail"; //forward
	}

	@RequestMapping(value = "/emp/empDetail.do", method = RequestMethod.POST)
	public String empDetailPost(EmpVO emp) {
		System.out.println(emp);
		empService.updateEmp(emp);

		return "redirect:/emp/emplist.do"; //forward
	}

	@RequestMapping("/emp/empDelete.do")
	public String empDeleteGet(int empid) {
		empService.deleteEmp(empid);

		return "redirect:/emp/emplist.do"; //forward
	}

}