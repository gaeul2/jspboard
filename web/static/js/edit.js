
function checkSize(inputFile){
    console.log(inputFile.files[0].size)
    if(inputFile.files[0].size > (1024 * 1000 * 5)){
        alert("파일사이즈는 5MB를 초과할 수 없습니다.");
        inputFile.value = null;
    }
}
function inputValidation(){
    let form = document.forms[0];
    let subject = form.subject.value;
    let writer = form.writer.value.trim();
    let category = form.category.value;
    let title = form.title.value;
    let content = form.content.value.trim();
    form.content = content.replace(/(?:\r\n|\r|\n)/g, '<br>');

    alert(check.value);

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
}

function deleteFile(){
    let form = document.forms[0];
    let downloadBtn = document.getElementById("downloadBtn");
    let deleteBtn = document.getElementById("deleteBtn")
    let cancelBtn = document.getElementById("cancelBtn")

    downloadBtn.style.display = 'none';
    deleteBtn.style.display = 'none';
    cancelBtn.style.display = 'initial';

    let newTag = document.createElement("input")
    newTag.name="check";
    newTag.value="1";
    newTag.id="checkInput"
    newTag.style.display='none'
    form.append(newTag);
}

function cancelDelete(){
    let form = document.forms[0];
    let downloadBtn = document.getElementById("downloadBtn");
    let deleteBtn = document.getElementById("deleteBtn");
    let cancelBtn = document.getElementById("cancelBtn");
    let check = document.getElementById("checkInput");

    check.remove();
    downloadBtn.style.display = 'inline-block';
    deleteBtn.style.display = 'initial';
    cancelBtn.style.display = 'none';
}