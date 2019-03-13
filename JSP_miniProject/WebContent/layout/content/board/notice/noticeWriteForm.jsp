<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginCheck = (String)session.getAttribute("ok");
	if(loginCheck == null) { 
		out.println("<script>alert('로그인이 필요합니다.');location.href = 'index.team3'</script>");	
	}
%>
<div class="allBox">
	<h1 class="head">공지사항 작성</h1>
	<form action="WriteOk.team3" method="post" name="board" onsubmit="return writeCheck();" enctype="Multipart/form-data">
		<hr class="sethr">
		<table style="border-collapse: collapse;">
			<tr>
				<th>제목</th><td><input class="subject" type="text" id="board_Subject" name="board_Subject" placeholder="제목을 입력해주세요.(50자 이하)" autofocus/></td>
			</tr>
			<tr>
				<th></th><td><button class="writeFunc1" type="button">가</button> <button type="button">IMG</button> <button class="writeFunc2" type="button" id="file1">파일첨부</button> <span id="files" class="files"><input type="file" id="fupload" name="fupload"/></span></td>
			</tr>
			<tr>
				<th>내용</th><td><textarea class="board_textarea" id="board_Content" name="board_Content" placeholder="내용을 입력해주세요."></textarea></td>
			</tr>
		</table>
		<hr>
		<div class="boardFunc3">
			<input type="hidden" id="board_Category" name="board_Category" value="notice"/>
			<input type="submit" value="등록하기"/> <input type="button" value="취소" onclick="history.back();" />
		</div>
	</form>
</div>
<script>
var file = document.querySelector("#file1");
var upload = document.querySelector(".files");

function fileUpload() {
	
	upload.classList.toggle("files");
/* 	var pHeight = 300;
	var pWidth = 500;
	var top = (window.screen.height - pHeight)/2;
	var left = (window.screen.width - pWidth)/2;
	var option = "width=" + pWidth + ", height= " + pHeight + ", left=" + left + ", top=" + top + ", location=no, toolbar=no, status=no, menubar=no, resizable=no, scrollbars=no";
	window.open("http://localhost:8081/JSP_miniProject/fileUpload.team3", "", option); */
}

file.addEventListener("click", fileUpload);
</script>
