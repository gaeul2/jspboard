<%@ page import="model1.BoardDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="bean" class="model1.BoardDTO">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

	<h1> writeProc.jsp</h1>
<%
	request.setCharacterEncoding("UTF-8");
	String writer = bean.getWriter().trim();
	BoardDAO bdao = new BoardDAO();
    String type = bean.getType().trim();
	System.out.println(type);

//    bdao.createPost(bean);
//	for (int i = 0; i < validateResult.size(); i++){
//        System.out.println(validateResult.get(i));
//	}
%>
