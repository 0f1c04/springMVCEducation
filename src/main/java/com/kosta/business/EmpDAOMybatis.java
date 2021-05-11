package com.kosta.business;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("empDAO_mybatis")
public class EmpDAOMybatis implements EmpDAOInterface{

    @Autowired
    SqlSession sqlSession;

    String namespace = "com.kosta.emp.";

    @Override
    public EmpVO loginChk(int empid, String email) {
        return null;
    }

    @Override
    public List<JobVO> selectAllJobs() {
        return sqlSession.selectList(namespace+"selectAllJobs");
    }

    @Override
    public int deleteEmp(int empid) {
        return sqlSession.delete(namespace+"delete", empid);
    }

    @Override
    public int updateEmp(EmpVO emp) {
        return sqlSession.update(namespace+"update", emp);
    }

    @Override
    public int insertEmp(EmpVO emp) {
        return sqlSession.insert(namespace+"insert", emp);
    }

    @Override
    public List<EmpVO> selectAll() {
        return sqlSession.selectList(namespace+"selectAll");
    }

    @Override
    public EmpVO selectById(int empid) {
        return sqlSession.selectOne(namespace+"selectById", empid);
    }

    @Override
    public List<EmpVO> selectByDept(int deptid) {
        return sqlSession.selectOne(namespace+"selectByDept", deptid);
    }

    @Override
    public List<EmpVO> selectByJob(String jobid) {
        return sqlSession.selectOne(namespace+"selectByJob", jobid);
    }

    @Override
    public List<EmpVO> selectBySalary(int minsal, int maxsal) {
        return null; //몰라
    }

    @Override
    public List<EmpVO> selectByDate(String sdate, String edate) {
        return null; //몰라
    }

    @Override
    public List<EmpVO> selectByDate2(Date sdate, Date edate) {
        return null; //몰라
    }

    @Override
    public List<EmpVO> selectByName(String ch) {
        return sqlSession.selectOne(namespace+"selectByName", ch);
    }

    @Override
    public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
        return null; //몰라
    }
}
