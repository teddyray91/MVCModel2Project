<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/project.css" />
<link rel="stylesheet" type="text/css" href="css/board.css" />
<link rel="stylesheet" type="text/css" href="css/membermodify.css" />
<script type="text/javascript" src="js/source.js"></script>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>

<title>Team3 Project</title>
</head>
<body>
<div class="lo_main" align="center">
<%
	if(session.getAttribute("admin")==null ||session.getAttribute("admin").toString().equals("0")){
%>
	
	<%@ include file="layout/content/main/head.jsp" %>
	<%@ include file="layout/content/main/nav.jsp" %>
	<%@ include file="layout/content/main/content.jsp" %>
	<%@ include file="layout/content/main/footer.jsp" %>
<%	
	}else{
%>
	<%@ include file="layout/content/main/head.jsp" %>
	<%@ include file="layout/content/admin/adminnav.jsp" %>
	<%@ include file="layout/content/admin/admincontent.jsp" %>
	<%@ include file="layout/content/main/footer.jsp" %>
<%	} %>
</div>
</body>
</html>