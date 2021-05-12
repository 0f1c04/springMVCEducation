package com.kosta.controller;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpServiceInterface;
import com.kosta.model.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	@RequestMapping(value = "/emp/login.do", produces = {"text/plain", "application/json"})
	@ResponseBody
	public String empLogin(int id, String email) {
		EmpVO emp = empService.loginChk(id, email);
		String message = "<h1>존재하지 않은 사용자입니다.</h1>";
		if(emp != null) message = emp.toString();
		return message;
	}

	@RequestMapping("/login/loginForm.do")
	public String loginFormGet() {
		return "login/loginForm";
	}

	@RequestMapping(value = "/login/login3.do", method = RequestMethod.POST)
	public String loginPost(int userid, String userpw, HttpSession session) {
		EmpVO emp = empService.loginChk(userid, userpw);
		if(emp!=null) {
			session.setAttribute("loginemp", emp);
			return "redirect:/emp/emplist.do";
		}else {
			return "redirect:/login/loginForm.do";
		}
	}

	@RequestMapping("/emp/selectByDept.do")
	public String selectByDept(Model model, int deptid) {
		List<EmpVO> emplist = empService.selectByDept(deptid);
		model.addAttribute("emp_all", emplist);
		return "emp/emplist";
	}

	@RequestMapping("/emp/selectBySalary.do")
	public String selectBySalary(Model model, int minsal, int maxsal) {
		List<EmpVO> emplist = empService.selectBySalary(minsal, maxsal);
		model.addAttribute("emp_all", emplist);
		return "emp/emplist";
	}

	@RequestMapping("/emp/selectByDate.do")
	public String selectByDate(Model model, String sdate, String edate) {
		List<EmpVO> emplist = empService.selectByDate(sdate, edate);
		model.addAttribute("emp_all", emplist);
		return "emp/emplist";
	}

	@RequestMapping("/emp/selectByDate2.do")
	public String selectByDate2(Model model, Date sdate, Date edate) {
		List<EmpVO> emplist = empService.selectByDate2(sdate, edate);
		model.addAttribute("emp_all", emplist);
		return "emp/emplist";
	}

	@RequestMapping("/emp/selectByCondition.do")
	public String selectByCondition(Model model, String deptid, String jobid, String sal, Date hdate, String chk) {
		System.out.println(chk);
		int dept = "".equals(deptid)?0:Integer.parseInt(deptid);
		int sal2 = "".equals(sal)?0:Integer.parseInt(sal);
		if(chk == null) {
			hdate = null;
		}
		List<EmpVO> emplist = empService.selectByCondition(dept, jobid, sal2, hdate);
		model.addAttribute("emp_all", emplist);
		return "emp/emplist";
	}

	@RequestMapping("/emp/selectByDeptMany.do")
	public String selectByDeptMany(Model model, Integer[] deptlist) {
		System.out.println(Arrays.toString(deptlist));
		List<EmpVO> elist = empService.selectByDeptMany(Arrays.asList(deptlist));
		model.addAttribute("emp_all", elist);
		return "emp/emplist";
	}
}