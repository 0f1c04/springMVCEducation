package com.kosta.business;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;

import java.sql.*;
import java.util.List;

public interface EmpServiceInterface {
    EmpVO loginChk(int empid, String email);

    List<JobVO> selectAllJobs();

    int deleteEmp(int empid);

    int updateEmp(EmpVO emp);

    int insertEmp(EmpVO emp);

    List<EmpVO> selectAll();

    EmpVO selectById(int empid);

    List<EmpVO> selectByDept(int deptid);

    List<EmpVO> selectByJob(String jobid);

    List<EmpVO> selectBySalary(int minsal, int maxsal);

    List<EmpVO> selectByDate(String sdate, String edate);

    List<EmpVO> selectByDate2(Date sdate, Date edate);

    List<EmpVO> selectByName(String ch);

    List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate);

    List<EmpVO> selectByDeptMany(List<Integer> deptlist);
}
