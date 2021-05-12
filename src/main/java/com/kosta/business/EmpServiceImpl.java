package com.kosta.business;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpServiceInterface {
    @Autowired
    @Qualifier("empDAO_mybatis")
    EmpDAOInterface empDAO;

    @Override
    public EmpVO loginChk(int empid, String email) {
        return empDAO.loginChk(empid, email);
    }

    @Override
    public List<JobVO> selectAllJobs() {
        return empDAO.selectAllJobs();
    }

    @Override
    public int deleteEmp(int empid) {
        return empDAO.deleteEmp(empid);
    }

    @Override
    public int updateEmp(EmpVO emp) {
        return empDAO.updateEmp(emp);
    }

    @Override
    public int insertEmp(EmpVO emp) {
        return empDAO.insertEmp(emp);
    }

    @Override
    public List<EmpVO> selectAll() {
        return empDAO.selectAll();
    }

    @Override
    public EmpVO selectById(int empid) {
        return empDAO.selectById(empid);
    }

    @Override
    public List<EmpVO> selectByDept(int deptid) {
        return empDAO.selectByDept(deptid);
    }

    @Override
    public List<EmpVO> selectByJob(String jobid) {
        return empDAO.selectByJob(jobid);
    }

    @Override
    public List<EmpVO> selectBySalary(int minsal, int maxsal) {
        return empDAO.selectBySalary(minsal, maxsal);
    }

    @Override
    public List<EmpVO> selectByDate(String sdate, String edate) {
        return empDAO.selectByDate(sdate, edate);
    }

    @Override
    public List<EmpVO> selectByDate2(Date sdate, Date edate) {
        return empDAO.selectByDate2(sdate, edate);
    }

    @Override
    public List<EmpVO> selectByName(String ch) {
        return empDAO.selectByName(ch);
    }

    @Override
    public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
        return empDAO.selectByCondition(deptid, jobid, sal, hdate);
    }

    @Override
    public List<EmpVO> selectByDeptMany(List<Integer> deptlist) {
        return empDAO.selectByDeptMany(deptlist);
    }
}
