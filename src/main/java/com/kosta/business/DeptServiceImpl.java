package com.kosta.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;

@Service("deptService")
//방법1...@Transactional(propagation=Propagation.REQUIRED)
public class DeptServiceImpl implements DeptServiceInterface {
	// DeptDAOInterface -> 구현class : DeptDAO, DeptDAOMybatis로 2개
	@Autowired
	@Qualifier("deptDAO_mybatis") //타입이 같은 class가 여러개라면 이름으로 구분한다.
	DeptDAOInterface deptDAO;

	@Override
	public List<DeptVO> findAll() {

		return deptDAO.findAll();
	}

	@Override
	public DeptVO findById(int deptid) {
		// TODO Auto-generated method stub
		return deptDAO.findById(deptid);
	}

	@Override
	public int insert(DeptVO dept) {
		// TODO Auto-generated method stub
		return deptDAO.insert(dept);
	}

	@Override
	public int update(DeptVO dept) {
		// TODO Auto-generated method stub
		return deptDAO.update(dept);
	}

	@Override
	public int delete(int deptid) {
		// TODO Auto-generated method stub
		return deptDAO.delete(deptid);
	}

	@Override
	public List<ManagerVO> findAllManager() {
		// TODO Auto-generated method stub
		return deptDAO.findAllManager();
	}

	@Override
	public List<LocationVO> findAllLocation() {
		// TODO Auto-generated method stub
		return deptDAO.findAllLocation();
	}

	@Override
	public int insertUpdate(DeptVO newDept, DeptVO oldDept) {
		int ret1 = deptDAO.insert(newDept);
		int ret2 = deptDAO.update(oldDept);
		return ret1+ret2;
	}

}
