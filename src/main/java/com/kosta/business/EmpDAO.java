package com.kosta.business;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.kosta.model.EmpVO;
import com.kosta.model.JobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.util.DBUtil;

//DAO(Data Access Object)
@Repository
public class EmpDAO implements EmpDAOInterface{

	@Autowired
	DataSource datasource;
	
	//CRUD(Create:insert, Read:select U:Update, D:Delete)	
	
	public EmpVO loginChk(int empid, String email) {
		EmpVO emp = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ? and email=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			st.setString(2, email);
			rs = st.executeQuery();
			while (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			while (rs.next()) {
				JobVO job = new JobVO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
				joblist.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return joblist;
	}
	
	public int deleteEmp(int empid) {
		int result = 0;
		String sql = "delete from employees where EMPLOYEE_ID = ? ";
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
				" update employees  set "+
			    " FIRST_NAME=?, "+           
			    " LAST_NAME=?, "+    
			    " EMAIL =?,  "+      
			    " PHONE_NUMBER=?,  "+      
			    " HIRE_DATE=?,  "+     
			    " JOB_ID=?,   "+
			    " SALARY=?,   "+            
			    " COMMISSION_PCT=?,   "+     
			    " MANAGER_ID=?,   "+        
			    " DEPARTMENT_ID=?   "+      
			" where EMPLOYEE_ID = ?   ";
					
		Connection conn;
		PreparedStatement st = null;
		conn = null;
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1,  emp.getFirst_name());
			st.setString(2,  emp.getLast_name());
			st.setString(3,  emp.getEmail());
			st.setString(4,  emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6,  emp.getJob_id());
			st.setInt(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			if(emp.getManager_id()==0)
				st.setNull(9, Types.INTEGER);
			else
				st.setInt(9, emp.getManager_id());
			
			st.setInt(10, emp.getDepartment_id());
			result = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}

	public int insertEmp(EmpVO emp) {
		String sql="insert into employees values(?,?,?,?,?,?,?,?,?,?,?) "; 
		Connection conn;
		PreparedStatement st = null;
		int result = 0;

		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2,  emp.getFirst_name());
			st.setString(3,  emp.getLast_name());
			st.setString(4,  emp.getEmail());
			st.setString(5,  emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7,  emp.getJob_id());
			st.setInt(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setInt(10, emp.getManager_id());
			st.setInt(11, emp.getDepartment_id());
			result = st.executeUpdate();
			conn.commit();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		
		
		return result;
	}

	public List<EmpVO> selectAll() {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from employees";
		try {
			conn = datasource.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpVO emp = makeEmp(rs);
				
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public EmpVO selectById(int empid) {
		EmpVO emp = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where employee_id = ?";
		try {
			Connection conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			rs = st.executeQuery();
			while (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectByJob(String jobid) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where job_id = ?";
		try {
			conn = datasource.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectBySalary(int minsal, int maxsal) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees where salary between ? and ?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, minsal);
			st.setInt(2, maxsal);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectByDate(String sdate, String edate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees " + " where hire_date between ? and  ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, sdate);
			st.setString(2, edate);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectByDate2(Date sdate, Date edate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees " + 
		" where hire_date between ? and  ?";
		try {
			st = conn.prepareStatement(sql);
			st.setDate(1, sdate);
			st.setDate(2, edate);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectByName(String ch) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees " + 
		" where first_name like  ?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, "%"+ch+"%");
		
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}

	public List<EmpVO> selectByCondition(int deptid, 
			                 String jobid, int sal,Date hdate) {
		List<EmpVO> emplist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from employees " + 
		        " where department_id =  ? " +
				" and job_id = ? "+
		        " and salary >= ? " +
				" and hire_date between ? and add_months(?, 24) ";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, deptid);
			st.setString(2, jobid);
			st.setInt(3, sal);
			st.setDate(4, hdate);
			st.setDate(5, hdate);
			rs = st.executeQuery();
			while (rs.next()) {
				emplist.add(makeEmp(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		return emplist;
	}
	

	private EmpVO makeEmp(ResultSet rs) throws SQLException {
		EmpVO emp = new EmpVO();
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
}
