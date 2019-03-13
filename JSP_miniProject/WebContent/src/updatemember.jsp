<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int check = 0;
	if(request.getAttribute("updatemembercheck") == null){
		return;
	}else{
		check = (Integer)request.getAttribute("updatemembercheck");
	}
	
	if(check == 0){
		out.println("<script>alert('수정을 실패했습니다.'); history.back();</script>");
	}else{
		out.println("<script>alert('수정완료!!'); history.back();</script>");
	}
%>