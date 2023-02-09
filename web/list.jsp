<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model1.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model1.BoardDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="Util.BoardPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="static/css/style.css">
	<title>글 목록</title>
</head>
<body>
<%
	BoardDAO bdao = new BoardDAO();
	int totalBoardCount = bdao.getAllPostCount();
//---------------------페이지 처리 시작------------------------//

    //페이지 총 갯수 계산
    double POSTS_PER_PAGE = 10;
    int PAGES_PER_BLOCK = 2;
	int totalPage = (int) Math.ceil(totalBoardCount / POSTS_PER_PAGE);

    //현재 페이지 확인
	int pageNum = 1; //기본값
	String currentPage = request.getParameter("pageNum");//파라미터로 받아서 현재 페이지확인
	if (currentPage != null && !currentPage.equals("")) // 아무것도 안넘어오면 1이고 넘어오면 요청받은 페이지로 바꿔줌
		pageNum = Integer.parseInt(currentPage);

	//db offset시작번호에 맞게 db인덱스 번호에 맞춘 계산식으로 바꿈(+1만 뺐다.)
	int start = (int) ((pageNum -1) * POSTS_PER_PAGE);

	//***start 와 limit을 파라미터로 전달!***
	//파라미터를 key, value로 보내기 위해 HashMap 사용
	Map<String, Object> param = new HashMap<>();
	param.put("start", start);
	param.put("limit", (int) POSTS_PER_PAGE);

//---------------------페이지 처리 끝------------------------//

	List<BoardDTO> blist = bdao.getAllPost(param);
    bdao.close();
%>
<c:set var="blist" value="<%= blist %>"/>
<div class="container">
	<!--검색기능-->
<%--	<div class ="search-box">--%>
<%--		<form method="post" action="">--%>
<%--			제목 <input type="text" name="title_search">--%>
<%--			작성자 <input type="text" name="writer_search">--%>
<%--			작성일<input type="date" name="start_date">--%>
<%--			~ <input type="date" name="end_date">--%>
<%--			<button>검색</button>--%>
<%--		</form>--%>
<%--	</div>--%>
	<div class="message-box">
	</div>
	<div class=status-wrapper>
		<div class = "total"><span>Total : <%= totalBoardCount %></span><span> Page : <%= pageNum %>/<%= totalPage %></span></div>
		<div class = "page"></div></div>
	<table class="list-table">
		<colgroup>
			<col style="width: 10%;">
			<col style="width: 10%;">
			<col style="width: 31%;">
			<col style="width: 10%;">
			<col style="width: 17%;">
			<col style="width: 15%;">
			<col style="width: 7%;">
		</colgroup>
		<thead>
		<tr>
			<!--행-->
			<th>번호</th>
			<th>구분</th>
			<th>제목</th>
			<th>첨부</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test= "${blist.size() == 0}">
				<tr>
					<td colspan="7">작성된 글이 존재 하지 않습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<%
					int indexNum = 0;
					int countNum = 0;
				%>
				<c:forEach var="bdto" items="${blist}">
					<%
						/* DB에서 들고온 제한된 개수로 offset(start)번호 ~limit개 들고와서 반복문 도니까
						   위에서부터 게시글 채워지므로 countNum 0 에서 가져온 게시글 수만큼 1씩증가.
						 */
						//전체 게시글 갯수 - ((페이지번호-1) * 페이지마다 보여줄 게시글수)
						indexNum = (int) (totalBoardCount - (((pageNum - 1) * POSTS_PER_PAGE) + countNum++));
					%>
					<tr>
						<td class="num"><%= indexNum %></td>
						<td class="category">${bdto.subject}</td>
						<td style="" class="title" id= "post-title" onclick="location.href='showPost.jsp?num=${bdto.num}'" >${bdto.title}</td>
						<td></td><!--첨부파일이미지-->
						<td class="date">${bdto.created_at}</td>
						<td class="writer">${bdto.writer}</td>
						<td class="hit">${bdto.hit}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		</tbody>
	</table>
	<div>
		<a href="write.jsp"><button class="create-btn">글쓰기</button></a>
	</div>
	<p> <%= BoardPage.pagingStr(totalBoardCount, (int) POSTS_PER_PAGE, PAGES_PER_BLOCK, pageNum, request.getRequestURI()) %></p>
</div>
</body>

</html>