<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model1.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="model1.BoardDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="static/css/style.css">
	<title>게시판입니다.</title>
</head>
<body>
<%
	BoardDAO bdao = new BoardDAO();
    List<BoardDTO> blist = bdao.getAllPost();
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
		<div class = "total"></div>
		<div class = "page"></div></div>
	<table class="list-table">
		<thead>
		<tr>
			<!--행-->
			<th class="num">번호</th>
			<th class="category">구분</th>
			<th class="title">제목</th>
			<th class="file">첨부</th>
			<th class="date">작성일</th>
			<th class="writer">작성자</th>
			<th class="hit">조회수</th>
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
				<tr>
					<td class="num"></td>
					<td class="category"></td>
					<td style="" class="title" id= "post-title" onclick="location.href=''" ></td>
					<td></td><!--첨부파일이미지-->
					<td class="date"></td>
					<td class="writer"></td>
					<td class="hit"></td>
				</tr>
			</c:otherwise>
		</c:choose>

		</tbody>
	</table>
		<div>
			<a href=""><button class="create-btn">글쓰기</button></a>
		</div>
</div>
</body>

</html>