package Util;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize) {
        try{
            return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String saveFilename, String originalFileName){
        String saveDirectory = req.getServletContext().getRealPath(directory);
        
        try{
            File targetFile = new File(saveDirectory, saveFilename);
            InputStream inputStream = new FileInputStream(targetFile);
    
            //한글파일명 깨짐방지
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1 ){ // 인터넷 익스플로러가 아닌 경우
                originalFileName = new String(originalFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            } else {// 인터넷 익스플로러인 경우
                originalFileName = new String(originalFileName.getBytes("KSC5601"), StandardCharsets.ISO_8859_1);
            }
            
            //파일 다운로드 용 응답 헤더 설정
            resp.reset(); //응답헤더를 초기화
		/*파일 다운로드 창을 띄우기 위한 콘텐츠 타입 지정. octet-stream은 8비트 단위의 바이너리 데이터.
			octet-stream을 응답헤더로 설정하게 되면 파일종류에 상관없이 웹브라우저는 다운로드 창을 띄움*/
            resp.setContentType("application/octet-stream");
            //웹 브라우저에서 파일 다운로드 창이 뜰때 원본파일명이 기본으로 입력되어있도록 설정
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\"");
            resp.setHeader("Content-Length", "" + targetFile.length());
    
            OutputStream outputStream = resp.getOutputStream();
            
            //출력스트림에 파일내용 출력
            byte b[] = new byte[(int) targetFile.length()];
            int readBuffer = 0;
            while ((readBuffer = inputStream.read(b)) > 0 ){
                outputStream.write(b, 0, readBuffer);
            }
            inputStream.close();
            outputStream.close();
        
        } catch (FileNotFoundException e){
            JSFunction.alertBack(resp, "파일을 찾을 수 없습니다.");
            e.printStackTrace();
        
        }
        catch (Exception e){
            JSFunction.alertBack(resp, "예외가 발생하였습니다.");
            e.printStackTrace();
        }
    }
    
    public static void deleteFile(HttpServletRequest req, String directory, String filename){
        String saveDirectory = req.getServletContext().getRealPath(directory);
        File targetFile = new File(saveDirectory + File.separator + filename);
        if (targetFile.exists()){
            targetFile.delete();
        }
    }
    
}
