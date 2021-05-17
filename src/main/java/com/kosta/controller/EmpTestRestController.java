package com.kosta.controller;


import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.kosta.model.UserVO2;

@RestController //@Controller + @ResponseBody
@RequestMapping("/emp2/*")
public class EmpTestRestController {

    @Autowired
    EmpService empService;

    Logger logger = LoggerFactory.getLogger(EmpTestRestController.class);

    @RequestMapping(value = "/emplist.do", produces = {"text/html; charset=UTF-8"})//Content-type,MIMETYPE:text
    public String test1() {
        return "<h1>직원리스트입니다.</h1>";
    }


    //JSON(JavaScript Object Notation) : JavaScript Object형태의 문자열{"키":"값"}
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
        Map<Integer,EmpVO> map = new HashMap<>();
        emplist.forEach(emp->{
            map.put(emp.getEmployee_id(), emp);
        });
        return map;
    }

    // {empid}는 /100으로 주면 됨
    @RequestMapping(value = "/emp5.do/{empid}", method = RequestMethod.GET)
    public EmpVO test5(@PathVariable("empid") int eid) {
        EmpVO emp = empService.selectbyId(eid);
        return emp;
    }

    //Jackson 라이브러리:
    //@ResponseBody로 보내기, @RequestBody로 받기
    //@ResopnseBody생략 대신 @RestController 사용 //Java Object->JSON 변경
    //@RequestBody는 JSON받을때 사용 //JSON->Java Object로 변경
    @RequestMapping(value = "/empinsert.do", method = RequestMethod.POST, produces = {"text/plain;charset=utf-8"})
    public String test6(@RequestBody EmpVO emp) {
        logger.info(emp.toString());
        int result=empService.insertEmp(emp);
        return result>0?"입력성공":"입력실패";
    }

    @RequestMapping(value="/empupdate.do", produces = {"text/plain; charset=UTF-8"}, method=RequestMethod.PUT)
    public String empUpdate(@RequestBody EmpVO emp) {
        int result = empService.updateEmp(emp);
        return result>0?"수정성공":"수정실패";
    }

    @RequestMapping(value = "/empdelete.do/{userid}", produces = {"text/plain; charset=UTF-8"}, method= RequestMethod.DELETE)
    public String empdelete(@PathVariable("userid") int userid) {
        int result = empService.deleteEmp(userid);
        return result>0?"삭제성공":"삭제실패";
    }

    @RequestMapping(value = "/login3.do/{userid}/{userpass}/{address}/{phone}", produces = {"application/json; charset=UTF-8"}, method= RequestMethod.GET)
    public EmpVO login(@PathVariable("userid") int userid, @PathVariable() String userpass, @PathVariable() String address, @PathVariable() String phone) {
        logger.info("아이디는 {} 비밀번호는 {}", userid, userpass);
        logger.info("주소는 {} 전화번호는 {}", address, phone);
        EmpVO emp = empService.loginChk(userid, userpass);
        return emp;
    }

    //RESTFull 목적 : GET, POST, PUT, DELETE
    //GET : URL이 자동 Encoding
    //POST : URL에 정보노출 안함, 요청문서의 Body 부분에 encoding없이 간다.
   /*@RequestMapping(value = "/login3.do", produces = {"application/json; charset=UTF-8"}, method = RequestMethod.POST)
   public EmpVO login(@RequestBody UserVO2 user) {
      logger.info("user :{}", user.toString());
      EmpVO emp = empService.loginChk(user.getUserid(), user.getUserpass());
      return emp;
   }*/

    @RequestMapping("/empByDept.do/{did}")
    public List<EmpVO> empByDept(@PathVariable("did") int did) {
        logger.info("deptid = {}", did);
        List<EmpVO> emplist = empService.selectByDept(did);
        return emplist;
    }

    @RequestMapping("/empBySal.do/{minsal}/{maxsal}")
    public List<EmpVO> empBySal(@PathVariable int minsal, @PathVariable int maxsal) {
        logger.info(minsal + "----------" + maxsal);
        List<EmpVO> emplist = empService.selectBySalary(minsal, maxsal);
        return emplist;
    }

    @RequestMapping("/empByDate.do/{sdate}/{edate}")
    public List<EmpVO> empByDate(@PathVariable String sdate, @PathVariable String  edate) {
        logger.info(sdate + "----------" + edate);
        List<EmpVO> emplist = empService.selectByHireDate(sdate, edate);
        return emplist;
    }

    @RequestMapping("/empByDate2.do/{sdate}/{edate}")
    public List<EmpVO> empByDate2(@PathVariable Date sdate, @PathVariable Date edate) {
        logger.info(sdate + "----------" + edate);
        List<EmpVO> emplist = empService.selectByHireDate2(sdate, edate);
        return emplist;
    }

    @RequestMapping("/empByCondition.do/{deptid}/{jobid}/{sal}/{hdate}/{chk}")
    public List<EmpVO> empByCondition(@PathVariable int deptid, @PathVariable String jobid, @PathVariable int sal, @PathVariable Date hdate, @PathVariable String chk) {
        logger.info(deptid + "---" + jobid + "---" + sal + "---" + hdate + "---" + chk);
        if(chk==null) hdate=null;
        if(jobid.equals("null")) jobid=null;
        List<EmpVO> emplist = empService.selectByCondition(deptid, jobid, sal, hdate);

        return emplist;
    }

}