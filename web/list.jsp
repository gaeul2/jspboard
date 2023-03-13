<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="static/css/style.css">
	<script type="text/javascript" src="static/js/search.js"></script>
	<title>글 목록</title>

</head>
<body>
<div class="container">
	<!--검색기능-->
	<div class ="search-box">
		<form method="get" action="/list.do" onsubmit="return false;">
			<input type="hidden" >
			제목 <input type="text" name="title_search">
			작성자 <input type="text" name="writer_search">
			작성일<input type="date" name="start_date">
			~ <input type="date" name="end_date">
			<input type="button" class="start_search" value="검색" onclick="searchValidate()">
		</form>
	</div>
	<!--검색값 배열로 저장-->
	<div class="message-box">
	</div>
	<div class=status-wrapper>
		<div class = "total"><span>Total : ${ map.totalCount}</span><span> Page : ${map.pageNum}/${map.totalPage}</span></div>
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
			<c:when test= "${ empty blist }">
				<tr>
					<td colspan="7">작성된 글이 존재 하지 않습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="bdto" items="${blist}" varStatus="loop">

<%--					/* DB에서 들고온 제한된 개수로 offset(start)번호 ~limit개 들고와서 반복문 도니까--%>
<%--					 	위에서부터 게시글 채워지므로 countNum 0 에서 가져온 게시글 수만큼 1씩증가.--%>
<%--					 */--%>
<%--						//전체 게시글 갯수 - ((페이지번호-1) * 페이지마다 보여줄 게시글수)--%>

					<tr>
						<td class="num">${ map.totalCount - (((map.pageNum - 1)* map.pageSize) + loop.index)}</td>
						<td class="category">${bdto.subject}</td>
						<td style="" class="title" id= "post-title" onclick="location.href='view.do?num=${bdto.num}'" >${bdto.title}</td>
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
	<div class = "lower-wrapper">
		<div class="pager">
			${map.paging}
		</div>
		<div>
			<a href="write.jsp"><button class="create-btn">글쓰기</button></a>
		</div>
	</div>
</div>
</body>

</html>