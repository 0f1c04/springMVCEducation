package net.spring.controller;

import com.kosta.model.DeptVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "hello.do", method = RequestMethod.GET)
public class TestController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView helloGet() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "hello.do를 요청...GET");
        mv.addObject("dept", new DeptVO(10, "개발부", 100, 1700));
        mv.setViewName("test/helloForm");
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView helloPost() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "hello.do를 요청...POST");
        mv.addObject("dept", new DeptVO(10, "개발부", 100, 1700));
        mv.setViewName("test/helloResult");
        return mv;
    }
}
