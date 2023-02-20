
function inputValidation(){
    let form = document.forms[0];
    let subject = form.subject.value;
    let writer = form.writer.value.trim();
    let category = form.category.value;
    let title = form.title.value;
    let content = form.content.value.trim();
    form.content = content.replace(/(?:\r\n|\r|\n)/g, '<br>');

    if (subject == "unselect"){
        alert("구분을 선택해 주세요.");
        return false;
    }

    if (writer == ""){
        form.writer.focus();
        alert("작성자는 필수 입력사항입니다.")
        return false;
    }

    if (category == ""){
        alert("분류를 선택해 주세요.")
        return false;
    }

    if (title == ""){
        form.title.focus();
        alert("제목은 필수 입력사항입니다.")
        return false;
    }

    if (content == ""){
        form.content.focus();
        alert("내용은 필수 입력사항입니다.")
        return false;
    }

    form.submit();

// textarea안의 내용을 받아와서 \r\n부분을 모두 <br>tag로 replace 적용해서 DB에 저장을 하면 DB안에 <br>tag가 같이 저장이 된다.

// 반대로 DB에서 가져올때 <br> 을 개행문자(\r\n) 로 변환하면 된다.


}

