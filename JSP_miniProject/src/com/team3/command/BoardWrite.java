package com.team3.command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.team3.dao.BoardDAO;

public class BoardWrite implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		BoardDAO dao = new BoardDAO();
		
		int memNumber = (Integer)request.getSession().getAttribute("number");
		String boardName = (String)request.getSession().getAttribute("name");
		String boardSubject = "";
		String boardContent = "";
		String boardCategory = "";
		String OriginalFile = "";
		String SystemFile = "";
		
		String path = request.getSession().getServletContext().getRealPath("/upload");

		int maxPostSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(
					request, 
					path, 
					maxPostSize, 
					encoding,
					policy
					);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Enumeration enums = multi.getParameterNames();
		while(enums.hasMoreElements()) {
			String name = enums.nextElement().toString();
			if(name.equals("board_Subject")) {
				boardSubject = multi.getParameter(name);
			}
			if(name.equals("board_Content")) {
				boardContent = multi.getParameter(name);
			}
			if(name.equals("board_Category")) {
				boardCategory = multi.getParameter(name);
			}
		}

		enums = multi.getFileNames();
		while(enums.hasMoreElements()) {
			String name = enums.nextElement().toString();
			OriginalFile = multi.getOriginalFileName(name);
			SystemFile = multi.getFilesystemName(name);
		}
		
		if(!((boardSubject == null || boardSubject.equals("")) || (boardContent == null || boardContent.equals("")))) {
			try {
				switch (boardCategory) {
				case "free":
					count =	dao.Insert_Boards_Write(memNumber, boardSubject, boardName, boardContent, boardCategory);
					break;
				case "study":
					count =	dao.Insert_Boards_Write(memNumber, boardSubject, boardName, boardContent, boardCategory);
					break;
				case "part":
					count =	dao.Insert_Boards_Write(memNumber, boardSubject, boardName, boardContent, boardCategory);
					break;
				case "job":
					count =	dao.Insert_Boards_Write(memNumber, boardSubject, boardName, boardContent, boardCategory);
					break;
				case "notice":
					count =	dao.Insert_Boards_Write(memNumber, boardSubject, boardName, boardContent, boardCategory);
					break;
				default:
					break;
				}
				
				enums = multi.getFileNames();
				if(enums.hasMoreElements()) {
					String name = enums.nextElement().toString();
					if(multi.getOriginalFileName(name) != null) {
						File files = multi.getFile(name);
						if(files != null) {
							long fileSize = files.length();
							System.out.println(fileSize);
						}
						dao.Insert_Boards_File(memNumber, boardSubject, OriginalFile, SystemFile);
					}
				}
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("writeSuccess", count);
		request.setAttribute("boardCategory", boardCategory);
	}

}
