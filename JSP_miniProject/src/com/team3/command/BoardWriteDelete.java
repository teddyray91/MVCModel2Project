package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;

public class BoardWriteDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		BoardDAO dao = new BoardDAO();
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardCategory = request.getParameter("boardCategory");
		
		try {
			count = dao.Delete_Boards_Write(boardNum);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("count", count);
		request.setAttribute("boardCategory", boardCategory);
	}

}
