package com.team3.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.team3.dao.BoardDAO;
import com.team3.dto.BoardDTO;

public class Recommend implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = null;
		
		int boardNumber = Integer.parseInt( request.getParameter("boardNum"));
		int memNumber = Integer.parseInt( request.getParameter("memNum"));
		String Type = request.getParameter("Type");
	
		try {
			dto = dao.Insert_Boards_Recommend(boardNumber, memNumber, Type);
			
			JSONObject jsonObject = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			JSONObject arrayObject = new JSONObject();
			
			if(dto != null) {
				arrayObject.put("Like", dto.getBoardLike());
				arrayObject.put("Hate", dto.getBoardHate());

				jsonArray.put(arrayObject);
				jsonObject.put("BoardRecommendData", jsonArray);
				
				try {
					String jsonString = jsonObject.toString();
					response.setContentType("application/json; charset=utf-8");
					response.getWriter().write(jsonString);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().write(Type);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

}
