<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Util.TypeValidate" %>
<%@ page import="model1.BoardDAO" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="updateDto" class="model1.BoardDTO">
	<jsp:setProperty name="updateDto" property="*"/>
</jsp:useBean>

<%
	if( request.getParameterValues("type") != null) {
		TypeValidate typeValidator = new TypeValidate();
		typeValidator.makeSentence(updateDto, request.getParameterValues("type"));
	} else {
		updateDto.setType("");
	}
	BoardDAO bdao = new BoardDAO();
    bdao.updatePost(updateDto);
	response.sendRedirect("/showPost.jsp?num="+updateDto.getNum()+"&return=1");
%>
<h4>updatePostProc.jsp</h4>