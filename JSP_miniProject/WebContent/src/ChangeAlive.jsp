<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int checkPoint = (Integer)request.getAttribute("AliveCheck");
	String gopage = (String)request.getAttribute("goto");
	if(gopage.equals("error")){
		out.println("<script>location.href = '"+ gopage +".team3';</script>");
		return;
	}
	if(checkPoint == 0) return;
%>
<script>
	alert('승인완료');
	location.href = "<%= gopage %>"+".team3";
</script>