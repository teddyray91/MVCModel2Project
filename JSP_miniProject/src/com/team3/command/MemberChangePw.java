package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class MemberChangePw implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		int cnt = 0;
		String name = request.getParameter("find_name");
		String id = request.getParameter("find_id");
		String newpassword = request.getParameter("new_password");
		
		if(id == null || id.equals("") || name == null || name.equals("") || newpassword == null || newpassword.equals("")) return;
		try {
			cnt = dao.setFindChagePw(name, id, newpassword);
			request.setAttribute("changepw", cnt);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
