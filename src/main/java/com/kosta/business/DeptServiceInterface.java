package com.kosta.business;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;

import java.util.List;

public interface DeptServiceInterface {
    List<DeptVO> findAll();
    DeptVO findByID(int deptid);
    int insert(DeptVO dept);
    int update(DeptVO dept);
    int delete(int deptid);

    List<ManagerVO> findAllManager();
    List<LocationVO> findAllLocation();
}
