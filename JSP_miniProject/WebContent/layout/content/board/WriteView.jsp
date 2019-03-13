<%@ page import="com.team3.dto.BoardCommentDTO"%>
<%@ page import="com.team3.dto.BoardDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String loginCheck = (String)session.getAttribute("ok");
	if(loginCheck == null) { %>
		<script>
			alert("로그인이 필요합니다.");
			location.href = "index.team3";
		</script>
<% 
	return;
	} 
%>
<% @SuppressWarnings("unchecked") List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("boardData"); %>
<% @SuppressWarnings("unchecked") List<BoardCommentDTO> list2 = (List<BoardCommentDTO>)request.getAttribute("CommentData"); %>
<% if(list == null) { %>
		<script>
			alert("존재하지 않는 게시물입니다.");
			location.href = "index.team3";
		</script>
<% } %>
<script>
function CommentModify(data) {
	var saveData = document.getElementById(data+"2").value;
	var changeData = saveData.replace(/&nbsp/g, ' ').replace(/<br>/g, '\n');
	document.getElementById(data).innerHTML = "<textarea id='comment_ModifyContent'>" + changeData + "</textarea><br><br><button onclick=CommentModifyOk(" + data + ");>완료</button> <button onclick=returnValue(" + data + ")>취소</button>";
}
function returnValue(data) {
	$(document).ready(function() {
		var returnContent = $("#"+data+"2").val();
		document.getElementById(data).innerHTML = 
		"<div>" + returnContent + "</div><button onclick=CommentModify('" + data + "');>수정</button> <button onclick=CommentDelete(" + data + ")>삭제</button>";
	});
}
function CommentModifyOk(data) {
	$(document).ready(function() {
		$.ajax({
			url: "UpdateComment.team3",
			type: "POST",
			cache: false,
			datatype: "json",
			data: {
				commentNum : data,
				commentContent : $("#comment_ModifyContent").val()
			},
			success: function(data2, status) {
				if(status == "success") {
					var commentObject = data2.CommentData;
					var i=0;
					
					for(i=0; i<commentObject.length; i++) {
						var commentContent = commentObject[i].comment_content.replace(/ /g, "&nbsp").replace(/\n/g, "<br>");
						document.getElementById(data).innerHTML = "<div>" + commentContent + "</div>" + "</div><button onclick=CommentModify(" + data + ");>수정</button> <button onclick=CommentDelete(" + data + ")>삭제</button>";
					}
				}
			}
			
		});
	});
}
function CommentDelete(data) {
	$(document).ready(function() {
		var DeleteConfirm = confirm("정말 삭제하시겠습니까?");
		if(DeleteConfirm) {
			$.ajax({
				url: "DeleteComment.team3",
				type: "POST",
				data: {
					commentNum : data,
					boardNum : <%= list.get(0).getBoardNum() %>
				},
				cache: false,
				success: function(data2, status) {
					if(status == "success") {
						var commentData = data2.BoardCommentData;
						var i=0;
						
						var div = "";
						if(commentData.length == 0) {
							div += "<h3 style='text-align: center;'>작성된 댓글이 없습니다.</h3>";
						} else {
							for(i = 0; i<commentData.length; i++) {
								div += "<div class='view_CommentList'>";
								div += "<div>" + commentData[i].comment_name + " (" + commentData[i].comment_regdate + ")</div>";
								div += "<div>내용</div>";
								div += "<div id = '" + commentData[i].comment_num + "'>";
								div += "<div>" + commentData[i].comment_content.replace(/ /g, "&nbsp").replace(/\n/g, "<br>") + "</div>";
								if(<%= (Integer)session.getAttribute("number") %> == commentData[i].mem_num) {						
									div += "<button onclick='CommentModify(" + commentData[i].comment_num + ");'>수정</button> <button onclick=CommentDelete(" + commentData[i].comment_num + ")>삭제</button>";
								}
								div += "</div>";
								div += "<input type='hidden' id='" + commentData[i].comment_num + "2' value='" + commentData[i].comment_content.replace(/ /g, '&nbsp').replace(/\n/g, '<br>') + "'>";
								div += "</div>";
							}
						}
							document.getElementById("view_CommentListBox").innerHTML = div;
					}
				}
			});
		}
	});
}
$(document).ready(function() {
	$("#Like").click(function() {
			$.ajax({
				url: "BoardRecommend.team3",
				type: "POST",
				data: {
					boardNum : <%= list.get(0).getBoardNum() %>,
					memNum : <%= (Integer)session.getAttribute("number") %>,
					Type: "Like"
				},
				cache: false,
				success: function(data, status) {
					if(status == "success") {
						if(data == "Like") {
							alert("이미 싫어요를 누른 게시글입니다.");
						} else {
							var RecommendData = data.BoardRecommendData;
							var i=0;
							
							document.getElementById("LikeValue").innerHTML = RecommendData[0].Like;
							document.getElementById("HateValue").innerHTML = RecommendData[0].Hate;
						}
					}
				}
			});
	});
	$("#Hate").click(function() {
		$.ajax({
			url: "BoardRecommend.team3",
			type: "POST",
			data: {
				boardNum : <%= list.get(0).getBoardNum() %>,
				memNum : <%= (Integer)session.getAttribute("number") %>,
				Type: "Hate"
			},
			cache: false,
			success: function(data, status) {
				if(status == "success") {
					if(data == "Hate") {
						alert("이미 좋아요를 누른 게시글입니다.");
					} else {
						var RecommendData = data.BoardRecommendData;
						var i=0;
						
						document.getElementById("LikeValue").innerHTML = RecommendData[0].Like;
						document.getElementById("HateValue").innerHTML = RecommendData[0].Hate;
					}
				}
			}
		});
	});
	$("#writeComment").click(function() {
		$.ajax({
			url: "WriteComment.team3",
			type: "POST",
			cache: false,
			data: {
				boardNum : $("#boardNum").val(),
				commentContent : $("#comment_Content").val()
			},
			success: function(data, status) {
				if(status == "success") {
					var commentData = data.BoardCommentData;
					var i=0;
					
					var div = "";
					for(i = 0; i<commentData.length; i++) {
						div += "<div class='view_CommentList'>";
						div += "<div>" + commentData[i].comment_name + " (" + commentData[i].comment_regdate + ")</div>";
						div += "<div>내용</div>";
						div += "<div id = '" + commentData[i].comment_num + "'>";
						div += "<div>" + commentData[i].comment_content.replace(/ /g, "&nbsp").replace(/\n/g, "<br>") + "</div>";
						if(<%= (Integer)session.getAttribute("number") %> == commentData[i].mem_num) {						
							div += "<button onclick='CommentModify(" + commentData[i].comment_num + ");'>수정</button> <button  onclick=CommentDelete(" + commentData[i].comment_num + ")>삭제</button>";
						}
						div += "</div>";
						div += "<input type='hidden' id='" + commentData[i].comment_num + "2' value='" + commentData[i].comment_content.replace(/ /g, '&nbsp').replace(/\n/g, '<br>') + "'>";
						div += "</div>";
					}
						document.getElementById("view_CommentListBox").innerHTML = div;
						document.getElementById("comment_Content").innerHTML = "";
				}
			}
		});
	});
$("#Delete").click(function() {
		var answer = confirm("정말 삭제하시겠습니까?");
		if(answer) {
			location.href = "Delete.team3?boardNum=<%= list.get(0).getBoardNum() %>&boardCategory=<%= list.get(0).getBoardCategory() %>";
		}
});
});
</script>

	<h1 class="head" style="display: inline-block;"><%= list.get(0).getBoardCategory().toUpperCase() %> - 상세보기</h1> <div style="display: inline-block; width: 100%;" align="right"><input type="button" value="목록" onclick="history.back()"></div>
	<hr class="sethr1">
		<div class="view_Header"><%= list.get(0).getBoardSubject() %></div><div class="view_Header_ViewCount">조회수 : <%= list.get(0).getBoardViewCount() %></div><div class="view_Header_Date"><%= list.get(0).getBoardRegDate() %></div><div class="view_Header_Writer">작성자 : <%= list.get(0).getBoardName() %></div>
	<hr class="sethr2">
	<div style="height: 300px; padding-left: 7px; overflow: auto;">
		<%= list.get(0).getBoardContent().replaceAll(" ", "&nbsp").replaceAll("\n", "<br>") %>
	</div>
	<% if((Integer)session.getAttribute("number") == list.get(0).getMemNum()) { %>
	<div class="view_BoardBtn"><button onclick="location.href = 'Update.team3?boardNum=<%= list.get(0).getBoardNum() %>'">수정</button> <button id="Delete">삭제</button></div>
	<% } %>
	<div class="view_LikeAHate">
		<button id="Like" type="button">좋아요 <span id="LikeValue"><%= list.get(0).getBoardLike() %></span></button> <button id="Hate" type="button">싫어요 <span id="HateValue"><%= list.get(0).getBoardHate() %></span></button>
	</div>
	<div class="view_Comment">
			<div class="view_CommentTitle">댓글</div>
			<div class="view_CommentInputBox">
				<textarea id="comment_Content" name="comment_Content"></textarea>
				<input type="hidden" id="boardNum" name="boardNum" value="<%= list.get(0).getBoardNum() %>" />
			</div>
			<div class="view_CommentButton">
				<button id="writeComment">작성</button>
			</div>
	</div>
	<hr>
	<div id="view_CommentListBox" class="view_CommentListBox">
			<% if(list2.size() != 0)  {%>
				<% for(int i=0; i<list2.size(); i++) { %>
					<div class="view_CommentList">
						<div><%= list2.get(i).getCommentName() %> (<%= list2.get(i).getCommentRegDate() %>)</div>
						<div>내용</div>
						<div id="<%= list2.get(i).getCommentNum() %>">
						<div><%= list2.get(i).getCommentContent().replaceAll(" ", "&nbsp").replaceAll("\n", "<br>") %></div>
						<% if((Integer)session.getAttribute("number") == list2.get(i).getMemNum()) { %>
						<button onclick="CommentModify(<%= list2.get(i).getCommentNum() %>);">수정</button> <button onclick="CommentDelete(<%= list2.get(i).getCommentNum() %>);">삭제</button>
						<% } %>
						</div>
						<input type="hidden" id="<%= list2.get(i).getCommentNum() %>2" value="<%= list2.get(i).getCommentContent().replaceAll(" ", "&nbsp").replaceAll("\n", "<br>") %>"/>
					</div>
				<% } %>
			<% } else { %>
					<h3 style="text-align: center;">작성된 댓글이 없습니다.</h3>
			<% } %>
	</div>
	<hr>
