//배열로

function searchValidate(){
    let form = document.forms[0];
    let title = form.title_search.value.trim();
    let writer = form.writer_search.value.trim();
    let start_date = form.start_date.value;
    let end_date = form.end_date.value;
    let now = new Date().toISOString().split("T")[0].replace(".","-");
    let date_check = 0;
    console.log("바뀌기 전 " + start_date);
    console.log("바뀌기 전 " + end_date);
    console.log(now);

    if( title == "" & writer == "" & start_date == "" & end_date == "") {
        alert("검색을 위해 검색값을 입력하거나 선택하세요!" )
    } else {
        if (title != "") {
            title = "'%" + title + "%'";
            form.title_search.value = title;
        }
        if(writer != ""){
            writer = "'%" + writer + "%'";
        }
        if ( start_date != "" & end_date != ""){
            if (start_date > end_date){
                alert("검색할 날짜의 범위를 올바르게 지정해주세요")
            }
        } else if (start_date != "" & end_date == ""){
            start_date += " 00:00:00"
            end_date = now + " 23:59:99";
            if (start_date > end_date){
                alert("검색 시작 날짜를 올바르게 지정해주세요");
                date_check = 0;
                start_date = end_date = "";
            }
        } else if(start_date == "" & end_date != ""){
            start_date = "1999-01-01 00:00:00";
            end_date += " 23:59:99";
            if (end_date > now){
                alert("검색 종료 날짜를 오늘 이후로 지정할 수 없습니다.");
                date_check = 0;
                start_date = end_date = "";
            }
        } else {
            if (start_date > end_date){
                alert("검색 시작 날짜를 올바르게 지정해주세요");
                date_check = 0;
                start_date = end_date = "";
            }
        }

    }

    alert(form.title_search.value);
    if(date_check == 1){
        form.submit();
    }

    console.log("바뀌나?title"+title);
    console.log("바뀌나?writer"+writer);
    console.log("바뀌나?start_date"+start_date);
    console.log("바뀌나?end_date"+end_date);

}