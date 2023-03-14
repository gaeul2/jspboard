<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>비밀번호 확인</title>
</head>
<script>


</script>
<body>
	<h1> 비밀번호를 확인 후 <%= (Objects.equals(request.getAttribute("mode").toString(), "delete"))?"삭제":"수정"%>합니다.</h1>
	<form name="checkForm" method="post" action="/pass.do" onsubmit="return validationForm(this)">
		<input type="hidden" name="num" value="${ param.num }">
		<input type="hidden" name="mode" value="${param.mode }">
		<label for="pass" >비밀번호 입력: </label>
		<input type="password" name="pass" id="pass">
		<button type="submit"><%= (Objects.equals(request.getAttribute("mode").toString(), "delete"))?"삭제":"수정"%></button>
		<button type="reset">리셋</button>
		<button type="button" onclick="location.href='/list.do?num=1'">목록으로 돌아가기</button>
	</form>
</body>
</html>
