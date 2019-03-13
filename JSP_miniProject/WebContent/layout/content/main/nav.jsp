<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="lo_nav">
	<ul class="lo_top_nav">
		<li onclick="location.href='/JSP_miniProject'" style="cursor: pointer;">메인페이지</li>
		<li onclick="location.href='#'" style="cursor: pointer;">소개</li>
		<li onclick="location.href='noticeboard.team3'" style="cursor: pointer;">공지사항</li>
		<li onclick="location.href='freeboard.team3'" style="cursor: pointer;">자유게시판</li>
		<li onclick="location.href='studyboard.team3'" style="cursor: pointer;">스터디모집게시판</li>
		<li onclick="location.href='partboard.team3'" style="cursor: pointer;">전공별게시판</li>
		<li onclick="location.href='jobboard.team3'" style="cursor: pointer;">직종별게시판</li>
	</ul>
</div>
<div class="topnav" id="lo_nav_600">
	<a href="/JSP_miniProject" id="lo_nav_active">메인페이지</a>
		<div id="lo_nav_link">
			<a href="#">소개</a>
			<a href="noticeboard.team3">공지사항</a>
			<a href="freeboard.team3">자유게시판</a>
			<a href="studyboard.team3">스터디모집게시판</a>
			<a href="partboard.team3">전공별게시판</a>
			<a href="jobboard.team3">직종별게시판</a>
		</div>
	<a href="javascript:void(0);" class="icon" onclick="myFunction()">
		<i class="fa fa-bars"></i>
	</a>
</div>