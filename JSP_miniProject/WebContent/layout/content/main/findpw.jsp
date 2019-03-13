<%@page import="com.team3.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% MemberDTO dto = (MemberDTO)request.getAttribute("find_pw"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 서비스</title>
</head>
<body>
<%	
	if(request.getAttribute("changepw") != null){
		if((Integer)request.getAttribute("changepw") == 1) {
			out.println("<script>alert('비밀번호 변경 완료!'); location.href='index.team3';</script>");
			return;
		}
	}
%>
<%
	if(request.getParameter("findpw_chk") == null || request.getParameter("findpw_chk").equals("")){
%>
<form method="post" action="Action_Find_Pw.team3" onsubmit="return findpw()">
	<div>
	<p>분실한 비밀번호 찾기 서비스</p>
	<p>아이디 <input type="text" name="find_id" id="find_id"required></p>
	<p>이메일 <input type="text" name="find_email" id="find_email" required></p>
	<p>전화번호 <input type="text" name="find_phone" id="find_phone" required></p>
	<input type="submit" value="비밀번호찾기"><input type="button" value="취소" onclick="window.close()">
	</div>
</form>
<%
	return;
	}
	
	if(dto == null){
		out.println("<script>alert('해당 정보의 아이디가 존재하지 않습니다.');</script>");
%>
<form method="post" action="Action_Find_Pw.team3" onsubmit="return findpw()">
	<div>
	<p>분실한 비밀번호 찾기 서비스</p>
	<p>아이디 <input type="text" name="find_id" id="find_id"required></p>
	<p>이메일 <input type="text" name="find_email" id="find_email" required></p>
	<p>전화번호 <input type="text" name="find_phone" id="find_phone" required></p>
	<input type="submit" value="비밀번호찾기"><input type="button" value="취소" onclick="window.close()">
	</div>
</form>
<%
	return;
	}
%>
<form method="post" action="Action_Change_Pw.team3" onsubmit="return findpw()">
	<input type="hidden" name="find_name" value="<%= dto.getMemName() %>">
	<input type="hidden" name="find_id" value="<%= dto.getMemId() %>">
	<div>
	<p>분실한 비밀번호 찾기 서비스</p>
	<p>이름 <label><%= dto.getMemName() %></label></p>
	<p>아이디 <label><%= dto.getMemId() %></label></p>
	<p>기존비밀번호 <label><%= dto.getMemPw() %></label></p>
	<p>신규비밀번호 <input type="password" name="new_password" id="new_password"></p>
	<input type="submit" value="비밀번호변경"><input type="button" value="다음에변경하기" onclick="window.close()">
	</div>
</form>

</body>
</html>