import Util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/download.do")
public class DownloadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String originalFileName = req.getParameter("originalFileName");
        String saveFileName = req.getParameter("saveFileName");
    
        if (saveFileName.contains("uploads")) {
            saveFileName = saveFileName.split("uploads")[1].replace("\\","");
        }
        String num = req.getParameter("num");
        
        //파일 다운로드
        FileUtil.download(req, resp, "/uploads", saveFileName, originalFileName);
        
    }
}
