<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String KnowPage;
	request.setCharacterEncoding("utf-8");
	if(request.getParameter("page") == null){
		KnowPage = "main";
	}
	else {
		KnowPage = request.getParameter("page");
	}
	System.out.print(KnowPage);
%>
<div id="lo_content">
	<div class="lo_content_main">
<%
	if(KnowPage.equals("main")){
%>
		<jsp:include page="/layout/content/main/main_content.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("freeboard")){
%>
		<jsp:include page="/layout/content/board/freeboard/freeboard.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("noticeboard")){
%>
		<jsp:include page="/layout/content/board/notice/notice.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("studyboard")){
%>
		<jsp:include page="/layout/content/board/studyboard/studyboard.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("partboard")){
%>
		<jsp:include page="/layout/content/board/partboard/partboard.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("jobboard")){
%>
		<jsp:include page="/layout/content/board/jobboard/jobboard.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("admin")) {
%>
		<jsp:include page="/layout/errorpage/error.jsp"></jsp:include>
<%
	}
	if(KnowPage.equals("usermodify")) {
%>
		<jsp:include page="/layout/content/main/membermodify.jsp"></jsp:include>
<%
	}
	if(KnowPage.endsWith("userdel")) {
%>
		<jsp:include page="/layout/content/main/memberdelete.jsp"></jsp:include>
<%
	}
%>
<% if(KnowPage.equals("freeboardWriteForm")) { %>
		<jsp:include page="/layout/content/board/freeboard/freeboardWriteForm.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("jobboardWriteForm")) { %>
		<jsp:include page="/layout/content/board/jobboard/jobboardWriteForm.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("partboardWriteForm")) { %>
		<jsp:include page="/layout/content/board/partboard/partboardWriteForm.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("studyboardWriteForm")) { %>
		<jsp:include page="/layout/content/board/studyboard/studyboardWriteForm.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("noticeboardWriteForm")) { %>
		<jsp:include page="/layout/content/board/notice/noticeWriteForm.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("WriteView")) { %>
		<jsp:include page="/layout/content/board/WriteView.jsp"></jsp:include>
<% } %>
<% if(KnowPage.equals("Update")) { %>
		<jsp:include page="/layout/content/board/UpdateForm.jsp"></jsp:include>
<% } %>

	</div>
</div>