<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int count = (Integer)request.getAttribute("joinOk"); %>
<% if(count == 1) { %>
	<script>
		alert("회원가입에 성공하였습니다.");
		location.href = "index.team3";
	</script>
<% } else {%>
	<script>
		alert("회원가입에 실패하였습니다.");
		location.href = "index.team3";
	</script>
<% } %>