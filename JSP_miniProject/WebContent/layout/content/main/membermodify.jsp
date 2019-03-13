<%@page import="com.team3.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	@SuppressWarnings("unchecked")
	List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("userdata");
	if(list.size() == 0 || list == null) {
		out.println("<script>location.href='index.team3';</script>");
		return;
	}
%>
<form id="user_modify" action="membermodify.team3?memnum=<%= list.get(0).getMemNum() %>" method="post" onsubmit="return usermodify()">
	<div class="modify_main">
		<span>정보수정</span>
		<hr style="border: 1px solid; ">
		<table style="width: 100%;">
			<tr>
				<td>아이디</td><td><input type="text" name="id" value="<%= list.get(0).getMemId() %>" disabled="disabled"></td>
			</tr>
			<tr>
				<td>이름</td><td><input type="text" name="name" value="<%= list.get(0).getMemName() %>" disabled="disabled"></td>
			</tr>
			<tr>
				<td>비밀번호</td><td><input type="text" name="password" value="<%= list.get(0).getMemPw() %>"></td>
			</tr>
			<tr>
				<td>이메일</td><td><input type="text" name="email" value="<%= list.get(0).getMemEmail() %>"></td>
			</tr>
			<tr>
				<td>학교</td><td><input type="text" name="school" value="<%= list.get(0).getMemSchool() %>"></td>
			</tr>
			<tr>
				<td>전공</td><td><input type="text" name="part" value="<%= list.get(0).getMemPart() %>"></td>
			</tr>
			<tr>
				<td>직업</td><td><input type="text" name="job" value="<%= list.get(0).getMemJob() %>"></td>
			</tr>
			<tr>
				<td>생년월일</td><td><input type="text" name="birth" value="<%= list.get(0).getMemBirth() %>" disabled="disabled"></td>
			</tr>
			<tr>
				<td>성별</td>
				<%
				if(list.get(0).getMemGender() == 1){
				%>
				<td><input type="text" name="gender" value="남자" disabled="disabled"></td>
				<%
				}else{
				%>
				<td><input type="text" name="gender" value="여자" disabled="disabled"></td>
				<%
				}
				%>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" value="<%= list.get(0).getMemPhone() %>"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="수정">&nbsp;&nbsp;&nbsp;<input type="button" value="취소" onclick="history.back()"></td>
			</tr>
		</table>
	</div>
</form>
