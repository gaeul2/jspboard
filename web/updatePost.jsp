<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>게시글 수정</title>
	<script type="text/javascript" src="static/js/write.js"></script>
</head>
<body>
	<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>글 작성</title>
		<link rel="stylesheet" href="static/css/style.css">
		<script type="text/javascript" src="static/js/edit.js"></script>
	</head>
	<body>
	<div class="container">
		<form method="post" action="/edit.do" enctype="multipart/form-data" >
			<input hidden="hidden"/>
			<input type="hidden" name="num" value="${bdto.num}">
			<input type="hidden" id="filename" name="prevOriginalFileName" value="${bdto.file_name}">
			<input type="hidden" id="savefilename" name="prevSaveFileName" value="${bdto.save_file_name}">
			<table class="create-or-update-table">
				<tr>
					<th>구분<span class="red">*</span></th>
					<td>
						<label for="selectBox"></label>
						<select name="subject" class="select-box" id="selectBox">
							<option value="unselect"><span id="unselect">선택해주세요</span></option>
							<option value="유지보수" <c:if test='${bdto.subject eq "유지보수"}'> selected </c:if>>유지보수</option>
							<option value="문의사항" <c:if test='${bdto.subject eq "문의사항"}'> selected </c:if>>문의사항</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>작성자<span class="red">*</span></th>
					<td>
						<label for="writer"></label>
						<input type='text' name="writer" id="writer" class ="writer-input" value="${bdto.writer}">
					</td>
				</tr>
				<tr>
					<th>분류<span class="red">*</span></th>
					<td>
						<div class="category-wrapper">
							<div class='detail-option-area'>
								<label for="homepage"></label>
								<input type='radio' name="category" id="homepage" value="홈페이지" <c:if test='${bdto.category eq "홈페이지"}'> checked </c:if>>홈페이지
								<label for="network"></label>
								<input type='radio' name="category" id="network" value="네트워크" <c:if test='${bdto.category eq "네트워크"}'> checked </c:if>>네트워크
								<label for="server"></label>
								<input type='radio' name="category" id="server" value="서버" <c:if test='${bdto.category eq "서버"}'> checked </c:if>>서버
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>고객유형</th>
					<td>
						<div class="type-wrapper">
							<div class='question-type-area'>
								<input type='checkbox' name='type' id="hosting" value="호스팅" <c:if test='${bdto.type.contains("호스팅")}'> checked </c:if>>
								<label for="hosting">호스팅</label>
								<input type='checkbox' name='type' id="repair" value="유지보수" <c:if test='${bdto.type.contains("유지보수")}'> checked </c:if>>
								<label for="repair">유지보수</label>
								<input type='checkbox' name='type' id="serverRent" value="서버임대" <c:if test='${bdto.type.contains("서버임대")}'> checked </c:if>>
								<label for="serverRent">서버임대</label>
								<input type='checkbox' name='type' id="etc" value="기타" <c:if test='${bdto.type.contains("기타")}'> checked </c:if>>
								<label for="etc">기타</label>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>제목<span class="red">*</span></th>
					<td>
						<label for="title"></label>
						<input type='text' name="title" id="title" class ="title-input" value="${bdto.title}">
					</td>
				</tr>
				<tr>
					<th>내용<span class="red">*</span></th>
					<td>
						<label for="content"></label>
						<textarea name="content" cols=75 rows=15 class ="content" id="content">${bdto.content}</textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:choose>
							<c:when test="${ not (empty bdto.file_name)}">
								<input type="file" name="file_name" value="${bdto.save_file_name}">
								<span>${bdto.file_name}</span>
								<a href='/download.do?originalFileName=${ URLEncoder.encode(bdto.file_name,"utf-8")}&saveFileName=${ URLEncoder.encode(bdto.save_file_name,"utf-8")}&num=${bdto.num}'><button type="button" id="downloadBtn">다운로드</button></a>
								<input type="hidden" name="prevOriginalFileName" value="${bdto.file_name}">
								<input type="hidden" name="prevSaveFileName" value="${bdto.save_file_name}">
								<a onclick="deleteFile()"><button type="button" id="deleteBtn">삭제하기</button></a>
								<a onclick="cancelDelete()"><button type="button" id="cancelBtn">파일삭제취소</button></a>
								<p class="warn">이미 게시글에 첨부파일이 있는 상태에서 새로운 파일을 올리면 기존파일은 삭제됩니다.</p>
							</c:when>
							<c:otherwise>
								<input type="file" name="file_name" onchange="checkSize(this)">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
			<div class = "lower-btn">
				<input type="hidden" name="return" value="1">
				<input type="submit" value="저장" class="button" onclick="inputValidation()">
				<!--수정하다 취소할 때 조회수 늘리지 않도록 return이라는 파라미터를 1로 넘김-->
				<a href="/view.do?num=${bdto.num}&return=1"><input type="button" value="취소" class="button"></a>
			</div>
		</form>
		<!--에러메세지 표시되는 곳 -->
		<div id="error">
		</div>
	</div>
	</body>
	</html>


</body>
</html>
