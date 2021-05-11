package com.kosta.business;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deptDAO_mybatis")
public class DeptDAOMybatis implements DeptDAOInterface {

    @Autowired
    SqlSession sqlSession;

    String namespace = "com.kosta.dept.";

    @Override
    public List<DeptVO> findAll() {
        return sqlSession.selectList(namespace + "selectAll");
    }

    @Override
    public DeptVO findByID(int deptid) {
        return sqlSession.selectOne(namespace + "selectById", deptid);
    }

    @Override
    public int insert(DeptVO dept) {
        return sqlSession.insert(namespace + "insert", dept);
    }

    @Override
    public int update(DeptVO dept) {
        return sqlSession.update(namespace + "update", dept);
    }

    @Override
    public int delete(int deptid) {
        return sqlSession.delete(namespace + "delete", deptid);
    }

    @Override
    public List<ManagerVO> findAllManager() {
        return sqlSession.selectList(namespace + "selectAllManager");
    }

    @Override
    public List<LocationVO> findAllLocation() {
        return sqlSession.selectList(namespace + "selectAllLocation");
    }
}
