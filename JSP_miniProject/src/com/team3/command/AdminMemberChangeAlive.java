package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class AdminMemberChangeAlive implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		int cnt = 0;
		String gopage = "";
		if(request.getParameter("gopage") == null || request.getParameter("gopage").equals("")) {
			gopage = "adminfrom";
		}else {
			gopage = request.getParameter("gopage");
		}
		
		try {
			if(request.getParameter("number") == null) {
				cnt = 0;
				gopage = "error";
			}else {
				cnt = dao.setAlive(Integer.parseInt(request.getParameter("number")));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("goto", gopage);
		request.setAttribute("AliveCheck", cnt);
	}

}
