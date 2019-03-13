/**
 * 
 */
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