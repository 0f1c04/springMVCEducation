package com.kosta.controller;

import com.kosta.business.DeptServiceImpl;
import com.kosta.model.DeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DeptController {
    @Autowired
    DeptServiceImpl deptService;

    @RequestMapping("/dept/alldeptlist.do")
    public String allDeptList(Model mydata) {
        mydata.addAttribute("deptlist", deptService.findAll());
        return "dept/dept_retrieve";
    }

    @RequestMapping("/dept/deptDetail.do")
    public String deptDetailGet(Model model, int deptid) {
        model.addAttribute("dept", deptService.findByID(deptid));
        return "dept/deptDetail"; //forward
    }

    @RequestMapping(value = "/dept/deptDetail.do", method = RequestMethod.POST)
    public String deptDetailPost(DeptVO dept) {
        System.out.println(dept);
        deptService.update(dept);
        return "redirect:/dept/alldeptlist.do"; //재요청
    }

    @RequestMapping("/dept/deptDelete.do")
    public String deptDeleteGet(int deptid) {
        System.out.println(deptid);
        deptService.delete(deptid);

        return "redirect:/dept/alldeptlist.do"; //재요청
    }

    @RequestMapping("/dept/deptInsert.do")
    public String deptinsert(Model model) {
        model.addAttribute("loclist", deptService.findAllLocation());
        model.addAttribute("deptlist", deptService.findAll());
        model.addAttribute("managerlist", deptService.findAllManager());
        return "dept/deptInsert"; //forward
    }

    @RequestMapping(value = "/dept/deptInsert.do", method = RequestMethod.POST)
    public String deptinsertPost(DeptVO dept) {
        System.out.println(dept);
        deptService.insert(dept);
        return "redirect:/dept/alldeptlist.do"; //재요청
    }
}
