package com.kosta.business;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.kosta.model.DeptVO;
import com.kosta.model.LocationVO;
import com.kosta.model.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kosta.util.DBUtil;

@Repository("deotDAO_jdbc")
public class DeptDAO implements DeptDAOInterface {

    @Autowired
    @Qualifier("dataSource")
    DataSource datasource;

    @Autowired
    @Qualifier("jdbcTemplate")
    JdbcTemplate jdbcTemplate;

    public List<ManagerVO> findAllManager() {
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

    public List<LocationVO> findAllLocation() {
        List<LocationVO> loclist = new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from Locations order by 1";
        try {
            conn = datasource.getConnection();
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

    //JDBC Template
    public List<DeptVO> findAll() {
        List<DeptVO> deptlist = new ArrayList<DeptVO>();
        String sql = "select * from departments order by 1";
        deptlist = jdbcTemplate.query(sql, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                DeptVO dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));

                return dept;
            }
        });

        return deptlist;
    }

    //JDBC 원본
//	public List<DeptVO> findAll() {
//		List<DeptVO> deptlist = new ArrayList<DeptVO>();
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null;
//		String sql = "select * from departments order by 1";
//		try {
//			conn = datasource.getConnection();
//			st = conn.createStatement();
//			rs = st.executeQuery(sql);
//			while(rs.next()) {
//				DeptVO dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
//				deptlist.add(dept);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.dbClose(rs, st, conn);
//		}
//
//		return deptlist;
//	}

    public int insert(DeptVO dept) {
        String sql = "insert into departments values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement st = null;
        int result = 0;

        try {
            conn = datasource.getConnection();
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

    public DeptVO findByID(int i_deptid) {
        DeptVO dept = null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from departments where department_id=?";
        try {
            conn = datasource.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, i_deptid);
            rs = st.executeQuery();
            while (rs.next()) {
                dept = new DeptVO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

        return dept;
    }

    public int update(DeptVO dept) {
        int result = 0;
        String sql =
                " update departments  set " +
                        " department_name=?, " +
                        " manager_id =?,  " +
                        " location_id=?  " +
                        " where department_id = ?";

        Connection conn;
        PreparedStatement st = null;
        conn = null;
        try {
            conn = datasource.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(4, dept.getDepartment_id());
            st.setString(1, dept.getDepartment_name());
            st.setInt(2, dept.getManager_id());
            st.setInt(3, dept.getLocation_id());

            result = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, st, conn);
        }

        return result;
    }

    public int delete(int deptid) {
        int result = 0;
        String sql = "delete from DEPARTMENTS where DEPARTMENT_ID = ? ";
        Connection conn;
        PreparedStatement st = null;
        conn = null;
        try {
            conn = datasource.getConnection();
            st = conn.prepareStatement(sql);
            st.setInt(1, deptid);
            result = st.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e.getMessage());
        } finally {
            DBUtil.dbClose(null, st, conn);
        }

        return result;
    }
}
