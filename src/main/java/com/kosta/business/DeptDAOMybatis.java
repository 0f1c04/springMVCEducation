package com.kosta.business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;

@Repository("deptDAO_mybatis")
public class DeptDAOMybatis implements DeptDAOInterface {

	@Autowired
	SqlSession sqlsession;

	String namespace = "com.kosta.dept.";

	@Override
	public List<DeptVO> findAll() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace + "selectAll");
	}

	@Override
	public DeptVO findById(int deptid) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace + "selectById", deptid);
	}

	@Override
	public int insert(DeptVO dept) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace + "insert", dept);
	}

	@Override
	public int update(DeptVO dept) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace + "update", dept);
	}

	@Override
	public int delete(int deptid) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace + "delete", deptid);
	}

	@Override
	public List<ManagerVO> findAllManager() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace + "selectAllManager");
	}

	@Override
	public List<LocationVO> findAllLocation() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace + "selectAllLocation");
	}

}
