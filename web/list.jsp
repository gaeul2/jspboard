<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLEncoder" %>

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
	<form method="get" action="/list.do" onsubmit="return false;">
		<div class ="search-box">
			<input type="hidden">
				<div class="title-search">
					<label for="title-search">제목</label>
					<input type="text" name="title_search" id="title-search">
				</div>
				<div class="writer-search">
					<label for="writer-search">작성자</label>
					<input type="text" name="writer_search" id="writer-search">
				</div>
				<div class="search-date">
					<label for="start-date">작성일</label>
					<input type="date" name="start_date" id="start-date">
					~ <input type="date" name="end_date" id="end-date">
				</div>
				<div class="search-btn">
					<button id="start_search" onclick="searchValidate()">검색</button>
				</div>
<%--			<input type="button" id="start_search" value="검색" onclick="searchValidate()">--%>
		</div>
	</form>
	<!--검색값 배열로 저장-->
	<div class="message-box">
	</div>
	<div class=status-wrapper>
		<div class = "total">
			<span>Total :
				<c:choose>
					<c:when test='${ map.totalCount == null}'>0</c:when>
					<c:otherwise>${ map.totalCount}</c:otherwise>
				</c:choose>
			</span>

		</div>
		<div class = "page">
			<span> Page :
				<c:choose>
					<c:when test='${map.totalPage == null}'>0/0</c:when>
					<c:otherwise>${map.pageNum}/${map.totalPage}</c:otherwise>
				</c:choose>
			</span>
		</div>
		<div class="return">
			<c:choose>
				<c:when test="${ searchCheck > 0 }">
					<a href="list.do?num=1"><button>전체 목록으로 돌아가기</button></a>
				</c:when>
			</c:choose>
		</div>
	</div>
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
						<c:choose>
							<c:when	test='${bdto.file_name eq null or bdto.file_name eq ""}'>
								<td></td>
							</c:when>
							<c:otherwise>
								<td>
									<div class="file_row">
										<a href='/download.do?originalFileName=${ URLEncoder.encode(bdto.file_name,"utf-8")}&saveFileName=${ URLEncoder.encode(bdto.save_file_name,"utf-8")}&num=${bdto.num}'>
											<img id="file_img" src="/static/img/save-file.png">
										</a>
									</div>
								</td><!--첨부파일이미지-->
							</c:otherwise>
						</c:choose>
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
			<a href="write.do"><button class="create-btn">글쓰기</button></a>
		</div>
	</div>
</div>
</body>

</html>