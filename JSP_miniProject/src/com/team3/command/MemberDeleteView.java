package com.team3.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class MemberDeleteView implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		BoardDAO board = new BoardDAO();
		BoardDAO comment = new BoardDAO();
		List<MemberDTO> member = new ArrayList<>();
		int boardcount = 0;
		int commentcount = 0;
		
		int memnum = 0;
		if(request.getParameter("memnum") == null) {
			return;
		}else {
			memnum = Integer.parseInt(request.getParameter("memnum"));
		}
		
		try {
			member = dao.FindMember(memnum);
			boardcount = board.UserDeleteWriteView(memnum);
			commentcount = comment.UserDeleteCommentView(memnum);
			
			request.setAttribute("member", member);
			request.setAttribute("boardcount", boardcount);
			request.setAttribute("commentcount", commentcount);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
