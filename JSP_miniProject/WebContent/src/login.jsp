<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int logincheck = (Integer)request.getAttribute("loginCheck");
	int alive = 0;
	if(request.getAttribute("alive") != null){
		alive = (Integer)request.getAttribute("alive");
	}
	if(logincheck == 0) {
		out.println("<script>");
		out.println("alert('아이디 또는 비밀번호 입력이 잘못되었습니다.');");
		out.println("location.href='logout.team3'");
		out.println("</script>");
		return;
	}
	if(alive == 0){
		out.println("<script>");
		out.println("alert('아이디 사용이 불가능 합니다. 관리자에게 승인을 받으세요.');");
		out.println("location.href='logout.team3'");
		out.println("</script>");
		
		return;
	}
	if(logincheck == 1 && alive == 1) {
		response.sendRedirect("index.team3");
		return;
	}
%>