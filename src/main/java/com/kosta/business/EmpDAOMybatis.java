package com.kosta.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

@Repository("empDAO_mybatis")
public class EmpDAOMybatis implements EmpDAOInterface{
	
	@Autowired
	SqlSession sqlsession;
	
	String namespace = "com.kosta.emp.";
	
	Logger logger = LoggerFactory.getLogger(EmpDAOMybatis.class);
	
	@Override
	public EmpVO loginChk(int empid, String email) {
		Map<String, Object> empInfo = new HashMap<>();
		empInfo.put("empid", empid);
		empInfo.put("email", email);
		EmpVO emp = sqlsession.selectOne(namespace+"loginChk", empInfo);
		return emp;
	}

	@Override
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = sqlsession.selectList(namespace+"selectAllJobs");
		//logger.info("{}건의 job이 있다.",joblist.size()); //placeholder
		return joblist;
	}

	@Override
	public int deleteEmp(int empid) {
		int result = sqlsession.delete(namespace + "delete", empid);
		logger.info("{}건 삭제.",result);
		return result;
	}

	@Override
	public int updateEmp(EmpVO emp) {
		int result = sqlsession.update(namespace + "update", emp);
		logger.info("{}건 수정.",result);
		return result;
	}

	@Override
	public int insertEmp(EmpVO emp) {
		//emp.setPhone_number(null);
		int insert = sqlsession.insert(namespace + "insert", emp);
		logger.info("insert : {}",insert);
		return insert;
	}

	@Override
	public List<EmpVO> selectAll() {
		List<EmpVO> alllist =  sqlsession.selectList(namespace + "selectAll");
		logger.info("{}건의 직원목록",alllist.size());
		return alllist;
	}

	@Override
	public EmpVO selectbyId(int empid) {
		EmpVO emp = sqlsession.selectOne(namespace + "selectById", empid);
		logger.info("한건 : {}",emp);
		return emp;
	}

	@Override
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = sqlsession.selectList(namespace+"selectByDept", deptid);
		System.out.println("selectbydept : " + emplist.size());
		return emplist;
	}

	@Override
	public List<EmpVO> selectByJobId(String jobid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		Map<String, Integer> salMap = new HashMap<>();
		salMap.put("min", minsal);
		salMap.put("max", maxsal);
		List<EmpVO> emplist = sqlsession.selectList(namespace+"selectBySalary", salMap);
		logger.info("selectBySalary : {}", emplist.size());
		return emplist;
	}

	@Override
	public List<EmpVO> selectByHireDate(String sdate, String edate) {
		Map<String, String> dateMap = new HashMap<>();
		dateMap.put("sdate", sdate);
		dateMap.put("edate", edate);
		List<EmpVO> emplist = sqlsession.selectList(namespace+"selectByHireDate", dateMap);
		System.out.println("selectByHireDate : " + emplist.size());
		return emplist;
	}

	@Override
	public List<EmpVO> selectByHireDate2(Date sdate, Date edate) {
		Map<String, Date> dateMap = new HashMap<>();
		dateMap.put("sdate", sdate);
		dateMap.put("edate", edate);
		List<EmpVO> emplist = sqlsession.selectList(namespace+"selectByHireDate2", dateMap);
		System.out.println("selectByHireDate2 : " + emplist.size());
		return emplist;
	}

	@Override
	public List<EmpVO> selectByChar(String ch) {
		List<EmpVO> emplist = sqlsession.selectList(namespace+"selectByChar", "%"+ch+"%");
		System.out.println("selectByChar : " + emplist.size());
		return emplist;
	}

	@Override
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptid", deptid);
		map.put("jobid", jobid);
		map.put("sal", sal);
		map.put("hdate", hdate);
		List<EmpVO> emplist = sqlsession.selectList(namespace + "selectByCondition", map); 
		System.out.println(emplist.size()+"건");
		return emplist;
	}

	@Override
	public List<EmpVO> selectByDeptMany(List<Integer> deptidList) {
		List<EmpVO> emplist = sqlsession.selectList(namespace + "selectByDeptMany", deptidList); 
		System.out.println(emplist.size()+"건");
		return emplist;
	}

}
