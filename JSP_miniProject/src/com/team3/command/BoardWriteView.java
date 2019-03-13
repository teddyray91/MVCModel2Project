package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardCommentDTO;
import com.team3.dto.BoardDTO;

public class BoardWriteView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = null;
		List<BoardCommentDTO> list2 = null;

		int boardNumber = Integer.parseInt(request.getParameter("boardNum"));
		
		try {
			list = dao.Select_Boards_Write_View(boardNumber);
			list2 = dao.Select_Comment(boardNumber);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("boardData", list);
		request.setAttribute("CommentData", list2);
	}

}