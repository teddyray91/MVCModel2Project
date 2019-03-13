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

public class CommentDelete implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardCommentDTO> list = null;
		BoardDAO dao = new BoardDAO();
		
		int commentNumber = Integer.parseInt(request.getParameter("commentNum"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNum"));
		
		try {
			list = dao.Delete_Comment(commentNumber, boardNumber);
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			for(int i=0; i<list.size(); i++) {
				JSONObject arrayObject = new JSONObject();
				
				arrayObject.put("mem_num", list.get(i).getMemNum());
				arrayObject.put("board_num", list.get(i).getBoardNum());
				arrayObject.put("comment_num", list.get(i).getCommentNum());
				arrayObject.put("comment_content", list.get(i).getCommentContent());
				arrayObject.put("comment_regdate", list.get(i).getCommentRegDate());
				arrayObject.put("comment_name", list.get(i).getCommentName());
				
				jsonArray.put(arrayObject);
			}
			
			jsonObject.put("BoardCommentData", jsonArray);
			
			try {
				String jsonString = jsonObject.toString();
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(jsonString);
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
