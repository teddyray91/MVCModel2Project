package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardDTO;

public class BoardWriteUpdate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardDTO> list = null;
		BoardDAO dao = new BoardDAO();
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		try {
			list = dao.Update_Boards(boardNum);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("BoardData", list);
	}

}
