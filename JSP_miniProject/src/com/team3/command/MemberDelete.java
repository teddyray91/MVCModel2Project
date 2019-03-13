package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class MemberDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		int delcheck = 0;
		int membernum = 0;
		if(request.getParameter("membernum") == null || request.getParameter("membernum").equals("0")) {
			return;
		}else {
			membernum = Integer.parseInt(request.getParameter("membernum"));
		}

		try {
			delcheck = dao.DeleteMember(membernum);
			request.setAttribute("delcheck", delcheck);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
