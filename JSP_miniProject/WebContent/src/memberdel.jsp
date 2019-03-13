<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int delcheck = 0;
	if(request.getAttribute("delcheck") == null ){
		out.println("<script>alert('탈퇴가 실패하였습니다.'); location.href='index.team3';</script>");
		return;
	}else{
		delcheck = (Integer)request.getAttribute("delcheck");
	}
	if(session.getAttribute("admin")==null ||session.getAttribute("admin").toString().equals("0")){
		if(delcheck == 1){
			out.println("<script>alert('반가웠습니다. 탈퇴가 완료되었습니다.'); location.href='logout.team3';</script>");
		}else if(delcheck == 0){
			out.println("<script>alert('탈퇴가 실패하였습니다.'); location.href='index.team3';</script>");
		}
	}else{
		if(delcheck == 1){
			out.println("<script>alert('탈퇴가 완료되었습니다.'); location.href='adminfrom.team3';</script>");
		}else if(delcheck == 0){
			out.println("<script>alert('탈퇴가 실패하였습니다.'); location.href='adminfrom.team3';</script>");
		}
	}
	
	
%>
