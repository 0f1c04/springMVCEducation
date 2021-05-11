package com.kosta.business;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptServiceInterface {
    @Autowired
    @Qualifier("deptDAO_mybatis")
    DeptDAOInterface deptDAO;

    @Override
    public List<DeptVO> findAll() {
        System.out.println("mybatis");
        return deptDAO.findAll();
    }

    @Override
    public DeptVO findByID(int deptid) {
        return deptDAO.findByID(deptid);
    }

    @Override
    public int insert(DeptVO dept) {
        return deptDAO.insert(dept);
    }

    @Override
    public int update(DeptVO dept) {
        return deptDAO.update(dept);
    }

    @Override
    public int delete(int deptid) {
        return deptDAO.delete(deptid);
    }

    @Override
    public List<ManagerVO> findAllManager() {
        return deptDAO.findAllManager();
    }

    @Override
    public List<LocationVO> findAllLocation() {
        return deptDAO.findAllLocation();
    }
}
