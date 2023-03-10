package Util;

import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;
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
}
