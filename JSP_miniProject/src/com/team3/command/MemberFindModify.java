package com.team3.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class MemberFindModify implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = new ArrayList<>();
		int memnum = 0;
		if(request.getParameter("memnum") == null) {
			return;
		}else {
			memnum = Integer.parseInt(request.getParameter("memnum"));
		}
		try {
			list = dao.FindMember(memnum);
			request.setAttribute("userdata", list);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
