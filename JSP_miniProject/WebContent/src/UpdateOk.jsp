<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int count = (Integer)request.getAttribute("count");
	String boardCategory = (String)request.getAttribute("boardCategory");
	int boardNum = (Integer)request.getAttribute("boardNum");
%>
<% if(count == 0) { %>
		<script>
			alert("수정에 실패하였습니다.");
			location.href = "<%= boardCategory %>board.team3";
		</script>
<% } else {%>
		<script>
			alert("수정되었습니다.");
			location.href = "WriteView.team3?boardNum=<%= boardNum %>";
		</script>
<% } %>