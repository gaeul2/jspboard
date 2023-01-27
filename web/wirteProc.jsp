<%@ page import="model1.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="bean" class="model1.BoardDTO">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

<%
	BoardDAO bdao = new BoardDAO();
	List<String> validate_result = bdao.validate_form(bean);
    bdao.createPost(validate_result);
%>
