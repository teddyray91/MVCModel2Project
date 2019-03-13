package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class AdminAliveMemberList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = null;
		int pagenum = 0;
		int viewwrite = 0;
		if(request.getParameter("pagenum") != null && request.getParameter("viewwrite") != null) {
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
			viewwrite = Integer.parseInt(request.getParameter("viewwrite"));
		}
		
		try {			
			list = dao.AllSeleteAliveMember(pagenum, viewwrite);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("MemberData", list);
	}

}
