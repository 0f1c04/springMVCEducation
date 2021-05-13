package com.spring.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
public class AccountService {

	@Autowired
	private AccountDAO accDAO;

	//이체
	public void sendMoney() throws DataAccessException {
		accDAO.updateBalance1(); //출금
		accDAO.updateBalance2(); //입금
	}
}