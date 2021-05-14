package com.kosta.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kosta.util.DBUtil;

public class BoardDAO {
	public int deleteBoard(int boardNo, String passwd) {
		int result = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		BoardVO board = selectByNo(boardNo);
		String sql = "delete from board where board_seq = ? and board_password = ?";
		
		try {
			//if(!board.getBoard_password().equals(passwd)) throw new SQLException("��й�ȣ����!! �����Ұ�");
			st = conn.prepareStatement(sql);
			st.setInt(1, boardNo);
			st.setString(2, passwd);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	public int updateBoard(BoardVO board) {
		int result = 0;
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		String sql = " update board set "
				+ " BOARD_TITLE = ?, "
				+ " BOARD_CONTENTS = ?, "
				//+ " BOARD_WRITER = ?, "
				+ " BOARD_DATE = sysdate, "
				//+ " BOARD_VIEWCOUNT = ?, "
				+ " BOARD_PASSWORD = ?, "
				+ " BOARD_IMAGE = ? "
				+ " where BOARD_SEQ = ?";
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(5, board.getBoard_seq());
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			//st.setInt(3, board.getBoard_writer());
			//st.setDate(4, board.getBoard_date());
			//st.setInt(5, board.getBoard_viewcount());
			st.setString(3, board.getBoard_password());
			st.setString(4, board.getBoard_image());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	public int insertBoard(BoardVO board) {
		int result = 0;
		Connection conn;
		PreparedStatement st = null;
		conn = DBUtil.getConnection();
		String sql = "insert into board values(board_autonum.nextval,?,?,?,sysdate,0,?,?)";
		try {
			st = conn.prepareStatement(sql);
			//st.setInt(1, board.getBoard_seq());
			st.setString(1, board.getBoard_title());
			st.setString(2, board.getBoard_contents());
			st.setInt(3, board.getBoard_writer());
			//st.setDate(5, board.getBoard_date());
			//st.setInt(6, board.getBoard_viewcount());
			st.setString(4, board.getBoard_password());
			st.setString(5, board.getBoard_image());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}
	
	public List<BoardVO> selectAll(){
		List<BoardVO> boardlist = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from board order by 1";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BoardVO makeRs = makeBoard(rs);
				boardlist.add(makeRs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return boardlist;
	}
	
	//�󼼺��� (Board��ȣ�� �󼼺���)
	public BoardVO selectByNo(int boardNo) {
		BoardVO board = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from board where Board_seq = ?";
		String sql2 = " update board "
					+ " set board_viewcount = nvl(board_viewcount,0) + 1 "
					+ " where Board_seq = ?";
		int result = 0;
		try {
			st = conn.prepareStatement(sql2);
			st.setInt(1,boardNo);
			result = st.executeUpdate();
			st = conn.prepareStatement(sql);
			st.setInt(1, boardNo);
			rs = st.executeQuery();
			while(rs.next()) {
				board =  makeBoard(rs);
				System.out.println(result > 0 ? "board_viewcount ��������":"board_viewcount ��������");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}
		
		return board;
	}

	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		//ResultSet���� �о VO��ü�� �����.
		BoardVO board = new BoardVO();
		board.setBoard_seq(rs.getInt("Board_seq"));
		board.setBoard_title(rs.getString("Board_title"));
		board.setBoard_contents(rs.getString("Board_contents"));
		board.setBoard_writer(rs.getInt("Board_writer"));
		board.setBoard_date(rs.getDate("Board_date"));
		board.setBoard_viewcount(rs.getInt("Board_viewcount"));
		board.setBoard_password(rs.getString("Board_password"));
		board.setBoard_image(rs.getString("Board_image"));
		return board;
	}
	
	
}

