/**
 * Login에 필요한 아이디 / 비밀번호 점검
 */
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