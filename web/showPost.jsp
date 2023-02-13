<%@ page import="model1.BoardDAO" %>
<%@ page import="model1.BoardDTO" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<link rel="stylesheet" href="static/css/style.css">
	<title>게시글 상세보기</title>
</head>
<body>
	<%
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO bdao = new BoardDAO();

        /*
        return파라미터는
        	update.jsp에서 수정하다 취소눌렀을 때,
        	수정 완료 이후,
        넘겨주도록 함.
        위의 상황들을 제외하고 게시글 조회시 조회수 값 올려줌.
         */
        if (request.getParameter("return") == null){
        	//hit 늘려주고 (같은 bdao객체 사용하는데 여기서 con.close()닫으면 다음애가 못씀)
			bdao.hitPlus(num);
        }
		//보여주고
        BoardDTO bdto = bdao.getPost(num);
	%>
	<c:set var="post" value="<%= bdto %>"/>
	<div class="container">
		<table class="post_table">
			<tr>
				<th>구분</th>
				<td>${post.subject}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${post.writer}</td>
			</tr>
			<tr>
				<th>분류</th>
				<td>${post.category}</td>
			</tr>
			<tr>
				<th>고객유형</th>
				<td>${post.type}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${post.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div class="content-read">
						${post.content}
					</div>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:choose>
						<c:when test="${ not (empty post.file_name)}">
							${post.file_name}
							<a href='Download.jsp?originalFileName=${ URLEncoder.encode(post.file_name,"utf-8")}&saveFileName=${ URLEncoder.encode(post.save_file_name,"utf-8")}'>다운로드</a>

						</c:when>
						<c:otherwise>
							<p>${post.file_name} ${post.file_name.length()}</p>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		<div class= "read-lower-wrapper">
			<a href="<c:url value="/updatePost.jsp"><c:param name="num" value="${post.num}"/></c:url>"><input type="button" value="수정"></a>
			<a href="<c:url value="/deletePostProc.jsp"><c:param name="num" value="${post.num}"/></c:url>"><input type="button" value="삭제"></a>
			<a href="<c:url value="/list.jsp"/>"><input type="button" value="목록"></a>
		</div>
	</div>
</body>
</html>
