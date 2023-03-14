<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" href="static/css/style.css">
	<title>게시글 상세보기</title>
</head>

<body>
	<div class="container">
		<table class="post_table">
			<tr>
				<th>구분</th>
				<td>${bdto.subject}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${bdto.writer}</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>${bdto.category}</td>
			</tr>
			<tr>
				<th>고객유형</th>
				<td>${bdto.type}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${bdto.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div class="content-read">
						${bdto.content}
					</div>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:choose>
						<c:when test="${ not (empty bdto.file_name)}">
							${bdto.file_name}
							<a href='/download.do?originalFileName=${ URLEncoder.encode(bdto.file_name,"utf-8")}&saveFileName=${ URLEncoder.encode(bdto.save_file_name,"utf-8")}&num=${bdto.num}'>다운로드</a>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<div class= "read-lower-wrapper">
			<a href="/pass.do?mode=edit&num=${bdto.num}"><input type="button" value="수정"></a>
			<a href="/pass.do?mode=delete&num=${bdto.num}"><input type="button" value="삭제"></a>
			<a href="<c:url value="/list.do"/>"><input type="button" value="목록"></a>
		</div>
	</div>
</body>
</html>
