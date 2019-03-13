/**
 * 
 */
window.onload = function() {
	show();
	// 브라우저 크기가 변할 시 동적으로 사이즈를 조절해야 하는경우
	window.addEventListener('resize', show);
}


window.onclick = function(event) {
	if (!event.target.matches('.userdrop_btn')) {
		var userdrop = document.getElementsByClassName("userdrop-content");
		var i;
		for (i = 0; i < userdrop.length; i++) {
			var openuserdrop = userdrop[i];
			if (openuserdrop.classList.contains('show')) {
				openuserdrop.classList.remove('show');
			}
		}
	}
}
function myFunction1() {
	document.getElementById("userdrop-content").classList.toggle("show");
}

function show(){
	var width = window.innerWidth;
	if(width < 600){
		if(document.getElementById("headlogin") != null){
			document.getElementById("headlogin").style.display = "";
		}
		if(document.getElementById("headlogin_text") != null){
			document.getElementById("headlogin_text").style.display = "";
		}
		if(document.getElementById("loginform") != null){
			document.getElementById("loginform").style.display = "none";
		}
		if(document.getElementById('login_medal1') != null){
			document.getElementById("login_medal1").style.display="none";
		}
		if(document.getElementById('lo_nav') != null){
			document.getElementById("lo_nav").style.display="none";
		}
		if(document.getElementById('lo_nav_600') != null){
			document.getElementById("lo_nav_600").style.display="";
		}
	}else{
		if(document.getElementById("headlogin") != null){
			document.getElementById("headlogin").style.display = "none";
		}
		if(document.getElementById("headlogin_text") != null){
			document.getElementById("headlogin_text").style.display = "none";
		}
		if(document.getElementById("loginform") != null){
			document.getElementById("loginform").style.display = "";
		}
		if(document.getElementById('login_medal') != null){
			document.getElementById('login_medal').style.display="none";
		}
		if(document.getElementById('lo_nav') != null){
			document.getElementById("lo_nav").style.display="";
		}
		if(document.getElementById('lo_nav_600') != null){
			document.getElementById("lo_nav_600").style.display="none";
			document.getElementById("lo_nav_link").style.display="none";
		}
	}
}
show();

function myFunction() {
  var link = document.getElementById("lo_nav_link");
  if (link.style.display == "block") {
	  link.style.display = "none";
  } else {
	  link.style.display = "block";
  }
}

// NEW LINE //
function findid(){
	if(document.getElementById("find_name") != null || document.getElementById("find_name").value != "" ||
		document.getElementById("find_email") != null || document.getElementById("find_email").value != "" ||
		document.getElementById("find_phone") != null || document.getElementById("find_phone").value != ""){
		return true;
	}else {
		return false;
	}
	
}
function findpw(){
	if(document.getElementById("find_id") != null || document.getElementById("find_id").value != "" ||
		document.getElementById("find_email") != null || document.getElementById("find_email").value != "" ||
		document.getElementById("find_phone") != null || document.getElementById("find_phone").value != ""){
		return true;
	}else {
		return false;
	}
}
function changepw(){
	if(document.getElementById("find_id") != null || document.getElementById("find_id").value != "" ||
		document.getElementById("find_name") != null || document.getElementById("find_name").value != "" ||
		document.getElementById("new_password") != null || document.getElementById("new_password").value != ""){
		return true;
	}else {
		return false;
	}
	
}

function admindeleteboard(boardnum){
	var answer = confirm("정말 삭제하시겠습니까?");
	if(answer) {
		location.href = "Delete.team3?boardNum="+boardnum+"&boardCategory=adminboard";
	}
}

function usermodify(){
	var answer = confirm("수정하시겠습니까?");
	if(answer) {
		return true;
	}
	return false;
}

function userdelete(memnum){
	var answer = confirm("정말 탈퇴하시겠습니까?");
	if(answer) {
		location.href="memberdelete.team3?membernum="+memnum;
	}
}

function openfindpw(){
	open("Find_Pw.team3", "비밀번호 찾기", "width=300, height=300, scrollbars=0, toolbar=0, menubar=no, location=no, resizable=no, fullscreen=no");
}

function openfindid(){
	open("Find_Id.team3", "아이디 찾기", "width=300, height=300, scrollbars=0, toolbar=0, menubar=no, location=no, resizable=no, fullscreen=no");
}





// ########### Login_check.js ###############
function loginOk(loginCheck, goto, admin) {
	if(loginCheck == "null") {
		alert("로그인이 필요합니다.");
		location.href = "index.team3";
	} else {
		if(goto == "notice") {
			if(admin != "1") {
				alert("관리자 권한이 필요합니다.");
				return false;
			} else {
				location.href = goto + "WriteForm.team3";
			}
		} else {				
			location.href = goto + "WriteForm.team3";
		}
	}
}
// ########### member_check.js ###############
function login(){
	var frm = document.forms["login_form"];
	var id = frm["user_id"].value.trim();
	var pw = frm["user_pw"].value.trim();
	if(id == ""){
		alert('아이디 입력이 잘못되었습니다.');
		frm["user_id"].focus();
		return false;
	}
	if(pw == ""){
		alert('비빌번호 입력이 잘못되었습니다.');
		frm["user_pw"].focus();
		return false;
	}
	return true;
}
//ID 점검 정규식
function id_check(user_id){
	var idreg =/^[A-Za-z0-9_]{6,16}$/;
	if(idreg.test(user_id)){
		return true;
	}else{
		document.getElementById("alert_id").innerHTML = '아이디 길이는 6자리 이상입력하세요.';
		return false;
	}
}
//PW 점검 정규식
function pw_check(user_pw){
	var pwreg = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*\W).{8,20}$/;
	if(pwreg.test(user_pw)){
		return true;
	}else{
		if(user_pw.length < 8){
			document.getElementById("alert_pw").innerHTML = '비밀번호 길이는 8자리 이상입력하세요.';
			return false;
		}else{
			document.getElementById("alert_pw").innerHTML = '비밀번호는 숫자, 문자, 특수문자를 사용하세요.';
			return false;
		}
	}
}
//EMAIL 점검 정규식
function email_check(user_email){
	var emailreg1 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	if(emailreg1.test(user_email)){
		return true;
	}else{
		//document.getElementById("alert_email").innerHTML = '이메일이 잘못 입력되었습니다.';
		return false;
	}
}
//전화번호 점검
function phone_check(user_phone){
	var phonereg = /^[0-9]{8,8}$/;
	if(phonereg.test(user_phone)){
		return true;
	}else{
		return false;
	}
}
function all_check(){
	var id = id_check(document.getElementById("user_id").value);
	var overlap = document.getElementById("overlapCheck").value;
	var pw = pw_check(document.getElementById("user_pw").value);
	var pw_try = null;
	var name = null;4
	var gen = null;
	var phone = phone_check(document.getElementById("user_phone").value);
	var email = email_check(document.getElementById("user_email").value);
	var year = document.getElementById("year").value;
	var month = document.getElementById("month").value;
	var day = document.getElementById("day").value;
	if(document.getElementById("user_pw").value == document.getElementById("user_pw_try").value){
		pw_try = true;
	}else{
		pw_try = false;
	}
	if(document.getElementById("user_name").value == ""){
		name = false;
	}else{
		name = true;
	}
	if(document.getElementById("user_gen").value == 0){
		gen = false;
		document.getElementById("alert_gen").innerHTML = '성별을 선택해 주세요.';
	}else{
		gen = true;
		document.getElementById("alert_gen").innerHTML = '';
	}
	if(id && pw && pw_try && name && gen && phone && email && overlap == "true"){
		return true;
	}else{
		if(overlap == "false") { alert("아이디 중복체크를 진행해주세요."); }
		document.getElementById("user_pw_try").value ="";
		return false;
	}
}
// ########### write_check.js ###############
function writeCheck() {
	var formValue = document.forms["board"];
	
	var subject = formValue["board_Subject"].value;
	var content = formValue["board_Content"].value;
	
	if(subject == "" || subject == null) {
		alert("제목을 입력해주세요.");
		return false;
	}
	if(subject.length > 50) {
		alert("제목은 50자 이하로 입력해주세요.");
		return false;
	}
	if(content == "" || content == null) {
		alert("내용을 입력해주세요.");
		return false;
	}
	
	return true;
}
