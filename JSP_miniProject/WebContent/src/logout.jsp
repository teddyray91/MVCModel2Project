<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sessionname, sessionvalue;
	Enumeration<String> enumerationname =  session.getAttributeNames(); // Enumeration<String> Return
	while(enumerationname.hasMoreElements()){ // boolean return 다음 값이 있는지 물어보는 메소드(T | F)
		
		sessionname = enumerationname.nextElement().toString(); // 값을 가져오는 메소드
		session.removeAttribute(sessionname);
	} // While END
%>
<script>
	location.href="index.team3";
</script>