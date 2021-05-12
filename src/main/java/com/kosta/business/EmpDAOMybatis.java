package com.kosta.business;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("empDAO_mybatis")
public class EmpDAOMybatis implements EmpDAOInterface{

    @Autowired
    SqlSession sqlSession;

    String namespace = "com.kosta.emp.";

    @Override
    public EmpVO loginChk(int empid, String email) {
        Map<String, Object> empInfo = new HashMap<>();
        empInfo.put("empid", empid);
        empInfo.put("email", email);
        return sqlSession.selectOne(namespace + "loginChk", empInfo);
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
        return sqlSession.selectList(namespace+"selectByDept", deptid);
    }

    @Override
    public List<EmpVO> selectByJob(String jobid) {
        return sqlSession.selectOne(namespace+"selectByJob", jobid);
    }

    @Override
    public List<EmpVO> selectBySalary(int minsal, int maxsal) {
        Map<String, Integer> salMap = new HashMap<>();
        salMap.put("min", minsal);
        salMap.put("max", maxsal);

        List<EmpVO> emplist = sqlSession.selectList(namespace+"selectBySalary", salMap);
        return emplist;
    }

    @Override
    public List<EmpVO> selectByDate(String sdate, String edate) {
        Map<String, String> salMap = new HashMap<>();
        salMap.put("sdate", sdate);
        salMap.put("edate", edate);

        List<EmpVO> emplist = sqlSession.selectList(namespace+"selectByDate", salMap);
        return emplist;
    }

    @Override
    public List<EmpVO> selectByDate2(Date sdate, Date edate) {
        Map<String, Date> salMap = new HashMap<>();
        salMap.put("sdate", sdate);
        salMap.put("edate", edate);

        List<EmpVO> emplist = sqlSession.selectList(namespace+"selectByDate", salMap);
        return emplist;
    }

    @Override
    public List<EmpVO> selectByName(String ch) {
        return sqlSession.selectOne(namespace+"selectByName", ch);
    }

    @Override
    public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
        Map<String, Object> map = new HashMap<>();
        map.put("deptid", deptid);
        map.put("jobid", jobid);
        map.put("sal", sal);
        map.put("hdate", hdate);
        List<EmpVO> emplist = sqlSession.selectList(namespace+"selectByCondition", map);
        return emplist;
    }

    @Override
    public List<EmpVO> selectByDeptMany(List<Integer> deptlist) {
        List<EmpVO> emplist = sqlSession.selectList(namespace+"selectByDeptMany", deptlist);
        return emplist;
    }
}
