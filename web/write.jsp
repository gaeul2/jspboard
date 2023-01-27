<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>글 작성</title>
	<link rel="stylesheet" href="static/css/style.css">
	<script type="text/javascript" src="static/js/write.js"></script>
</head>

<body>
<div class="container">
	<form method="post" action="writeProc.jsp">
	<table class="create-or-update-table">
		<tr>
			<th>구분<span class="red">*</span></th>
			<td>
				<select name="subject" class="select-box">
					<option value="unselect"><span id="unselect">선택해주세요</span></option>
					<option value="유지보수">유지보수</option>
					<option value="문의사항">문의사항</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>작성자<span class="red">*</span></th>
			<td>
				<input type='text' name="writer" class ="writer">
			</td>
		</tr>
		<tr>
			<th>분류<span class="red">*</span></th>
			<td>
				<div class="category-wrapper">
					<div class='detail-option-area'>
						<input type='radio' name="category" value="홈페이지">홈페이지
						<input type='radio' name="category" value="네트워크">네트워크
						<input type='radio' name="category" value="서버">서버
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>고객유형</th>
			<td>
				<div class="type-wrapper">
					<div class='question-type-area'>
						<input type='checkbox' name='type[]' value="호스팅">호스팅
						<input type='checkbox' name='type[]' value="유지보수">유지보수
						<input type='checkbox' name='type[]' value="서버임대">서버임대
						<input type='checkbox' name='type[]' value="기타">기타
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th>제목<span class="red">*</span></th>
			<td>
				<input type='text' name="title" class ="title">
			</td>
		</tr>
		<tr>
			<th>내용<span class="red">*</span></th>
			<td>
				<textarea name="content" cols=75 rows=15 class ="content"></textarea>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
				<td>
					<input type="file" name="file_name">
				</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
			<input type="password" name="pass"><span>게시글 수정, 삭제시 사용할 비밀번호를 입력하세요.</span>
			</td>
		</tr>
	</table>
	<div class = "lower-btn">
		<input type="submit" value="저장" class="button">
		<a href="list.jsp"><input type="button" value="취소" class="button"></a>
	</div>
	</form>
	<!--에러메세지 표시되는 곳 -->

</div>
</body>
</html>
