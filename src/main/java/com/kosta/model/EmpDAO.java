package com.kosta.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kosta.util.DBUtil;


//DAO(Data Access Object)
@Repository
public class EmpDAO {
	
	@Autowired
	@Qualifier("dataSource")
	DataSource datasource;
	
	//CRUD(Create:insert, Read:select, U:update, D:delete)
	
	public EmpVO loginChk(int empid, String email) {
		EmpVO emp = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ? and email=?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			st.setString(2, email);
			rs = st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emp;
	}
	
	
	public List<JobVO> selectAllJobs() {
		List<JobVO> joblist = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from jobs order by 1";
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return joblist;
	}
	
	
	public int deleteEmp(int empid) {
		int result = 0;
		String sql = "delete from employees where employee_id = ?";
		Connection conn;
		PreparedStatement st = null;
		conn = null;
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	public int updateEmp(EmpVO emp) {
		int result = 0;
		String sql =
				  " update employees set "
				+ " FIRST_NAME = ?,  "
				+ " LAST_NAME = ?,  "
				+ " EMAIL = ?, "
				+ " PHONE_NUMBER = ?, "
				+ " HIRE_DATE = ?,  "
				+ " JOB_ID = ?,  "
				+ " SALARY = ?,  "
				+ " COMMISSION_PCT = ?, "
				+ " MANAGER_ID = ?, "
				+ " DEPARTMENT_ID = ?  "
				+ " where employee_id = ?";
		Connection conn;
		PreparedStatement st = null;
		conn = null;
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5,  emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			//if(emp.getManager_id())
			st.setInt(9, emp.getManager_id());
			st.setInt(10, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
	}
	
	public int insertEmp(EmpVO emp) {
		String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement st = null;
		int result = 0;
		
		try {
			conn = datasource.getConnection(); 
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6,  emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
			conn.commit();     //2.占싹놂옙占쏙옙 set占쏙옙 占싫되몌옙 commit占쏙옙 占싫듸옙
		} catch (SQLException e) {
			try {
				conn.rollback(); //3.commit占쏙옙 占싫되몌옙 rollback
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	
	
	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employees order by 1";
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	public EmpVO selectbyId(int empid) {
		EmpVO emp = null;
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs = st.executeQuery();
			while(rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emp;
	}
	
	
	public List<EmpVO> selectByDept(int deptid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where department_id = ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	public List<EmpVO> selectByJobId(String jobid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where job_id = ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);;
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	public List<EmpVO> selectByHireDate(String sdate, String edate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, sdate);
			st.setString(2, edate);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	public List<EmpVO> selectByHireDate2(Date sdate, Date edate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where hire_date between ? and ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setDate(1, sdate);
			st.setDate(2, edate);
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	public List<EmpVO> selectByChar(String ch) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name like '%'||?||'%'";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, ch);
			//st.setString(1, "%"+ch+"%");
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	public List<EmpVO> selectByCondition(int deptid, String jobid, int sal, Date hdate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = " select * from employees" +
					" where department_id = ?" +
					" and job_id = ?" +
					" and salary >= ?" +
					" and hire_date between ? and add_months(?, 24)";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			//st.setString(1, "%"+ch+"%");
			rs = st.executeQuery();
			while(rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	
	
	
	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO(); //占십뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙 占썩본占쏙옙占쏙옙占쌘뤄옙
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("employee_id"));
		emp.setFirst_name(rs.getString("first_name"));
		emp.setHire_date(rs.getDate("hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setLast_name(rs.getString("last_name"));
		emp.setManager_id(rs.getInt("manager_id"));
		emp.setPhone_number(rs.getString("phone_number"));
		emp.setSalary(rs.getInt("salary"));
		return emp;
	}
	
	
	
//	public List<EmpVO> selectByString(String namePart) {
//		List<EmpVO> emplist = new ArrayList<>();
//		Connection conn = DBUtil.getConnection();
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		String sql = "select employee_id 占쏙옙占쏙옙占쏙옙호, first_name 占싱몌옙, salary 占쌨울옙"
//				+ " from employees where first_name like '%'||?||'%'"
//				+ " order by 1";
//		try {
//			st = conn.prepareStatement(sql);
//			st.setString(1, namePart);
//			//st.setString(1, "%"+ch+"%");
//			rs = st.executeQuery();
//			while(rs.next()) {
//				emplist.add(makeEmp2(rs));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.dbClose(rs, st, conn);
//		}
//		return emplist;
//	}
//	
//	private EmpVO makeEmp2(ResultSet rs) throws SQLException {
//		EmpVO emp = new EmpVO(); //占십뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙 占썩본占쏙옙占쏙옙占쌘뤄옙
//		emp.setEmployee_id(rs.getInt("占쏙옙占쏙옙占쏙옙호"));
//		emp.setFirst_name(rs.getString("占싱몌옙"));
//		emp.setSalary(rs.getInt("占쌨울옙"));
//		return emp;
//	}

}
















