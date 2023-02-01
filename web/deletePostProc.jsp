<%@ page import="model1.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	BoardDAO bdao = new BoardDAO();
    bdao.deletePost(num);

    response.sendRedirect("/list.jsp");
%>
<h2>delete.jsp</h2>