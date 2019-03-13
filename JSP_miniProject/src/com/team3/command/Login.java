package com.team3.command;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.dao.MemberDAO;
import com.team3.dto.MemberDTO;

public class Login implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> list = null;
		
		String memID = request.getParameter("id");
		String memPW = request.getParameter("password");
		
		if(memID.equals("") || memPW.equals("") || memID == null || memPW == null) { // Null 또는 빈문자 확인
			request.setAttribute("loginCheck", 0);
			return;
		} else {
				try {
					list = dao.SelectMemberId_Pw(memID, memPW);
					if((list != null) && memID.equals(list.get(0).getMemId()) && memPW.equals(list.get(0).getMemPw())) {
							String sessionname1 = "number";
							int sessionvalue1 = list.get(0).getMemNum();
							System.out.println(sessionvalue1);
							String sessionname2 = "id";
							String sessionvalue2 = list.get(0).getMemId();
							String sessionname3 = "pw";
							String sessionvalue3 = list.get(0).getMemPw();
							String sessionname4 = "name";
							String sessionvalue4 = list.get(0).getMemName();
							String sessionname5 = "ok";
							String sessionvalue5 = "1";
							
							request.getSession().setAttribute(sessionname1, sessionvalue1);
							request.getSession().setAttribute(sessionname2, sessionvalue2);
							request.getSession().setAttribute(sessionname3, sessionvalue3);
							request.getSession().setAttribute(sessionname4, sessionvalue4);
							request.getSession().setAttribute(sessionname5, sessionvalue5);
							request.getSession().setAttribute("admin", list.get(0).getSuper_num());
							
							request.setAttribute("loginCheck", 1);
							request.setAttribute("alive", list.get(0).getaLive());
						
					} else { // 로그인 성공시 전달값
						request.setAttribute("loginCheck", 0);
					}
				} catch (NamingException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	} // execute END
