<%@page import="com.team3.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> noticelist = null;
	List<BoardDTO> hitlist = null;
	List<BoardDTO> freelist = null;
	List<BoardDTO> partlist = null;
	List<BoardDTO> joblist = null;
	List<BoardDTO> studylist = null;

	if(request.getAttribute("noticelist") != null)
		noticelist = (List<BoardDTO>)request.getAttribute("noticelist");
	if(request.getAttribute("hitlist") != null) 
		hitlist = (List<BoardDTO>)request.getAttribute("hitlist");
	if(request.getAttribute("freelist") != null) 
		freelist = (List<BoardDTO>)request.getAttribute("freelist");
	if(request.getAttribute("partlist") != null) 
		partlist = (List<BoardDTO>)request.getAttribute("partlist");
	if(request.getAttribute("joblist") != null) 
		joblist = (List<BoardDTO>)request.getAttribute("joblist");
	if(request.getAttribute("studylist") != null) 
		studylist = (List<BoardDTO>)request.getAttribute("studylist");
%>
<div class="lo_content_notice_l">
	<div class="content">
		<h1 class="main_content_title">공지사항</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(noticelist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 공지사항이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == noticelist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= noticelist.get(i).getBoardNum() %>"><%= noticelist.get(i).getBoardSubject() %></a></td>
				<td>공지사항</td>
				<td><%= noticelist.get(i).getBoardViewCount() %></td>
				<td><%= noticelist.get(i).getBoardLike() %></td>
				<td><%= noticelist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
	<div class="content">
		<h1 class="main_content_title">인기게시글</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(hitlist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 게시글이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == hitlist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= hitlist.get(i).getBoardNum() %>"><%= hitlist.get(i).getBoardSubject() %></a></td>
				<%	if(hitlist.get(i).getBoardCategory().equals("free")){	%>
				<td>자유게시판</td>
				<%	} else if(hitlist.get(i).getBoardCategory().equals("notice")){	%>
				<td>공지사항</td>
				<%	} else if(hitlist.get(i).getBoardCategory().equals("job")){	%>
				<td>직업게시판</td>
				<%	} else if(hitlist.get(i).getBoardCategory().equals("part")){	%>
				<td>전공게시판</td>
				<%	} else if(hitlist.get(i).getBoardCategory().equals("study")){	%>
				<td>스터디그룹</td>
				<%	} %>
				<td><%= hitlist.get(i).getBoardViewCount() %></td>
				<td><%= hitlist.get(i).getBoardLike() %></td>
				<td><%= hitlist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
	<div class="content">
		<h1 class="main_content_title">자유게시판</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(freelist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 게시글이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == freelist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= freelist.get(i).getBoardNum() %>"><%= freelist.get(i).getBoardSubject() %></a></td>
				<td>자유게시판</td>
				<td><%= freelist.get(i).getBoardViewCount() %></td>
				<td><%= freelist.get(i).getBoardLike() %></td>
				<td><%= freelist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
	<div class="content">
		<h1 class="main_content_title">전공게시판</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(partlist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 게시글이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == partlist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= partlist.get(i).getBoardNum() %>"><%= partlist.get(i).getBoardSubject() %></a></td>
				<td>전공게시판</td>
				<td><%= partlist.get(i).getBoardViewCount() %></td>
				<td><%= partlist.get(i).getBoardLike() %></td>
				<td><%= partlist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
	<div class="content">
		<h1 class="main_content_title">직업게시판</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(joblist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 게시글이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == joblist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= joblist.get(i).getBoardNum() %>"><%= joblist.get(i).getBoardSubject() %></a></td>
				<td>직업게시판</td>
				<td><%= joblist.get(i).getBoardViewCount() %></td>
				<td><%= joblist.get(i).getBoardLike() %></td>
				<td><%= joblist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
	<div class="content">
		<h1 class="main_content_title">스터디그룹게시판</h1>
		<table style="width:100%;" class="boardList">
			<tr>
				<th>제목</th><th>카테고리</th><th>조회수</th><th>좋아요</th><th>싫어요</th>
			</tr>
			<!-- DB의 Notice 끌어오기 -->
			<%
				if(studylist.size() == 0){
			%>
			<tr class="writeList">
				<td colspan="5"> 작성된 게시글이 없습니다. </td>
			</tr>
			<%					
				}else{
					for(int i = 0; i < 5; i++){
						if(i == studylist.size()) break;
			%>
			<tr class="writeList">
				<td><a href ="WriteView.team3?boardNum=<%= studylist.get(i).getBoardNum() %>"><%= studylist.get(i).getBoardSubject() %></a></td>
				<td>스터디그룹</td>
				<td><%= studylist.get(i).getBoardViewCount() %></td>
				<td><%= studylist.get(i).getBoardLike() %></td>
				<td><%= studylist.get(i).getBoardHate() %></td>
			</tr>
			<%
					}
				}
			%>
			<!-- DB의 Notice 끌어오기 -->
		</table>				
	</div>
</div>
<div class="lo_content_notice_r">
	<div class="content" id="loginform">
		<jsp:include page="login_form.jsp"></jsp:include>
	</div>
</div>