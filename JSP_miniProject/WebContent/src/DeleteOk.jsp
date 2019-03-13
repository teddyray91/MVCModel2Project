<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int count = (Integer)request.getAttribute("count");
	String boardCategory = (String)request.getAttribute("boardCategory");
%>
<%
	if(count == 0) { 
		if(boardCategory.equals("adminboard")){
			out.println("<script>alert('이미 삭제되었거나 존재하지 않는 글 입니다.');location.href = 'adminboard.team3';</script>");
			return;
		}
%>
		<script>
			alert("이미 삭제되었거나 존재하지 않는 글 입니다.");
			location.href = "<%= boardCategory %>board.team3";
		</script>
<%	} else { 
		if(boardCategory.equals("adminboard")){
			out.println("<script>alert('삭제되었습니다.');location.href = 'adminboard.team3';</script>");
			return;
		}
%>
		<script>
			alert("삭제되었습니다.");
			location.href = "<%= boardCategory %>board.team3";			
		</script>
<% } %>
