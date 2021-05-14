package com.kosta.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpService;
import com.kosta.model.EmpVO;
import com.kosta.util.ConvertUtil;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	@Autowired
	DeptServiceInterface deptService;
	
	Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@RequestMapping("/emp/emplist.do")
	public String emplist(Model model) {
		logger.warn("emplist.do��û~~ warn");
		logger.info("emplist.do��û~~ info");
		logger.debug("emplist.do��û~~ debug");
		
		
		model.addAttribute("emplist", empService.selectAll());
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/empInsert.do")
	public String empInsert(Model model) {
		model.addAttribute("dlist", deptService.findAll());
		model.addAttribute("mlist", deptService.findAllManager());
		model.addAttribute("jlist", empService.selectAllJobs());
		return "emp/empInsert"; //forward�� �����Ǿ� ����
	}
	
	@RequestMapping(value = "/emp/empInsert.do", method = RequestMethod.POST)
	public String empInsertPost(EmpVO emp) {
		System.out.println(emp);
		empService.insertEmp(emp);
		return "redirect:/emp/emplist.do"; //���û
	}
	
	@RequestMapping("/emp/empDetail.do")
	public String empDetailGet(Model model, int empid) {
		model.addAttribute("emp", empService.selectbyId(empid));
		model.addAttribute("dlist", deptService.findAll());
		model.addAttribute("mlist", deptService.findAllManager());
		model.addAttribute("jlist", empService.selectAllJobs());
		
		return "emp/empDetail"; //forward�� �����Ǿ� ����
	}
	@RequestMapping(value="/emp/empDetail.do", method=RequestMethod.POST)
	public String empDetailPost(EmpVO emp) {
		empService.updateEmp(emp);
		
		return "redirect:/emp/emplist.do"; //forward�� �����Ǿ� ����
	}
	@RequestMapping("/emp/empDelete.do")
	public String empDelete(int empid) {
		empService.deleteEmp(empid);
		
		return "redirect:/emp/emplist.do"; 
	}
	
	//�ּ�: http://localhost:9090/springmvcproject/emp/login.do?empid=100&email=SKING
	//produces�� �̿��� �ѱ��� ������ �ʰ� ���� �� ����.
	@RequestMapping(value = "/emp/login.do", produces = {"text/html;charset=utf-8"})
	@ResponseBody //browser�� ���乮���� ����. body�� ���
	public String empLogin(int empid, String email) {
		EmpVO emp = empService.loginChk(empid, email);
		String message = "<h1>�������� �ʴ� ������Դϴ�</h1>";
		if(emp != null) message = emp.toString();
		return emp.toString();
	}
	
	
	
	@RequestMapping("/login/loginForm.do")
	public String loginFormGet() {
		return "/login/loginForm";
	}
	@RequestMapping(value = "/login/login3.do", method = RequestMethod.POST)
	public String loginPost(int userid, String userpw, HttpSession session) {
		EmpVO emp = empService.loginChk(userid, userpw);
		System.out.println(emp);
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
		model.addAttribute("emplist", emplist);
		System.out.println(emplist);
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectBySalary.do")
	public String selectBySalary(Model model, int minsal, int maxsal) {
		List<EmpVO> emplist = empService.selectBySalary(minsal, maxsal);
		model.addAttribute("emplist", emplist);
		return "emp/emplist";
	}
	
	
	@RequestMapping("/emp/selectByHireDate.do")
	public String selectByHireDate(Model model, String sdate, String edate) {
		List<EmpVO> emplist = empService.selectByHireDate(sdate, edate);
		model.addAttribute("emplist", emplist);
		return "emp/emplist";
	}
	
	
	@RequestMapping("/emp/selectByHireDate2.do")
	public String selectByHireDate2(Model model, Date sdate, Date edate) {
		List<EmpVO> emplist = empService.selectByHireDate2(sdate, edate);
		model.addAttribute("emplist", emplist);
		return "emp/emplist";
	}
	
	
	@RequestMapping("/emp/selectByCondition.do")
	public String selectByCondition(Model model, String deptid, String jobid, String sal, String hdate, String chk) {
		//System.out.println(chk);
		int dept = "".equals(deptid)?0:Integer.parseInt(deptid);
		int salary = "".equals(sal)?0:Integer.parseInt(sal);
		if(chk==null) hdate=null;
		Date hiredate = null;
		if(hdate!="" && hdate!=null)
			hiredate = ConvertUtil.convertDate(hdate);
		List<EmpVO> emplist = empService.selectByCondition(dept, jobid, salary, hiredate);
		model.addAttribute("emplist", emplist);
		return "emp/emplist";
	}
	
	@RequestMapping("/emp/selectByDeptMany.do")
	public String selectByDeptMany(Model model, int[] deptlist) {
		System.out.println(Arrays.toString(deptlist));
		List<Integer> dlist = new ArrayList<>();
		for(Integer dept:deptlist) {dlist.add(dept);}
		
		List<EmpVO> emplist = empService.selectByDeptMany(dlist);
		model.addAttribute("emplist", emplist);
		return "emp/emplist";
	}
	
	
	
	
	
}


