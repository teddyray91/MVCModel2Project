package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class MemberFindPw implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		String id = request.getParameter("find_id");
		String email = request.getParameter("find_email");
		String phone = request.getParameter("find_phone");
		
		if(id == null || id.equals("") || email == null || email.equals("") || phone == null || phone.equals("")) return;
		try {
			dto = dao.getFindPw(id, email, phone);
			request.setAttribute("find_pw", dto);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
