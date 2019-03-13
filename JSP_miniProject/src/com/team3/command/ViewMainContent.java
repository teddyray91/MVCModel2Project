package com.team3.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardDTO;

public class ViewMainContent implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao1 = new BoardDAO();
		BoardDAO dao2 = new BoardDAO();
		BoardDAO dao3 = new BoardDAO();
		BoardDAO dao4 = new BoardDAO();
		BoardDAO dao5 = new BoardDAO();
		BoardDAO dao6 = new BoardDAO();
		
		List<BoardDTO> noticelist = new ArrayList<>();
		List<BoardDTO> freelist = new ArrayList<>();
		List<BoardDTO> partlist = new ArrayList<>();
		List<BoardDTO> joblist = new ArrayList<>();
		List<BoardDTO> studylist = new ArrayList<>();
		List<BoardDTO> hitlist = new ArrayList<>();
		try {
			noticelist = dao1.MainContentList("notice");
			freelist = dao2.MainContentList("free");
			partlist = dao3.MainContentList("part");
			joblist = dao4.MainContentList("job");
			studylist = dao5.MainContentList("study");
			hitlist = dao6.MainContentList("hit");
			
			request.setAttribute("noticelist", noticelist);
			request.setAttribute("freelist", freelist);
			request.setAttribute("partlist", partlist);
			request.setAttribute("joblist", joblist);
			request.setAttribute("studylist", studylist);
			request.setAttribute("hitlist", hitlist);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
