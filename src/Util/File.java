package Util;

import com.oreilly.servlet.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

public class File {
    
    //파일업로드(multipart/form-data 요청)처리
    public static MultipartRequest uploadFile(HttpServletRequest req, String saveDirectory, int maxPostSize){
        try{
            return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String saveFilename, String originalFileName){
        String saveFileDirectory = req.getServletContext().getRealPath(directory);
        try{
            File targetFile = new File(saveFileDirectory, saveFilename);
            InputStream inputStream = new FileInputStream(targetFile);
        } catch (Exception e){
        
        }
    }
}
