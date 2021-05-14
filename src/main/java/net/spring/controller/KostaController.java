package net.spring.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.model.DeptDAO;
import com.kosta.model.EmpDAO;
import com.kosta.model.UserVO;

@Controller
public class KostaController {

	@Autowired
	DeptDAO deptDAO;
	@Autowired
	EmpDAO empDAO;

	@RequestMapping(value = { "/sample1.do", "/test/sample2.do" })
	public ModelAndView test1() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("title", "�����ּҸ� ������ ��� �����ϴ�");
		// ������ �̸��� ���ٸ� default�� ��û�ּ� �̸��� ���ȴ�.
		// /sample1.do -> WEB-INF/views/sample1.jsp
		// /test/sample2.do -> WEB-INF/views/test/sample2.jsp

		return mv;
	}

	@RequestMapping(value = "sample3.do")
	public ModelAndView test2(@RequestParam("userid") String id, int userpass, @RequestParam("email") String email2,
			HttpServletRequest request, Map<String, Object> userinfo, UserVO user) {
		ModelAndView mv = new ModelAndView();
		System.out.println("id : " + id);
		System.out.println("pass : " + userpass);
		System.out.println("email : " + email2);
		System.out.println("userinfo : " + userinfo);
		System.out.println("user : " + user);
		mv.addObject("title", "�����ּҸ� param����ϱ�");
		mv.addObject("id", id);
		mv.addObject("userpass", userpass);
		mv.addObject("email2", email2);
		mv.addObject("userinfo", userinfo);
		mv.addObject("user", user);
		// request�� �޾ƿ͵� ��
		String email = request.getParameter("email");
		mv.addObject("email", email);

		mv.setViewName("sample3Result");

		return mv;
	}

	// return�� String : �������̸��� return...�ش�page�� forward
	// model : page�� ����
	@RequestMapping(value = "sample4.do")
	public String test4(Model model, String myname, HttpSession session) {
		model.addAttribute("title", "page�̸� return ���");
		System.out.println("myname: " + myname); // ?myname=yoon �ּҿ� �߰��ϸ� ����
		UserVO user = new UserVO();
		user.setEmail("request�� ������ �̸���");
		model.addAttribute("user", user);

		UserVO user2 = new UserVO();
		user2.setEmail("���ǿ� ������ �̸���");
		session.setAttribute("user", user2);
		return "sample3Result";
	}

	@RequestMapping(value = "sample5.do")
	public void test5(HttpServletRequest request, HttpSession session) {
		String cpath = request.getContextPath();
		System.out.println(cpath);
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURL());
		System.out.println(session.getServletContext().getRealPath("."));
	}

	@RequestMapping(value = "sample6.do")
	@ResponseBody
	public String test6() {
		return "hello~";
	}

	@RequestMapping("emp/allmanager.do")
	public String test7(Model mydata) {

		mydata.addAttribute("mlist", deptDAO.selectAllManager());
		return "emp/allmanager";
	}

	// �μ���ȸ
	@RequestMapping("emp/alldeptlist.do")
	public String alldeptlist(Model mydata) {
		mydata.addAttribute("dlist", deptDAO.selectAll());
		return "emp/alldeptlist";
	}

	// �μ���
	@RequestMapping("emp/empByDept.do")
	public String empByDept(Model mydata, int deptid) {
		mydata.addAttribute("emplist", empDAO.selectByDept(deptid));
		return "emp/emplist";
	}

	// job��ȸ
	@RequestMapping("emp/alljoblist.do")
	public String alljoblist(Model mydata) {
		mydata.addAttribute("jlist", empDAO.selectAllJobs());
		return "emp/alljoblist";
	}

	// job��
	@RequestMapping("emp/empByJob.do")
	public String empByJob(Model mydata, String jobid) throws IOException {

		// 1.�������
		int a = 10/0;
		// System.out.println(a);

		// 2.������ �ƴ� ��Ÿ����
		ClassPathResource resource = new ClassPathResource("oracledb.properties");
		System.out.println(resource.getFilename());

		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

		String s;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		br.close();

		mydata.addAttribute("emplist", empDAO.selectByJobId(jobid));
		return "emp/emplist";

	}

	// error test
	@RequestMapping("emp/errorTest.do")
	public void errorTest() throws IOException {
		// 1.�������
		// int a = 10/0;
		// System.out.println(a);

		// 2.������ �ƴ� ��Ÿ����
		ClassPathResource resource = new ClassPathResource("oracledb.properties");
		System.out.println(resource.getFilename());

		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));

		String s;
		while ((s = br.readLine()) != null) {
			System.out.println(s);
		}
		br.close();
	}

}
