
function inputValidation(){
    let form = document.forms[0];
    let subject = form.subject.value;
    let writer = form.writer.value.trim();

    if (subject == "unselect"){
        alert("구분은 필수 선택사항입니다.");
        return false;
    }

    if (writer == ""){
        form.writer.focus();
        alert("작성자는 필수 입력사항입니다.")
        return false;
    }


}

