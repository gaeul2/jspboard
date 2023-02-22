package Util;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
        String pagingStr = "";
        
        //전체페이지 수 계산
        int totalPages = (int) (Math.ceil((double) totalCount / pageSize));
        
        //이전 페이지 가기 출력
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
//        int pageTemp = pageNum;
        //현재페이지가 1이 아닐때만 << 과 < 를 출력
        if (pageTemp != 1){
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'> << </a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'> < </a>";
        }
        
        //각 페이지 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages){
            //현재 페이지는 링크를 걸지 않음
            if (pageTemp == pageNum){
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }
        
        //다음페이지 가기 출력
        if (pageTemp <= totalPages){
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp) + "'> > </a>";
            pagingStr += "&nbsp";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'> >> </a>";
        }
        return pagingStr;
    }
}
