package com.kosta.springmvcproject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/friendlist.do")
	public String test(Model model) {
		List<String> flist = new ArrayList<String>();
		flist.add("πË¿Áøµ");
		flist.add("¿”ªÛ«ı1");
		flist.add("¿”ªÛ«ı2");
		flist.add("¿”ªÛ«ı3");
		flist.add("¿”ªÛ«ı4");
		
		model.addAttribute("flist", flist);
		return "friendlist";
	}
	
	@RequestMapping("/friendlist2.do")
	public ModelAndView test2() {
		
		ModelAndView mv = new ModelAndView();
		List<String> flist = new ArrayList<String>();
		flist.add("πË¿Áøµ");
		flist.add("¿”ªÛ«ı5");
		flist.add("¿”ªÛ«ı6");
		flist.add("¿”ªÛ«ı7");
		flist.add("¿”ªÛ«ı8");
		
		mv.addObject("flist",flist);
		mv.setViewName("friendlist");
		return mv;
	}
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
