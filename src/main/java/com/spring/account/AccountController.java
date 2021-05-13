package com.spring.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {

    @Autowired
    private AccountService accService;

    @RequestMapping(value = "/account.do", produces = {"text/plain;charset=UTF-8"})
    @ResponseBody
    public String sendMoney() throws Exception {
        accService.sendMoney();
        return "Account.do End 한글한글";
    }
}