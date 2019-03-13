<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int count = (Integer)request.getAttribute("writeSuccess");
	String boardCategory = (String)request.getAttribute("boardCategory");
%>	
<% 	if(count == 0) { %>
	<script>
		alert("작성에 실패하였습니다.");
	</script>
<% 	} else if(count == 1){ %>
	<script>
		alert("작성되었습니다.");
	</script>
<%  } else { %>
	<script>
		alert("파일크기제한");
		history.back();
	</script>
<% 	} %>
<script>
location.href = "<%= boardCategory %>board.team3";
</script>