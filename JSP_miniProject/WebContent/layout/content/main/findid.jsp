<%@page import="com.team3.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% MemberDTO dto = (MemberDTO)request.getAttribute("find_id"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>아이디 찾기 서비스</title>
</head>
<body>
<%
	if(request.getParameter("findid_chk") == null || request.getParameter("findid_chk").equals("")){
%>
<form method="post" action="Action_Find_Id.team3" onsubmit="return findid()">
	<div>
	<p>분실한 아이디 찾기 서비스</p>
	<p>이름 <input type="text" name="find_name" id="find_name"required></p>
	<p>이메일 <input type="text" name="find_email" id="find_email" required></p>
	<p>전화번호 <input type="text" name="find_phone" id="find_phone" required></p>
	<input type="submit" value="아이디찾기"><input type="button" value="취소" onclick="window.close()">
	</div>
</form>
<%
	return;
	}
	
	if(dto == null){
		out.println("<script>alert('해당 정보의 아이디가 존재하지 않습니다.');</script>");
%>
<form method="post" action="Action_Find_Id.team3" onsubmit="return findid()">
	<div>
	<p>분실한 아이디 찾기 서비스</p>
	<p>이름 <input type="text" name="find_name" id="find_name"required></p>
	<p>이메일 <input type="text" name="find_email" id="find_email" required></p>
	<p>전화번호 <input type="text" name="find_phone" id="find_phone" required></p>
	<input type="submit" value="아이디찾기"><input type="button" value="취소" onclick="window.close()">
	</div>
</form>
<%
	return;
	}
%>
<form method="post" action="Action_Find_Id.team3" onsubmit="return findid()">
	<div>
	<p>분실한 아이디 찾기 서비스</p>
	<p>이름 <input type="text" disabled="disabled" value="<%= dto.getMemName() %>"></p>
	<p>이메일 <input type="text" disabled="disabled" value="<%= dto.getMemEmail() %>"></p>
	<p>아이디 <input type="text" disabled="disabled" value="<%= dto.getMemId() %>"></p>
	<input type="button" value="확인" onclick="window.close()">
	</div>
</form>

</body>
</html>