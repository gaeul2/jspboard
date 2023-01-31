<%@ page import="model1.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="bean" class="model1.BoardDTO">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

<%
	String[] type = (request.getParameterValues("type") == null)? new String[]{""} :request.getParameterValues("type");
	StringBuilder types = new StringBuilder();
	for (String s : type) {
		types.append(s).append(" ");
	}
    types = new StringBuilder(types.toString().trim().replace(" ", ", "));;
    bean.setType(types.toString());

	BoardDAO bdao = new BoardDAO();
	bdao.createPost(bean);

    response.sendRedirect("/list.jsp");
%>
<h1> writeProc.jsp</h1>