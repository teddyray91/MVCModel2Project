package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team3.constant.D;
import com.team3.dto.MemberDTO;

public class MemberDAO {

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDAO() { }
	
	public Connection getConnection() throws NamingException, SQLException {
		Context init = new InitialContext();
		Context Env = (Context)init.lookup("java:/comp/env");
		DataSource ds = (DataSource)Env.lookup("jdbc/oracle");
		
		return ds.getConnection();
	}
	
	public int InsertMember(String memId, String memPw, String memEmail, String memName, String memSchool, String memPart, String memJob, String memBirth, String memGender, String memPhone) throws NamingException, SQLException {
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.INSERT_MEMBER);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPw);
			pstmt.setString(3, memName);
			pstmt.setString(4, memBirth);
			pstmt.setString(5, memEmail);
			pstmt.setInt(6, Integer.parseInt(memGender));
			pstmt.setString(7, memPhone);
			pstmt.setString(8, memSchool);
			pstmt.setString(9, memPart);
			pstmt.setString(10, memJob);
			count = pstmt.executeUpdate();
		} finally {
			Close();
		}
		
		return count;
	}
	
	public boolean SelectMemberId(String memID) throws NamingException, SQLException {
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SELECT_MEMBER_ID);
			pstmt.setString(1, memID);
			
			rs = pstmt.executeQuery();
		
			return rs.next();
		} finally {
			Close();
		}
	}
	
	public List<MemberDTO> SelectMemberId_Pw(String memID, String memPW) throws NamingException, SQLException {
		List<MemberDTO> list = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SELECT_MEMBER_ID_PW);
			pstmt.setString(1, memID);
			pstmt.setString(2, memPW);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				list = new ArrayList<>();
				list.add(new MemberDTO(1, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate));
				
				return list;
			}
			
			return list;
		} finally {
			Close();
		}
	}
	// NEW LINE //
	public MemberDTO getFindId(String name, String email, String phone) throws NamingException, SQLException {
		MemberDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.FIND_ID);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				dto = new MemberDTO(1, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate);
				
				return dto;
			}
			
			return dto;
		} finally {
			Close();
		}
		
	}
	public MemberDTO getFindPw(String id, String email, String phone) throws NamingException, SQLException {
		MemberDTO dto = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.FIND_PW);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, phone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				dto = new MemberDTO(1, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate);
				
				return dto;
			}
			
			return dto;
		} finally {
			Close();
		}
		
	}
	public int setFindChagePw(String name, String id, String newpassword) throws NamingException, SQLException {
		int cnt = 0;
		int memNum = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.CHANGE_PW_P);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memNum = rs.getInt("mem_num");
			}
			rs.close();
			pstmt.close();
			pstmt = conn.prepareStatement(D.CHANGE_PW);
			pstmt.setString(1, newpassword);
			pstmt.setInt(2, memNum);
			
			cnt = pstmt.executeUpdate();
			conn.commit();
			
			return cnt;
		} finally {
			Close();
		}
		
	}
	
	public List<MemberDTO> AllSeleteMember(int pagenum, int viewwrite) throws NamingException, SQLException {
		List<MemberDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select count(*) totalmember from members");
			rs = pstmt.executeQuery();
			rs.next();
			int totalmember = rs.getInt("totalmember");
			rs.close();
			pstmt.close();
			
			if(pagenum == 0) {
				pstmt = conn.prepareStatement(D.ALL_SELECT_MEMBER);
			}else {
				pstmt = conn.prepareStatement(D.ALL_SELECT_MEMBER2);
				pstmt.setInt(1, totalmember);
				pstmt.setInt(2, pagenum);
				pstmt.setInt(3, viewwrite);
				pstmt.setInt(4, viewwrite);
			}
			
			rs = pstmt.executeQuery();
			conn.commit();
			
			while(rs.next()) {
				int rowNum = rs.getInt("ROWNUM");
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				list.add(new MemberDTO(rowNum, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate));
			}
			
			return list;
		} finally {
			Close();
		}
		
	}
	
	public List<MemberDTO> AllSeleteAliveMember(int pagenum, int viewwrite) throws NamingException, SQLException {
		List<MemberDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select count(*) totalmember from members where mem_alive=0");
			rs = pstmt.executeQuery();
			rs.next();
			int totalmember = rs.getInt("totalmember");
			rs.close();
			pstmt.close();
			
			if(pagenum == 0) {
				pstmt = conn.prepareStatement(D.ALL_SELECT_ALIVE_MEMBER);
			}else {
				pstmt = conn.prepareStatement(D.ALL_SELECT_ALIVE_MEMBER2);
				pstmt.setInt(1, totalmember);
				pstmt.setInt(2, pagenum);
				pstmt.setInt(3, viewwrite);
				pstmt.setInt(4, viewwrite);
			}
			
			rs = pstmt.executeQuery();
			conn.commit();
			while(rs.next()) {
				int rowNum = rs.getInt("ROWNUM");
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				list.add(new MemberDTO(rowNum, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate));
			}
			
			return list;
		} finally {
			Close();
		}
		
	}
	
	public int setAlive(int memNum) throws NamingException, SQLException {
		int cnt = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.CHANGE_ALIVE);
			pstmt.setInt(1, memNum);
			cnt = pstmt.executeUpdate();

			return cnt;
		} finally {
			Close();
		}
		
	}
	
	public List<MemberDTO> FindMember(int memnum) throws NamingException, SQLException {
		List<MemberDTO> list = new ArrayList<>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.FIND_MEMBER);
			pstmt.setInt(1, memnum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int memNum = rs.getInt("mem_num");
				int aLive = rs.getInt("mem_alive");
				int super_num = rs.getInt("mem_super");
				String memId = rs.getString("mem_id");
				String memPw = rs.getString("mem_pw");
				String memEmail = rs.getString("mem_email");
				String memName = rs.getString("mem_name");
				String memSchool = rs.getString("mem_school");
				String memPart = rs.getString("mem_part");
				String memJob = rs.getString("mem_job");
				String memBirth = rs.getString("mem_birth");
				int memGender = Integer.parseInt(rs.getString("mem_gender"));
				String memPhone = rs.getString("mem_phone");
				String memCreatedate = rs.getString("mem_create");
				
				list.add(new MemberDTO(1, memNum, aLive, super_num, memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone, memCreatedate));
			}
			
			return list;
		} finally {
			Close();
		}
		
	}
	
	public int UpdateMember(String password, String email, String school, String part, String job, String phone, int memnum) throws NamingException, SQLException {
		int cnt = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.UPDATE_MEMBER);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			pstmt.setString(3, school);
			pstmt.setString(4, part);
			pstmt.setString(5, job);
			pstmt.setString(6, phone);
			pstmt.setInt(7, memnum);
			cnt = pstmt.executeUpdate();
			
			return cnt;
		} finally {
			Close();
		}
	}
	
	public int DeleteMember(int memnum) throws NamingException, SQLException {
		int cnt = 0;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			//FILE DB 정리
			pstmt = conn.prepareStatement(D.USER_DELETE_FILE);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			//COMMENT 정리
			pstmt = conn.prepareStatement(D.USER_DELETE_COMMENT1);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(D.USER_DELETE_COMMENT2);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			//RECOMMEND 정리
			pstmt = conn.prepareStatement(D.USER_DELETE_RECOMMEND1);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = conn.prepareStatement(D.USER_DELETE_RECOMMEND2);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			//BOARD 정리
			pstmt = conn.prepareStatement(D.USER_DELETE_WRITE);
			pstmt.setInt(1, memnum);
			pstmt.executeUpdate();
			pstmt.close();
			//MEMBER 정리
			pstmt = conn.prepareStatement(D.USER_DELETE);
			pstmt.setInt(1, memnum);
			cnt = pstmt.executeUpdate();
			
			conn.commit();
			
			return cnt;
		} finally {
			conn.rollback();
			Close();
		}
	}
	
	void Close() throws SQLException {
		if(rs != null) { rs.close(); }
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
	}
}
