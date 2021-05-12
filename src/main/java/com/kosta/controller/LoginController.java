package com.kosta.controller;

import java.util.List;

import com.kosta.business.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.model.EmpVO;

@RestController //@Controller+@ResponseBoby
public class LoginController {

    @Autowired
    EmpServiceImpl empService;

    //http://localhost:9090/springmvcproject/emp/login2.do?id=100&email=SKING
    @RequestMapping(value = "/emp/login2.do" )
    public  EmpVO empLogin2(int id, String email) {

        EmpVO emp = empService.loginChk(id, email);
        System.out.println(emp);
        return emp;
    }

    @RequestMapping(value = "/emp/emplist2.do" )
    public List<EmpVO> emplist2() {
        List<EmpVO> emplist = empService.selectAll();
        return emplist;
    }


}
