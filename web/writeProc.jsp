<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model1.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="Util.TypeValidate" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="bean" class="model1.BoardDTO">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

<%
	if( request.getParameterValues("type") != null) {
		TypeValidate typeValidator = new TypeValidate();
		typeValidator.makeSentence(bean, request.getParameterValues("type"));
	} else {
        bean.setType("");
	}

	BoardDAO bdao = new BoardDAO();
	bdao.createPost(bean);

	response.sendRedirect("/list.jsp");
%>
<h1> writeProc.jsp</h1>