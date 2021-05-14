package com.kosta.business;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

@Service("empService")
public class EmpServiceImpl implements EmpService{
	
	@Autowired
	@Qualifier("empDAO_mybatis") //이름을 따로 안정해주면 empDAOMybatis로 앞에만 소문자로 해주면 됨.
	EmpDAOInterface empDAO;
	
	@Override
	public EmpVO loginChk(int empid, String email) {
		// TODO Auto-generated method stub
		return empDAO.loginChk(empid, email);
	}

	@Override
	public List<JobVO> selectAllJobs() {
		// TODO Auto-generated method stub
		return empDAO.selectAllJobs();
	}

	@Override
	public int deleteEmp(int empid) {
		// TODO Auto-generated method stub
		return empDAO.deleteEmp(empid);
	}

	@Override
	public int updateEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return empDAO.updateEmp(emp);
	}

	@Override
	public int insertEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return empDAO.insertEmp(emp);
	}

	@Override
	public List<EmpVO> selectAll() {
		// TODO Auto-generated method stub
		return empDAO.selectAll();
	}

	@Override
	public EmpVO selectbyId(int empid) {
		// TODO Auto-generated method stub
		return empDAO.selectbyId(empid);
	}

	@Override
	public List<EmpVO> selectByDept(int deptid) {
		// TODO Auto-generated method stub
		return empDAO.selectByDept(deptid);
	}

	@Override
	public List<EmpVO> selectByJobId(String jobid) {
		// TODO Auto-generated method stub
		return empDAO.selectByJobId(jobid);
	}

	@Override
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		// TODO Auto-generated method stub
		return empDAO.selectBySalary(minsal, maxsal);
	}

	@Override
	public List<EmpVO> selectByHireDate(String sdate, String edate) {
		// TODO Auto-generated method stub
		return empDAO.selectByHireDate(sdate, edate);
	}

	@Override
	public List<EmpVO> selectByHireDate2(Date sdate, Date edate) {
		// TODO Auto-generated method stub
		return empDAO.selectByHireDate2(sdate, edate);
	}

	@Override
	public List<EmpVO> selectByChar(String ch) {
		// TODO Auto-generated method stub
		return empDAO.selectByChar(ch);
	}

	@Override
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		// TODO Auto-generated method stub
		return empDAO.selectByCondition(deptid, jobid, sal, hdate);
	}

	@Override
	public List<EmpVO> selectByDeptMany(List<Integer> deptidList) {
		// TODO Auto-generated method stub
		return empDAO.selectByDeptMany(deptidList);
	}
	
}
