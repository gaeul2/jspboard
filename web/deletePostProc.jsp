<%@ page import="model1.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	BoardDAO bdao = new BoardDAO();
    bdao.deletePost(num);
	String msg = "삭제가 완료되었습니다.";
    response.sendRedirect("/list.do?num=1");
%>
<h2>delete.jsp</h2>