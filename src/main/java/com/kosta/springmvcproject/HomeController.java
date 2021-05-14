package com.kosta.springmvcproject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.business.DeptServiceInterface;
import com.kosta.business.EmpService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	EmpService empService;
	
	@Autowired
	DeptServiceInterface deptService;
	
	
	@RequestMapping("/friendlist2.do")
	public ModelAndView test(Model model) {
		ModelAndView mv = new ModelAndView();
		List<String> flist = new ArrayList<>();
		flist.add("¼öÁö2");
		flist.add("½Â±â2");
		flist.add("Çöºó2");
		model.addAttribute("flist",flist);
		mv.setViewName("friendlist");
		return mv;
		
	}
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("dlist", deptService.findAll());
		model.addAttribute("jlist", empService.selectAllJobs());
		model.addAttribute("mlist", deptService.findAllManager());
		
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
