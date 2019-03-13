package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class MemberModify implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int cnt = 0;
		MemberDAO dao = new MemberDAO();
		int memnum;
		String password, email, school, part, job, phone;
		if(request.getParameter("memnum") == null || request.getParameter("password") == null || request.getParameter("email") == null || 
				request.getParameter("school") == null || request.getParameter("part") == null || request.getParameter("job") == null ||
				request.getParameter("phone") == null) {
			return;
		}else {
			if(request.getParameter("memnum").equals("") || request.getParameter("password").equals("") || request.getParameter("email").equals("") || 
				request.getParameter("school").equals("") || request.getParameter("part").equals("") || request.getParameter("job").equals("") ||
				request.getParameter("phone").equals("")) {
				return;
			}else {
				memnum = Integer.parseInt(request.getParameter("memnum"));
				password = request.getParameter("password");
				email = request.getParameter("email");
				school = request.getParameter("school");
				part = request.getParameter("part");
				job = request.getParameter("job");
				phone = request.getParameter("phone");
			}
		}
		
		try {
			cnt = dao.UpdateMember(password, email, school, part, job, phone, memnum);
			request.setAttribute("updatemembercheck", cnt);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
