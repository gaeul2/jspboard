package Util;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize){
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
            
            
        } catch (Exception e){
        
        }
        catch (Exception e){
            System.out.println("예외가 발생하였습니다.");
            e.printStackTrace();
        }
    }
}
