package net.wodud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.model.DeptVO;

@Controller
@RequestMapping("hello.do")
public class TestController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView helloGet() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title","hello.do request. (Get)");
		mv.addObject("dept", new DeptVO(10,"개발부",100,1700));
		mv.setViewName("test/helloForm");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView helloPost() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title","hello.do request. (Post)");
		mv.addObject("dept", new DeptVO(10,"개발부",100,1700));
		mv.setViewName("test/helloResult");
		return mv;
	}
}
