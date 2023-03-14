import Util.FileUtil;
import Util.JSFunction;
import Util.Validations;
import com.oreilly.servlet.MultipartRequest;
import model1.BoardDAO;
import model1.BoardDTO;

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
//        String saveDirectory = req.getServletContext().getRealPath("/uploads");
        String saveDirectory = "C:/jspboard/web/uploads";
        int maxPostSize = 1024 * 1000 * 5; //5MB
        
//        파일 업로드
        MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
        if(mr == null){
            JSFunction.alertLocation(resp, "파일이 5MB를 초과하였습니다.", "/write.do");
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
        if (fileName != null){
            validator.changeFileName(bdto, fileName, saveDirectory);
        }

        BoardDAO bdao = new BoardDAO();
        int result = bdao.createPost(bdto);
        bdao.close();
        
        if(result == 1) {
            JSFunction.alertLocation(resp, "게시물 작성이 완료되었습니다.", "/list.do");
        } else {
            JSFunction.alertLocation(resp, "게시물 작성에 실패했습니다.", "/write.do");
        }
    }

}
