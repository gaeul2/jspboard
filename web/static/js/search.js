//배열로

function searchValidate(){
    let form = document.forms[0];
    let title = form.title_search.value.trim();
    let writer = form.writer_search.value.trim();
    let start_date = form.start_date.value;
    let end_date = form.end_date.value;
    let now = new Date().toISOString().split("T")[0].replace(".","-");
    console.log("바뀌기 전 " + start_date);
    console.log("바뀌기 전 " + end_date);
    console.log(now);

    if( title == "" & writer == "" & start_date == "" & end_date == "") {
        alert("검색을 위해 검색값을 입력하거나 선택하세요!" )
    } else {
        if (title != "") {
            title = "'%" + title + "%'";
        }
        if(writer != ""){
            writer = "'%" + writer + "%'";
        }
        if ( start_date != "" & end_date != ""){
            if (start_date > end_date){
                alert("검색할 날짜의 범위를 올바르게 지정해주세요")
            }
        } else if (start_date != "" & end_date == ""){
            end_date = now;
            if (start_date > end_date){
                alert("검색 시작 날짜를 올바르게 지정해주세요");
            }
        }

    }

    console.log("바뀌나?start_date"+start_date);
    console.log("바뀌나?end_date"+end_date);

}