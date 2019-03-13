<%@page import="com.team3.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
	$("#viewwrite").change(function(){
		var value = $("#viewwrite option:selected").val()
		location.href = "adminalive.team3?pagenum=1&viewwrite="+value;
	});
});
</script>
<%
	@SuppressWarnings("unchecked")
	List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("MemberData");
	String loginCheck = "1";
	if(list == null){
		out.println("<script>alert('회원정보를 찾을 수 없습니다.'); history.back();</script>");
		return;
	}

	int viewwrite = 0;
	if(request.getParameter("viewwrite") == null){
		viewwrite = 10;	
	}else {
		viewwrite = Integer.parseInt(request.getParameter("viewwrite"));
	}
	int total_page = 0;
	if(request.getParameter("totalpage") == null){
		for(int j = 0 ; j < list.size()/viewwrite ; j++){
			total_page++;
		}
		if(total_page*viewwrite < list.size()){
			total_page++;
		}
	}else{
		total_page = Integer.parseInt(request.getParameter("totalpage"));
	}
%>
<div class="admin_list">
<h1 class="head">
가입대기리스트
<select id="viewwrite">
<% if(viewwrite == 10) {%>
	<option value="10" selected>10</option>
	<option value="15">15</option>
	<option value="20">20</option>
<% } %>
<% if(viewwrite == 15) {%>	
	<option value="10">10</option>
	<option value="15" selected>15</option>
	<option value="20">20</option>
<% } %>
<% if(viewwrite == 20) {%>	
	<option value="10">10</option>
	<option value="15">15</option>
	<option value="20" selected>20</option>
<% } %>
</select>
</h1>
<hr class="sethr">
<table class="boardList" style="width:100%;">
	<% if(list.size() == 0) { %>
		<tr>
		<td><h1 style="text-align: center;">검색된 회원이 없습니다.</h1></td>
		</tr>
	<% } else { %>
	<tr>
		<th>회원번호</th><th>승인</th><th>권한</th><th>이름</th><th>생년월일</th><th>아이디</th><th>가입일자</th><th>삭제</th>
	</tr>
		<% for(int i=0; i<viewwrite; i++) { 
				if(i == list.size()) break;
		%>
			<tr class="writeList">
				<td><%= list.get(i).getRowNum() %></td>
				<% if(list.get(i).getaLive() == 0) {%>
				<td><input type="button" value="승인" onclick="location.href='changealive.team3?number=<%= list.get(i).getMemNum() %>&gopage=adminalive'"></td>
				<% }else{ %>
				<td>완료</td>
				<% } %>
				<td><%= list.get(i).getSuper_num() %></td>
				<td><%= list.get(i).getMemName() %></td>
				<td><%= list.get(i).getMemBirth() %></td>
				<td><%= list.get(i).getMemId() %></td>
				<td><%= list.get(i).getMemCreatedate() %></td>
				<td><input type="button" value="삭제" onclick="userdelete(<%= list.get(0).getMemNum() %>)"></td>
			</tr>
		<% } %>
	<% } %>
</table>
<div align="center">
	<p class="page_num">
<%
	for(int i = 1; i <= total_page ; i ++){
		if(i == total_page){
%>
			<span><a href="adminalive.team3?totalpage=<%= total_page %>&pagenum=<%= i %>&viewwrite=<%= viewwrite %>"><%= i %></a></span>
<%
		}else{
%>
			<span><a href="adminalive.team3?totalpage=<%= total_page %>&pagenum=<%= i %>&viewwrite=<%= viewwrite %>"><%= i %></a> | </span>
<%
		}
	}
%>
	</p>
</div>
</div>