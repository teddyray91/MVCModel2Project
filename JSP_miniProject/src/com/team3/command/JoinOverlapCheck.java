package com.team3.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class JoinOverlapCheck implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		boolean overlapFlag = false;
		
		String memID = request.getParameter("memID");
		
		try {
			overlapFlag = dao.SelectMemberId(memID);
		} catch (NamingException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(String.valueOf(overlapFlag));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}