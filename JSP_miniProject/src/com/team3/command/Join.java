package com.team3.command;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;

public class Join implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int count = 0;
		MemberDAO dao = new MemberDAO();
		
		String memId = request.getParameter("user_id");
		String memPw = request.getParameter("user_pw");
		String memEmail = request.getParameter("user_email");
		String memName = request.getParameter("user_name");
		String memSchool = request.getParameter("user_school");
		String memPart = request.getParameter("user_part");
		String memJob = request.getParameter("user_job");
		String memBirth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
		String memGender = request.getParameter("user_gen");
		String memPhone = request.getParameter("phone1")+request.getParameter("user_phone");
		
		if(memSchool.trim().equals("")) memSchool = "None";
		if(memPart.trim().equals("")) memPart = "None";
		if(memJob.trim().equals("")) memJob = "None";
		
		if(request.getParameter("user_id") != null && request.getParameter("user_pw") != null && request.getParameter("user_email") != null &&
			request.getParameter("user_name") != null && request.getParameter("user_school") != null && request.getParameter("user_part") != null &&
			request.getParameter("user_job") != null && request.getParameter("year") != null && request.getParameter("month") != null &&
			request.getParameter("day") != null && request.getParameter("user_gen") != null && request.getParameter("phone1") != null && request.getParameter("user_phone") != null) {
			
			if((memId != null && memId.trim().equals("")) && (memPw != null && memPw.trim().equals("")) && (memEmail != null && memEmail.trim().equals("")) &&
					(memName != null && memName.trim().equals("")) && (memSchool != null && memSchool.trim().equals("")) && (memPart != null && memPart.trim().equals("")) &&
					(memJob != null && memJob.trim().equals("")) && (memBirth != null && memBirth.trim().equals("")) && (memGender != null && memGender.trim().equals("")) && (memPhone != null && memPhone.trim().equals(""))) {
				return;
			}else {
				try {
					try {
						count = dao.InsertMember(memId, memPw, memEmail, memName, memSchool, memPart, memJob, memBirth, memGender, memPhone);
					} catch (NamingException e) {
						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}// 내부 값 확인 IF END

			
		} // 파라미터 값 확인 IF END
		
		request.setAttribute("joinOk", count);
	}

}