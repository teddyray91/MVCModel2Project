package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardDTO;

public class AdminBoardList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = null;
		String category = "";
		int pagenum = 0;
		int viewwrite = 0;
		if(request.getParameter("pagenum") != null && request.getParameter("viewwrite") != null) {
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
			viewwrite = Integer.parseInt(request.getParameter("viewwrite"));
		}
		
		try {
			if(request.getParameter("category") == null || request.getParameter("category").equals(""))
				category = "all";
			else category = request.getParameter("category");
			list = dao.AdminBoardList(category, pagenum, viewwrite);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("BoardData", list);
	}

}
