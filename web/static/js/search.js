//배열로

function searchValidate(){
    let form = document.forms[0];
    let title = form.title_search.value.trim();
    let writer = form.writer_search.value.trim();
    let start_date = form.start_date.value;
    let end_date = form.end_date.value;
    let now = new Date().toISOString().split("T")[0].replace(".","-");
    let check = 0;

    if( title == "" & writer == "" & start_date == "" & end_date == "") {
        alert("검색을 위해 검색값을 입력하거나 선택하세요!" )
    } else {
        if (title != "") {
            form.title_search.value = title;
            check = 1;
        }
        if(writer != ""){
            form.writer_search.value = writer;
            check = 1;
        }
        if ( start_date != "" & end_date != ""){
            start_date += " 00:00:00"
            end_date += " 23:59:59";
            if (start_date > end_date){
                alert("검색할 날짜의 범위를 올바르게 지정해주세요")
                check = 0;
            } else {
                check = 1;
            }
        } else if (start_date != "" & end_date == ""){
            start_date += " 00:00:00"
            end_date = now + " 23:59:59";
            if (start_date > end_date){
                alert("검색 시작 날짜를 올바르게 지정해주세요");
                check = 0;
                start_date = end_date = "";
            } else {
                check = 1;
            }
        } else if(start_date == "" & end_date != ""){
            start_date = "1999-01-01 00:00:00";
            end_date += " 23:59:59";
            if (start_date > end_date){
                alert("검색 종료 날짜를 올바르게 지정해주세요.");
                check = 0;
                start_date = end_date = "";
            } else {
                check = 1;
            }
        }
    }

    if(check == 1){
        form.submit();
    }

}