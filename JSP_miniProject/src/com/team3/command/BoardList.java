package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardDTO;

public class BoardList implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = null;
		String BoardCategory = (String)request.getAttribute("boardCategory");
		int pagenum = 0;
		int viewwrite = 0;
		if(request.getParameter("pagenum") != null && request.getParameter("viewwrite") != null) {
			pagenum = Integer.parseInt(request.getParameter("pagenum"));
			viewwrite = Integer.parseInt(request.getParameter("viewwrite"));
		}
		
		try {			
				switch (BoardCategory) {
				case "free":
					list = dao.Select_Boards_All(BoardCategory, pagenum, viewwrite);
					break;
				case "study":
					list = dao.Select_Boards_All(BoardCategory, pagenum, viewwrite);
					break;
				case "part":
					list = dao.Select_Boards_All(BoardCategory, pagenum, viewwrite);
					break;
				case "job":
					list = dao.Select_Boards_All(BoardCategory, pagenum, viewwrite);
					break;
				case "notice":
					list = dao.Select_Boards_All(BoardCategory, pagenum, viewwrite);
					break;
				default:
					break;
				}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("boardData", list);
	}

}
