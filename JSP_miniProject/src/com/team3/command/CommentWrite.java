package com.team3.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardCommentDTO;

public class CommentWrite implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardCommentDTO> list = null;
		BoardDAO dao = new BoardDAO();
		
		int memNum = (Integer)request.getSession().getAttribute("number");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String commentName = (String)request.getSession().getAttribute("name");
		String commentContent = request.getParameter("commentContent");
		
		try {
			list = (List<BoardCommentDTO>)dao.Insert_Select_Comment(memNum, boardNum, commentName, commentContent);
			
			JSONObject jsonObject = new JSONObject();
			
			JSONArray jsonArray = new JSONArray();
			for(int i=0; i<list.size(); i++) {
				JSONObject jsonArraySub = new JSONObject();
				
				jsonArraySub.put("mem_num", list.get(i).getMemNum());
				jsonArraySub.put("board_num", list.get(i).getBoardNum());
				jsonArraySub.put("comment_num", list.get(i).getCommentNum());
				jsonArraySub.put("comment_name", list.get(i).getCommentName());
				jsonArraySub.put("comment_content", list.get(i).getCommentContent());
				jsonArraySub.put("comment_regdate", list.get(i).getCommentRegDate());
				
				jsonArray.put(jsonArraySub);
			}
			
			jsonObject.put("BoardCommentData", jsonArray);
			
			try {
				String jsonObjectString = jsonObject.toString();
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(jsonObjectString);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
