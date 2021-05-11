package net.spring.controller;

import com.kosta.business.DeptDAO;
import com.kosta.business.EmpDAO;
import com.kosta.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Controller
public class KostaController {
    @Autowired
    DeptDAO deptDAO;

    @Autowired
    EmpDAO empDAO;

    @RequestMapping(value = {"/sample1.do", "/test/sample2.do"})
    public ModelAndView test1() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "매핑주소를 여러개 사용가능하다.");
        //페이지 이름이 없다면 default로 요청주소이름이 사용
        //sample1.do -> WEB-INF/views/sample1.jsp
        //test/sample2.do -> WEB-INF/views/test/sample2.jsp
        return mv;
    }

//    @RequestMapping(value = "/sample3.do", params = {"userid=abc", "userpass", "!email"})
//    public ModelAndView test2() {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("title", "매핑주소에 param 사용");
//        mv.setViewName("sample3Result");
//        return mv;
//    }

    @RequestMapping(value = "/sample3.do")
    public ModelAndView test2(@RequestParam("userid") String id,
                              int userpass,
                              @RequestParam("email") String email2,
                              HttpServletRequest request,
                              Map<String, Object> userinfo,
                              UserVO user ) {
        System.out.println("id=" + id);
        System.out.println("userpw=" + userpass);
        System.out.println("email2=" + email2);
        System.out.println("userinfo=" + userinfo);
        System.out.println("user=" + user);

        ModelAndView mv = new ModelAndView();
        mv.addObject("title", "파라미터 받기");
        mv.addObject("id", id);
        mv.addObject("userpass", userpass);
        mv.addObject("email2", email2);

        mv.addObject("userinfo", userinfo);
        mv.addObject("user", user);

        String email = request.getParameter("email");
        mv.addObject("email", email);

        mv.setViewName("sample3Result");
        return mv;
    }

    //return이 String: 페이지이름이 return ... 해당 page로 forward
    //Model: page에 전달
    @RequestMapping(value = "/sample4.do")
    public String test4(Model model, String myname, HttpSession session) {
        model.addAttribute("title", "page이름 return방법");
        System.out.println("myname=" + myname);

        UserVO user = new UserVO();
        user.setEmail("request@naver.com");
        model.addAttribute("user", user);

        UserVO user2 = new UserVO();
        user2.setEmail("session@naver.com");
        session.setAttribute("user", user2);

        return "sample3Result";
    }

    @RequestMapping(value = "sample5.do")
    public void test5(HttpServletRequest request, HttpSession session) {
        String cPath = request.getContextPath();
        System.out.println(cPath);
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());
        System.out.println(session.getServletContext().getRealPath("."));
    }

    @RequestMapping(value = "sample6.do")
    @ResponseBody
    public String test6() {
        return "<h1>Hello~</h1>";
    }

    @RequestMapping("emp/allmanager.do")
    public String allManagerList(Model mydata) {
        mydata.addAttribute("mlist", deptDAO.findAllManager());
        return "emp/allmanager";
    }

    @RequestMapping("emp/alldeptlist.do")
    public String allDeptList(Model mydata) {
        mydata.addAttribute("dlist", deptDAO.findAll());
        return "emp/alldeptlist";
    }

    @RequestMapping("emp/empByDept.do")
    public String empByDept(Model mydata, int deptid) {
        mydata.addAttribute("emp_all", empDAO.selectByDept(deptid));
        return "emp/emplist";
    }

    @RequestMapping("emp/alljoblist.do")
    public String alljoblist(Model mydata) {
        mydata.addAttribute("jlist", empDAO.selectAllJobs());
        return "emp/alljoblist";
    }

    @RequestMapping("emp/empByJob.do")
    public String empByJob(Model mydata, String jobid) {
        mydata.addAttribute("emp_all", empDAO.selectByJob(jobid));
        return "emp/emplist";
    }

    @RequestMapping("emp/errorTest.do")
    public void errorTest() throws IOException {
        //1. 연산오류
//        int a = 10/0;
//        System.out.println(a);

        //2. 연산오류 아닌 기타오류
        ClassPathResource resource = new ClassPathResource("oracledb.properties");
        System.out.println(resource.getFilename());

        BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        String s = br.readLine();
        while ((s = br.readLine()) != null) {
            System.out.println(s);
        }
        br.close();
    }
}
