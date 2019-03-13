package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class MemberFindId implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		String name = request.getParameter("find_name");
		String email = request.getParameter("find_email");
		String phone = request.getParameter("find_phone");
		
		if(name == null || name.equals("") || email == null || email.equals("") || phone == null || phone.equals("")) return;
		try {
			dto = dao.getFindId(name, email, phone);
			request.setAttribute("find_id", dto);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
