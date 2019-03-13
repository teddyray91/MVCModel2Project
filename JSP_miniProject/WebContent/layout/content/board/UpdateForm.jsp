<%@page import="com.team3.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginCheck = (String)session.getAttribute("ok");
	if(loginCheck == null) { 
		out.println("<script>alert('로그인이 필요합니다.');location.href = 'index.team3';</script>");
		return;
	}
	@SuppressWarnings("unchecked")
	List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("BoardData"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/write_Form.css">
<script type="text/javascript" src="js/write_check.js"></script>
</head>
<body>
<div class="allBox">
	<h1 class="head"><%= list.get(0).getBoardCategory().toUpperCase() %> - 수정하기(<%= list.get(0).getBoardName() %>)</h1>
	<form action="UpdateOk.team3" method="post" name="board" onsubmit="return writeCheck();">
		<hr class="sethr">
		<table style="border-collapse: collapse;">
			<tr>
				<th>제목</th><td><input class="subject" type="text" id="board_Subject" name="board_Subject" placeholder="제목을 입력해주세요.(50자 이하)" autofocus value="<%= list.get(0).getBoardSubject() %>" /></td>
			</tr>
			<tr>
				<th></th><td><button class="writeFunc1" type="button">가</button> <button type="button">IMG</button> <button class="writeFunc2" type="button">파일첨부</button></td>
			</tr>
			<tr>
				<th>내용</th><td><textarea class="board_textarea" id="board_Content" name="board_Content" placeholder="내용을 입력해주세요."><%= list.get(0).getBoardContent() %></textarea></td>
			</tr>
		</table>
		<hr>
		<div class="boardFunc">
			<input type="hidden" id="board_Num" name="board_Num" value="<%= list.get(0).getBoardNum() %>"/>
			<input type="hidden" id="board_Category" name="board_Category" value="<%= list.get(0).getBoardCategory() %>"/>
			<input type="submit" value="수정하기"/> <input type="button" value="취소" onclick="history.back();" />
		</div>
	</form>
</div>
</body>
</html>