<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String adminpage = request.getParameter("adminpage");
%>
<div class="lo_content_notice_r" style="float:left;">
	<div class="content" style="border: 0px;">
		<ul class="lo_top_nav" style="display:block;">
			<li onclick="location.href='adminfrom.team3'" style="cursor: pointer; width:100%;">회원리스트</li>
			<li onclick="location.href='adminalive.team3'" style="cursor: pointer; width:100%;">가입승인대기</li>
			<li onclick="location.href='adminboard.team3'" style="cursor: pointer; width:100%;">게시글리스트</li>
		</ul>
	</div>
</div>

<div class="lo_content_notice_l" style="float:right;">
<%
	if(adminpage == null || adminpage.trim().equals("") || adminpage.trim().equals("memberlist")){
%>
	<div class="content" style="border: 0px;">
		<jsp:include page="admin_member_list.jsp"></jsp:include>
	</div>
<%
	return;
	}
	if(adminpage.equals("adminboard")){
%>
	<div class="content" style="border: 0px;">
		<jsp:include page="admin_board_list.jsp"></jsp:include>
	</div>
<%
	return;
	}
	if(adminpage.equals("memberalivelist")){
%>
	<div class="content" style="border: 0px;">
		<jsp:include page="admin_member_alivelist.jsp"></jsp:include>
	</div>
<%
	}
%>
</div>
