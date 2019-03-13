<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/source.js"></script>
<link rel="stylesheet" type="text/css" href="css/join_form.css">
<link rel="stylesheet" type="text/css" href="css/join_form.css" media="(max-width:600px;)">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<script type="text/javascript">
function date_birth(){
	var nowDate = new Date();
	var nowYear = nowDate.getFullYear();
	var nowMonth = eval(nowDate.getMonth())+1;
	var nowDay = eval(nowDate.getDate());
	var startYear = nowYear - 70;

	for( var i=0; i<=70; i++ ) {
		document.getElementById("year").options[i] = new Option(startYear+i, startYear+i);
	}

	for (var i=0; i<12; i++) {
		document.getElementById("month").options[i] = new Option(i+1, i+1);
	}
   
	document.getElementById("year").value = nowYear;
	document.getElementById("month").value = nowMonth;
	setDay();
    document.getElementById("day").value = nowDay;
}

function setDay() {
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;    
	var dateDay = document.getElementById("day");
	var arrayMonth = [31,28,31,30,31,30,31,31,30,31,30,31];

	if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
		arrayMonth[1] = 29;
	}

	for(var i=dateDay.length; i > 0; i--) {
		dateDay.remove(dateDay.selectedIndex);
	}
    
	for (var i=1; i<=arrayMonth[month-1]; i++) {
 		dateDay.options[i-1] = new Option(i, i);
	}

	if( day != null || day != "" ) {
		if( day > arrayMonth[month-1] ) {
			dateDay.options.selectedIndex = arrayMonth[month-1]-1;
		} else {
			dateDay.options.selectedIndex = day-1;
		}
	}
}
</script>
</head>
<body onload="date_birth()">
<form action="joinOk.team3" method="post" onsubmit="return all_check();">
	<div class="j_main" align="center">
		<div class="j_form">
			<div class="logo_img">
				<div class="image"></div>
			</div>
			<div class="label">
				<p align="left">아이디 <button type="button" id="idOverlap">중복체크</button></p>
				<input type="hidden" id="overlapCheck" value="false"/>
			</div>
			<div class="in_text">
				<input type="text" name="user_id" id="user_id" class="input_text" autofocus/>
				<div id="check_id" class="alert_cnt" style="text-align: left;"><label id="alert_id" class="alert"></label></div>
			</div>
			<div class="label">
				<p align="left">비밀번호</p>
			</div>
			<div class="in_text">
				<input type="password" name="user_pw" id="user_pw" class="input_text"/>
				<div id="check_pw" class="alert_cnt" style="text-align: left;"><label id="alert_pw" class="alert"></label></div>
			</div>
			<div class="label">
				<p align="left">비밀번호 재확인</p>
			</div>
			<div class="in_text">
				<input type="password" name="user_pw_try" id="user_pw_try" class="input_text"/>
				<div id="check_pw_try" class="alert_cnt" style="text-align: left;"><label id="alert_pw_try" class="alert"></label></div>
			</div>
			<div class="label">
				<p align="left">이메일</p>
			</div>
			<div class="in_text">
				<input type="text" name="user_email" id="user_email" class="input_text"/>
				<div id="check_email" class="alert_cnt" style="text-align: left;"><label id="alert_email" class="alert"></label></div>
			</div>
			<div class="label">
				<p align="left">이름</p>
			</div>
			<div class="in_text">
				<input type="text" name="user_name" id="user_name" class="input_text"/>
			</div>
			<div class="label">
				<p align="left">학교</p>
			</div>
			<div class="in_text">
				<input type="text" name="user_school" id="user_school" class="input_text"/>
			</div>
			<div class="label">
				<p align="left">전공</p>
			</div>
			<div class="in_text">
				<input type="text" name="user_part" id="user_part" class="input_text"/>
			</div>
			<div class="label">
				<p align="left">직장</p>
			</div>
			<div class="in_text">
				<input type="text" name="user_job" id="user_job" class="input_text"/>
			</div>
			<div class="label">
				<p align="left">생년월일</p>
			</div>
			<div class="in_text">
				<select name="year" id="year" class="date" onchange="setDay()">
				</select>
				<select name="month" id="month" class="date" onchange="setDay()">
				</select>
				<select name="day" id="day" class="date">
				</select>
				<!-- 
				 <input type="text" name="user_birth" id="user_birth" class="input_text"/>
				 -->
			</div>
			<div class="label">
				<p align="left">성별</p>
			</div>
			<div class="in_text">
				<select name="user_gen" id="user_gen" class="date" style="width: 97%;">
					<option value="0">성별</option>
					<option value="1">남자</option>
					<option value="2">여자</option>
				</select>
				<div id="check_gen" class="alert_cnt" style="text-align: left;"><label id="alert_gen" class="alert"></label></div>
			</div>
			<div class="label">
				<p align="left">전화번호</p>
			</div>
			<div class="in_text">
				<select name="phone1" id="phone1" class="date" style="width: 25%;">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
				</select>
				<input type="text" name="user_phone" id="user_phone" class="input_text" style="width: 60%" maxlength="8"/>
				<div id="check_phone" class="alert_cnt" style="text-align: left;"><label id="alert_phone" class="alert"></label></div>
			</div>
			<div class="in_text">
				<button type="submit" name="agree" id="agree" style="width: 97%; height: 45px; font-size:12pt; margin-top:8px; ">회원가입</button>
				<button type="button" name="cancel" id="cancel" style="width: 97%; height: 45px; font-size:12pt; margin-top:8px;" onclick="location.href='index.team3'">취소</button>
			</div>
		</div>
	</div>
</form>
</body>

<script type="text/javascript">
$(function(){
	$('#user_email').css("color","#e7e7e7");
	$('#user_email').val("example@gmail.com");
	// Sample email View
	$('#user_email').focus(function(){
		if($('#user_email').val() == "example@gmail.com"){
			$('#user_email').css("color","#000000");
			$('#user_email').val("");
		}
	});
	$('#user_email').blur(function(){ 
	if($('#user_email').val() == ""){
	$('#user_email').css("color","#e7e7e7");
	$('#user_email').val("example@gmail.com");
	}
	});
	// id 정규식_중복확인
	$('#user_id').blur(function(){ 
	if($('#user_id').val() != ""){
	$('#check_id').show();
	if(id_check($('#user_id').val())){
	//중복 검사 필요
/* 	$.ajax({ // id 중복확인(포커스 잃을 시)
	url: "idOverlap.team3?memID="+$("#user_id").val(),
	type: "GET",
	 	datatype: "text",
	cache: false,
	 	success: function(data, status) {
	 	if(status == "success") {
	if(data == "false") {
	alert("사용가능한 아이디입니다.");
	document.getElementById("overlapCheck").value = "true";
	} else {
	alert("이미 등록되어 있는 아이디입니다.");
	document.getElementById("overlapCheck").value = "false";
	}
	 	}
	 	}
	}); */
	document.getElementById("overlapCheck").value = "false";
	$('#check_id').hide();
	}
	}else{
	document.getElementById("overlapCheck").value = "false";
	$('#check_id').hide();
	}
	});
	//id 중복확인(버튼)
	 	$("#idOverlap").click(function() {
	if($("#user_id").val() == null || $("#user_id").val() == "") {
	alert("아이디를 입력해주세요.");
	return false;
	}
	if(!(id_check($('#user_id').val()))) {
	return false;	
	}
	 	$.ajax({
	url: "idOverlap.team3?memID="+$("#user_id").val(),
	type: "GET",
	 	datatype: "text",
	cache: false,
	 	success: function(data, status) {
	 	if(status == "success") {
	if(data == "false") {
	alert("사용가능한 아이디입니다.");
	document.getElementById("overlapCheck").value = "true";
	} else {
	alert("이미 등록되어 있는 아이디입니다.");
	document.getElementById("overlapCheck").value = "false";
	}
	 	}
	 	}
	});
	});
	// pw 정규식 확인
	$('#user_pw').blur(function(){
		if($('#user_pw').val() != ""){
			$('#check_pw').show();
			if(pw_check($('#user_pw').val())){
				$('#check_pw').hide();
			}
		}else{
			$('#check_pw').hide();
		}
	});
	// pw 재확인
	$('#user_pw_try').blur(function(){
		if($('#user_pw_try').val() != ""){
			$('#check_pw_try').show();
			if($('#user_pw').val() == $('#user_pw_try').val()){
				$('#check_pw_try').hide();
			}else{
				$('#alert_pw_try').html("비밀번호가 올바르지 않습니다.");
			}
		}else{
			$('#check_pw_try').hide();
		}
	});
	// email 정규식 확인
	$('#user_email').blur(function(){
		if($('#user_email').val() != ""){
			$('#check_email').show();
			if(email_check($('#user_email').val())){
				$('#check_email').hide();
			}else{
				$('#alert_email').html("이메일이 올바르지 않습니다.");
			}
		}else{
			$('#check_email').hide();
		}
	});
	//전화번호 검사
	$('#user_phone').blur(function(){
		if($('#user_phone').val() != ""){
			$('#check_phone').show();
			if(phone_check($('#user_phone').val())){
				$('#check_phone').hide();
			}else{
				$('#alert_phone').html("전화번호는 \"-\" 없이 숫자로 입력해주세요.");
			}
		}else{
			$('#check_phone').hide();
		}
	});
});
</script>
</html>