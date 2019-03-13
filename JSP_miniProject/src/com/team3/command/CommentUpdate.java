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

public class CommentUpdate implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<BoardCommentDTO> list = null;
		BoardDAO dao = new BoardDAO();
		
		int commentNumber = Integer.parseInt(request.getParameter("commentNum"));
		String commentContent = request.getParameter("commentContent");
		
		try {
			list = dao.Update_Comment(commentNumber, commentContent);
			
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			
			for(int i=0; i<list.size(); i++) {
				JSONObject ArrayObject = new JSONObject();
				
				ArrayObject.put("mem_num", list.get(i).getMemNum());
				ArrayObject.put("board_num", list.get(i).getBoardNum());
				ArrayObject.put("comment_num,", list.get(i).getCommentNum());
				ArrayObject.put("comment_content", list.get(i).getCommentContent());
				ArrayObject.put("comment_regdate", list.get(i).getCommentRegDate());
				ArrayObject.put("comment_name", list.get(i).getCommentName());
				
				jsonArray.put(ArrayObject);
			}
			
			jsonObject.put("CommentData", jsonArray);
			
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
