//배열로

function searchValidate(){
    let form = document.forms[0];
    let titlev = document.getElementsByName("title_search");

    let title = form.title_search.value;
    console.log("바뀌기 전 " + title)
    title = "메롱";

    console.log("바뀌나?"+title);

}