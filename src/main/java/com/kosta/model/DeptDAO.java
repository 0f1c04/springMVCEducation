package com.kosta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.util.DBUtil;

@Repository
public class DeptDAO {
	
	@Autowired
	DataSource datasource;
	
	public List<ManagerVO> selectAllManager() {
		  List<ManagerVO> mlist = new ArrayList<ManagerVO>();
		  Connection conn = null;
		  Statement st = null;
		  ResultSet rs = null;
		  String sql = "select employee_id, first_name||last_name fullname " +
		    "from EMPLOYEES where employee_id in (select distinct manager_id from employees )";
		  try {
			conn = datasource.getConnection();
		    st = conn.createStatement();
		    rs = st.executeQuery(sql);
		    while (rs.next()) {
		      ManagerVO vo = new ManagerVO(rs.getInt(1), rs.getString(2));
		      mlist.add(vo);
		    }
		  } catch (SQLException e) {
		    e.printStackTrace();
		  } finally {
		    DBUtil.dbClose(rs, st, conn);
		  }

		  return mlist;
		}
	
	public List<LocationVO> selectAllLocation() {
		List<LocationVO> loclist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from Locations order by 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				LocationVO vo = new LocationVO();
				vo.setLocation_id(rs.getInt(1));
				vo.setCity(rs.getString("city"));
				loclist.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return loclist;
	}
	
	public List<DeptVO> selectAll() {
		List<DeptVO> deptlist = new ArrayList<DeptVO>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from departments order by 1";
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				DeptVO dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return deptlist;
	}
	
	public int insertDept(DeptVO dept) {
		String sql = "insert into departments values(?,?,?,?)";
		Connection conn;
		PreparedStatement st = null;
		int result = 0;

		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			st.setInt(3, dept.getManager_id());
			st.setInt(4, dept.getLocation_id());
			result = st.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}

	public DeptVO selectByID(int i_deptid) {
		DeptVO dept = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from departments where department_id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, i_deptid);
			rs = st.executeQuery();
			while(rs.next()) {
				dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return dept;
	}
}
