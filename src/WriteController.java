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

@WebServlet(name = "writeController", urlPatterns = "/write.do")
public class WriteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/write.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //    파일 업로드 처리
        String saveDirectory = req.getServletContext().getRealPath("/uploads");
        ServletContext application = getServletContext();
        int maxPostSize = 1024* 1000 * 5;


//        파일 업로드
        MultipartRequest mr = Util.File.uploadFile(req, saveDirectory, maxPostSize);

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

        //list.jsp열어주는 서블릿과 연결할것.
        resp.sendRedirect("/list.jsp");

    }

}
