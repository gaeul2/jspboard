<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model1.BoardDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="model1.BoardDTO" %>
<%@ page import="Util.Validations" %>


<%
	//파일처리하면서 request.getParameter/<jsp:usebean> 이용 할 수 없게됨.
	String saveDirectory = application.getRealPath("/uploads");
	int maxPostSize = 1024 * 1000 * 5; //파일최대크기 (5MB)
	String encoding = "UTF-8"; //인코딩 방식

	MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);

	BoardDTO bdto = new BoardDTO();
	bdto.setWriter(mr.getParameter("writer"));
	bdto.setTitle(mr.getParameter("title"));
	bdto.setContent(mr.getParameter("content"));
	bdto.setSubject(mr.getParameter("subject"));
	bdto.setCategory(mr.getParameter("category"));
	bdto.setPass(mr.getParameter("pass"));

	Validations validator = new Validations();
    //고객유형 처리
    if( mr.getParameterValues("type") != null) {
		validator.typeMakeSentence(bdto, mr.getParameterValues("type"));
	} else {
        bdto.setType("");
	}

    //파일명 처리
	String fileName = mr.getFilesystemName("file_name");
    if (fileName != null){
		validator.changeFileName(bdto, fileName, saveDirectory);
	}

	BoardDAO bdao = new BoardDAO();
	bdao.createPost(bdto);

	response.sendRedirect("/list.jsp");
%>
<h1> writeProc.jsp</h1>