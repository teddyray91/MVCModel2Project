<%@page import="com.team3.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
$(document).ready(function(){
	$("#viewwrite").change(function(){
		var value = $("#viewwrite option:selected").val()
		location.href = "adminboard.team3?pagenum=1&viewwrite="+value;
	});
});
</script>
<%
	@SuppressWarnings("unchecked")
	List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("BoardData");
	if(list == null){
		out.println("<script>alert('게시글을 찾을 수 없습니다.'); history.back();</script>");
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
게시글리스트
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
		<td><h1 style="text-align: center;">게시글이 없습니다.</h1></td>
		</tr>
	<% } else { %>
	<tr>
		<th>글번호</th><th>카테고리</th><th>글제목</th><th>작성자</th><th>조회수</th><th>좋아요</th><th>싫어요</th><th>등록일자</th><th>삭제</th>
	</tr>
		<% for(int i=0; i<viewwrite; i++) { 
				if(i == list.size()) break;
		%>
			<tr class="writeList">
				<td><%= list.get(i).getRowNum() %></td>
				<%	if(list.get(i).getBoardCategory().equals("free")){	%>
				<td>자유게시판</td>
				<%	} else if(list.get(i).getBoardCategory().equals("notice")){	%>
				<td>공지사항</td>
				<%	} else if(list.get(i).getBoardCategory().equals("job")){	%>
				<td>직업게시판</td>
				<%	} else if(list.get(i).getBoardCategory().equals("part")){	%>
				<td>전공게시판</td>
				<%	} else if(list.get(i).getBoardCategory().equals("study")){	%>
				<td>스터디그룹</td>
				<%	} %>
				<td><a href="WriteView.team3?boardNum=<%= list.get(i).getBoardNum() %>"><%= list.get(i).getBoardSubject() %></a></td>
				<td><%= list.get(i).getBoardName() %></td>
				<td><%= list.get(i).getBoardViewCount() %></td>
				<td><%= list.get(i).getBoardLike() %></td>
				<td><%= list.get(i).getBoardHate() %></td>
				<td><%= list.get(i).getBoardRegDate() %></td>
				<td><input type="button" value="삭제" onclick="admindeleteboard(<%= list.get(i).getBoardNum() %>)"></td>
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
			<span><a href="adminboard.team3?totalpage=<%= total_page %>&pagenum=<%= i %>&viewwrite=<%= viewwrite %>"><%= i %></a></span>
<%
		}else{
%>
			<span><a href="adminboard.team3?totalpage=<%= total_page %>&pagenum=<%= i %>&viewwrite=<%= viewwrite %>"><%= i %></a> | </span>
<%
		}
	}
%>
	</p>
</div>
</div>
