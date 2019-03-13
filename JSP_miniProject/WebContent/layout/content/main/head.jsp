<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="lo_head">
<%
	String sessionname, sessionvalue;
	Enumeration<String> enumerationname =  session.getAttributeNames(); // Enumeration<String> Return
	String name, id, pw;
	
	int i = 0;
	
	while(enumerationname.hasMoreElements()){ // boolean return 다음 값이 있는지 물어보는 메소드(T | F)
		
		sessionname = enumerationname.nextElement().toString(); // 값을 가져오는 메소드
		sessionvalue = session.getAttribute(sessionname).toString(); // Value값을 가져오는 방법
		
		i++;
	} // While END
	
	if(i == 0){ // Session 이 없을경우
%>
	<div align="right" id="headlogin">
		<button onclick="document.getElementById('login_medal').style.display='block'" style="width:auto;" class="page_login_bt">로그인</button>
		<div id="login_medal" class="modal">
			<form class="modal-content animate" action="login.team3" method="post">
				<div class="login_container">
					<label for="uname"><b>ID</b></label>
					<input type="text" placeholder="Enter Username" name="id" class="login_text" required> <!-- Param : id -->
					<label for="psw"><b>Password</b></label>
					<input type="password" placeholder="Enter Password" name="password" class="login_text" required> <!-- Param : password -->
					<button type="submit" class="login_bt">로그인</button>
				</div>
				<div class="login_container" style="background-color:#f1f1f1">
					<button type="button" onclick="location.href='join.jsp'" class="joinbtn">회원가입</button><!-- 회원가입 폼으로 이동필요 -->
					<button type="button" onclick="document.getElementById('login_medal').style.display='none'" class="cancelbtn">취소</button>
					<span class="psw"><a href="" onclick="openfindid()">아이디</a>/<a href="" onclick="openfindpw()">비밀번호</a> 찾기</span>
				</div>
			</form>
		</div>
	</div>
<%
	} else {
		int usernum = (Integer)session.getAttribute("number");
		name = session.getAttribute("name").toString();
%>
<!-- 	<div class="userdrop">
		<div style="width:25px; height: 25px; background-color: black; position: relative;" class="userdrop_btn" onclick="myFunction1()">
		</div>
		<div id="userdrop-content" class="userdrop-content">
			<a href="#UserModify">UserModify</a>
		</div>
	</div> -->
	
	<div id="headlogin_text">
		<span style="font-size:8pt;color:rgb(150,150,150);"> <%= name %>님 반갑습니다.</span>
		<input type="button" value="로그아웃" onclick="location.href='logout.team3'"> 
		<input type="button" value="정보수정" onclick="location.href='memmodify.team3?memnum=<%= usernum %>'"> 
		<input type="button" value="탈퇴" onclick="location.href='memdelete.team3?memnum=<%= usernum %>'"> 
	</div>
<%
	}
%>
</div>