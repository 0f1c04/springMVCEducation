package com.kosta.business;

import java.sql.Date;
import java.util.List;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

public interface EmpService {
	
	public EmpVO loginChk(int empid, String email);
	
	public List<JobVO> selectAllJobs();
	
	public int deleteEmp(int empid);
	
	public int updateEmp(EmpVO emp);
	
	public int insertEmp(EmpVO emp);
	
	public List<EmpVO> selectAll();
	
	public EmpVO selectbyId(int empid); 
	
	public List<EmpVO> selectByDept(int deptid); 
	
	public List<EmpVO> selectByJobId(String jobid);
	
	public List<EmpVO> selectBySalary(int minsal, int maxsal);
	
	public List<EmpVO> selectByHireDate(String sdate, String edate);
	
	public List<EmpVO> selectByHireDate2(Date sdate, Date edate);
	
	public List<EmpVO> selectByChar(String ch);
	
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate);
	
	public List<EmpVO> selectByDeptMany(List<Integer> deptidList);
}
