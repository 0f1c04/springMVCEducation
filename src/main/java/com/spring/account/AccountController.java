package com.spring.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	@Autowired
	private AccountService accService;
	
	@RequestMapping(value = "/account.do", produces = {"text/plain;charset=UTF-8"})
	@ResponseBody
	public String sendMoney() throws Exception {
		accService.sendMoney();
		return "account.do END!!!";
	}
}
