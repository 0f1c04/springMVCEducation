package com.kosta.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kosta.model.UserVO2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.business.EmpService;
import com.kosta.model.EmpVO;

@RestController //@Controller + @ResponseBody
@RequestMapping("/emp2/*")
public class EmpTestRestController {

    @Autowired
    EmpService empService;

    Logger logger = LoggerFactory.getLogger(EmpTestRestController.class);

    @RequestMapping(value = "/emplist.do", produces = {"text/html; charset=UTF-8"})//Content-type,MIMETYPE:text
    public String test1() {
        return "<h1>��������Ʈ�Դϴ�.</h1>";
    }


    //JSON(JavaScript Object Notation) : JavaScript Object������ ���ڿ�{"Ű":"��"}
    @RequestMapping(value = "/emp.do")
    public EmpVO test2() {
        EmpVO emp = empService.selectbyId(101);
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
        emplist.forEach(emp -> {
            map.put(emp.getEmployee_id(), emp);
        });
        return map;
    }

    // {empid}�� /100���� �ָ� ��
    @RequestMapping(value = "/emp5.do/{empid}", method = RequestMethod.GET)
    public EmpVO test5(@PathVariable("empid") int eid) {
        EmpVO emp = empService.selectbyId(eid);
        return emp;
    }

    //Jackson ���̺귯��:
    //@ResponseBody�� ������, @RequestBody�� �ޱ�
    //@ResopnseBody���� ��� @RestController ��� //Java Object->JSON ����
    //@RequestBody�� JSON������ ��� //JSON->Java Object�� ����
    @RequestMapping(value = "/empinsert.do", method = RequestMethod.POST, produces = {"text/plain;charset=utf-8"})
    public String test6(@RequestBody EmpVO emp) {
        logger.info(emp.toString());
        int result = empService.insertEmp(emp);
        return result > 0 ? "입력성공" : "입력실패";
    }

    @RequestMapping(value = "/login3.do/{userid}/{userpw}/{address}/{phone}",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public EmpVO login(@PathVariable("userid") int userid,
                        @PathVariable String userpw,
                        @PathVariable String address,
                        @PathVariable String phone) {
        logger.info("아이디{} 비밀번호{}", userid, userpw);
        logger.info("주소{} 전화번호{}", address, phone);

        EmpVO emp = empService.loginChk(userid, userpw);

        return emp;
    }

    @RequestMapping(value = "/empDelete.do/{userid}",
            method = RequestMethod.DELETE, produces = "text/plain;charset=utf-8")
    public String login(@PathVariable("userid") int userid) {
        logger.info("아이디는{}", userid);
        int result = empService.deleteEmp(userid);
        return result>0?"삭제성공":"삭제실패";
    }

    @RequestMapping(value = "/empUpdate.do", method = RequestMethod.PUT, produces = "text/plain;charset=utf-8")
    public String update(@RequestBody EmpVO emp) {
        logger.info(emp.toString());
        int result = empService.updateEmp(emp);
        return result > 0 ? "입력성공" : "입력실패";
    }

//    @RequestMapping(value = "/login3.do", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
//    public EmpVO login(@RequestBody UserVO2 user) {
//
//        logger.info("user:{}", user.toString());
//
//        EmpVO emp = empService.loginChk(user.getUserid(), user.getEmail());
//
//        return emp;
//    }

}
