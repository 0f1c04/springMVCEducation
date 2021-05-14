package com.kosta.business;

import java.util.List;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;

public interface DeptServiceInterface {
	
	public List<DeptVO> findAll();
	public DeptVO findById(int deptid);
	public int insert(DeptVO dept);
	public int update(DeptVO dept);
	public int delete(int deptid);
	
	public List<ManagerVO> findAllManager();
	public List<LocationVO> findAllLocation();
	
	public int insertUpdate(DeptVO newDept, DeptVO oldDept);
	
}
