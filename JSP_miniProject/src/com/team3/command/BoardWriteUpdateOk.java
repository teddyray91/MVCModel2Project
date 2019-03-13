package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;

public class BoardWriteUpdateOk implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		BoardDAO dao = new BoardDAO();
		
		int boardNum = Integer.parseInt(request.getParameter("board_Num"));
		String boardSubject = request.getParameter("board_Subject");
		String boardContent = request.getParameter("board_Content");
		String boardCategory = request.getParameter("board_Category");
		
		try {
			count = dao.Update_Boards_Write(boardNum, boardSubject, boardContent);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("count", count);
		request.setAttribute("boardCategory", boardCategory);
		request.setAttribute("boardNum", boardNum);
	}

}
