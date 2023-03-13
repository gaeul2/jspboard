<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Util.Validations" %>
<%@ page import="model1.BoardDAO" %>


<jsp:useBean id="updateDto" class="model1.BoardDTO">
	<jsp:setProperty name="updateDto" property="*"/>
</jsp:useBean>

<%
	if( request.getParameterValues("type") != null) {
		Validations typeValidator = new Validations();
		typeValidator.typeMakeSentence(updateDto, request.getParameterValues("type"));
	} else {
		updateDto.setType("");
	}
	BoardDAO bdao = new BoardDAO();
    bdao.updatePost(updateDto);
	response.sendRedirect("/view.do?num="+updateDto.getNum()+"&return=1");
%>
<h4>updatePostProc.jsp</h4>