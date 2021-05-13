package com.kosta.controller;

import com.kosta.business.EmpServiceInterface;
import com.kosta.model.EmpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp2/*")
public class EmpTestRestController {

    @Autowired
    EmpServiceInterface empService;

    @RequestMapping(value = "/emplist.do", produces = {"text/html;charset=UTF-8"})
    public String test1() {
        return "<h1>직원리스트입니다.</h1>";
    }

    @RequestMapping(value = "/emp.do")
    public EmpVO test2() {
        EmpVO emp = empService.selectById(101);
        return emp;
    }

    @RequestMapping(value = "/emplist3.do")
    public List<EmpVO> test3() {
        List<EmpVO> emplist = empService.selectAll();
        return emplist;
    }

    @RequestMapping(value = "/emplist4.do")
    public Map<Integer, EmpVO> test4() {
        List<EmpVO> emplist = empService.selectAll();
        Map<Integer, EmpVO> map = new HashMap<>();
        emplist.forEach(emp->{
            map.put(emp.getEmployee_id(), emp);
        });
        return map;
    }

    @RequestMapping(value = "/emp5.do/{empid}", method = RequestMethod.GET)
    public EmpVO test5(@PathVariable("empid") int eid) {
        EmpVO emp = empService.selectById(eid);
        return emp;
    }

}
