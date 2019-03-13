<%@page import="com.team3.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	@SuppressWarnings("unchecked")
	List<MemberDTO> list = (List<MemberDTO>)request.getAttribute("member");
	int writecount = (Integer)request.getAttribute("boardcount");
	int commentcount = (Integer)request.getAttribute("commentcount");
	if(list.size() == 0 || list == null) {
		out.println("<script>location.href='index.team3';</script>");
		return;
	}
%>
<div class="modify_main">
	<span>회원탈퇴</span>
	<hr style="border: 1px solid; ">
	<table style="width: 100%;">
		<tr>
			<td>아이디</td><td><input type="text" name="id" value="<%= list.get(0).getMemId() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>이름</td><td><input type="text" name="name" value="<%= list.get(0).getMemName() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="text" name="password" value="<%= list.get(0).getMemPw() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>이메일</td><td><input type="text" name="email" value="<%= list.get(0).getMemEmail() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>학교</td><td><input type="text" name="school" value="<%= list.get(0).getMemSchool() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>전공</td><td><input type="text" name="part" value="<%= list.get(0).getMemPart() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>직업</td><td><input type="text" name="job" value="<%= list.get(0).getMemJob() %>" disabled="disabled"></td>
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
			<td><input type="text" name="phone" value="<%= list.get(0).getMemPhone() %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>작성한 글 수</td>
			<td><input type="text" name="writecnt" value="<%= writecount %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td>작성한 댓글 수</td>
			<td><input type="text" name="commentcnt" value="<%= commentcount %>" disabled="disabled"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="탈퇴" onclick="userdelete(<%= list.get(0).getMemNum() %>)">&nbsp;&nbsp;&nbsp;<input type="button" value="취소" onclick="location.href='index.team3'"></td>
		</tr>
	</table>
</div>