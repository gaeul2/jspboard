import Util.Validations;
import com.oreilly.servlet.MultipartRequest;
import model1.BoardDAO;
import model1.BoardDTO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update.do")
public class EditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("num");
        BoardDAO bdao = new BoardDAO();
        BoardDTO bdto = new BoardDTO();
        req.setAttribute("bdto", bdto);
        req.getRequestDispatcher("/updatePost.jsp").forward(req,resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String saveDirectory = req.getServletContext().getRealPath("/uploads");
        ServletContext application = getServletContext();
        int maxPostSize = 1024* 1000 * 5;
    
        //        파일 업로드
        MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
    
        if(mr == null){
            System.out.println("첨부파일이 제한 용량을 초과합니다.");
            return;
        }
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
        String prevOriginalFileName = mr.getParameter("prevOriginalFileName");
        System.out.println("prevOriginalFileName");
        if (fileName != null){
            
            validator.changeFileName(bdto, fileName, saveDirectory);
            
            
        } else {
        
        }
    
        BoardDAO bdao = new BoardDAO();
        
        
        
        
    }
}
