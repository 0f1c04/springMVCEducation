package com.spring.account;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	
	@Autowired
	private SqlSession sqlSession;


	public void updateBalance1() throws DataAccessException {
		sqlSession.update("com.kosta.account.update1");
	}
	
	public void updateBalance2() throws DataAccessException {
		sqlSession.update("com.kosta.account.update2");
	}
	

}
