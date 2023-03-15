<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글 작성</title>
	<link rel="stylesheet" href="static/css/style.css">
	<script type="text/javascript" src="static/js/write.js"></script>
</head>

<body>
<div class="container">
	<form method="post" action="/write.do" enctype="multipart/form-data" return = "false">
	<table class="create-or-update-table">
		<tr>
			<th>구분<span class="red">*</span></th>
			<td>
				<label for="selectBox"></label>
				<select name="subject" class="select-box" id="selectBox">
					<option value="unselect"><span id="unselect">선택해주세요</span></option>
					<option value="유지보수">유지보수</option>
					<option value="문의사항">문의사항</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>작성자<span class="red">*</span></th>
			<td>
				<label for="writer"></label>
				<input type='text' name="writer" id="writer" class ="writer-input">
			</td>
		</tr>
		<tr>
			<th>분류<span class="red">*</span></th>
			<td>
				<div class="category-wrapper">
					<div class='detail-option-area'>
						<label for="homepage"></label>
						<input type='radio' name="category" id="homepage" value="홈페이지">홈페이지
						<label for="network"></label>
						<input type='radio' name="category" id="network" value="네트워크">네트워크
						<label for="server"></label>
						<input type='radio' name="category" id="server" value="서버">서버
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>고객유형</th>
			<td>
				<div class="type-wrapper">
					<div class='question-type-area'>
						<input type='checkbox' name='type' id="hosting" value="호스팅">
						<label for="hosting">호스팅</label>
						<input type='checkbox' name='type' id="repair" value="유지보수">
						<label for="repair">유지보수</label>
						<input type='checkbox' name='type' id="serverRent" value="서버임대">
						<label for="serverRent">서버임대</label>
						<input type='checkbox' name='type' id="etc" value="기타">
						<label for="etc">기타</label>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>제목<span class="red">*</span></th>
			<td>
				<label for="title"></label>
				<input type='text' name="title" id="title" class ="title-input">
			</td>
		</tr>
		<tr>
			<th>내용<span class="red">*</span></th>
			<td>
				<label for="content"></label>
				<textarea name="content" cols=75 rows=15 class ="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
				<td>
					<input type="file" name="file_name" onchange="checkSize(this)">
				</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<label for="pass"></label>
				<input type="password" name="pass" id="pass"><span class="info">게시글 수정, 삭제시 사용할 비밀번호를 숫자 4자리로 입력하세요.</span>
			</td>
		</tr>
	</table>
	<div class = "lower-btn">
		<input type="button" value="저장" class="button" onclick="inputValidation()">
		<a href="/list.do?num=1" ><input type="button" value="취소" class="button"></a>
	</div>
	</form>
	<!--에러메세지 표시되는 곳 -->
	<div id="error">

	</div>
</div>
</body>
</html>
