<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="Util.JSFunction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<%
	String saveDirectory = application.getRealPath("/uploads");
	String saveFilename = request.getParameter("saveFileName").replace(saveDirectory+"\\", "");
	String originalFileName = request.getParameter("originalFileName");

    try{
        //파일을 찾아 입력스트림 생성
		File targetFile = new File(saveDirectory, saveFilename);
		InputStream inputStream = new FileInputStream(targetFile);

		//한글파일명 깨짐방지
		String client = request.getHeader("User-Agent");
		if (client.indexOf("WOW64") == -1 ){ // 인터넷 익스플로러가 아닌 경우
			originalFileName = new String(originalFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
		} else {// 인터넷 익스플로러인 경우
			originalFileName = new String(originalFileName.getBytes("KSC5601"), StandardCharsets.ISO_8859_1);
		}

        //파일 다운로드용 응답 헤더 설정
		response.reset(); //응답헤더를 초기화
		/*파일 다운로드 창을 띄우기 위한 콘텐츠 타입 지정. octet-stream은 8비트 단위의 바이너리 데이터.
			octet-stream을 응답헤더로 설정하게 되면 파일종류에 상관없이 웹브라우저는 다운로드 창을 띄움*/
        response.setContentType("application/octet-stream");
		//웹 브라우저에서 파일 다운로드 창이 뜰때 원본파일명이 기본으로 입력되어있도록 설정
        response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");
		response.setHeader("Content-Length", "" + targetFile.length());

        //출력 스트림 초기화
		/*새로운 출력 스트림을 생성하기 위해 초기화. JSP가 열린상태에서 다운로드를 위해 또 다른 JSP를 열면
		출력 스트림이 중복으로 생성되어 이부분이 없으면 예외가 발생*/
		out.clear(); //clear()빨간줄이 뜰 경우 프로젝트 구조-라이브러리에 maven추가로 javax.servlet.api를 추가할것.

		//response내장 객체로 부터 새로운 출력 스트림 생성
		OutputStream outputStream = response.getOutputStream();

        //출력 스트림에 파일 내용 출력
		byte b[] = new byte[(int) targetFile.length()];
        int readBuffer = 0;
        while ( (readBuffer = inputStream.read(b)) > 0 ) {
            outputStream.write(b, 0, readBuffer);
		}

        //입출력 스트림 닫음
		inputStream.close();
        outputStream.close();
    } catch (FileNotFoundException e){
		System.out.println("파일을 찾을 수 없습니다." + out);

    } catch (Exception e) {
		System.out.println("예외가 발생하였습니다." + out);
    }
%>
</body>
</html>
