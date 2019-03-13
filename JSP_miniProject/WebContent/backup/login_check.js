/**
 * 
 */
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