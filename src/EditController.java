import Util.FileUtil;
import Util.JSFunction;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/edit.do")
public class EditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("num");
        int number = Integer.parseInt(num);
        BoardDAO bdao = new BoardDAO();
        BoardDTO bdto = bdao.getPost(number);
        req.setAttribute("bdto", bdto);
        req.getRequestDispatcher("/updatePost.jsp").forward(req,resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("editdoPost");
        String saveDirectory = req.getServletContext().getRealPath("/uploads");
        ServletContext application = getServletContext();
        int maxPostSize = 1024* 1000 * 5;
        
        // 파일 업로드
        MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);

        if(mr == null){
            JSFunction.alertLocation(resp, "파일이 5MB를 초과하였습니다.", "/write.do");
            return;
        }

        String num = mr.getParameter("num");
        int number = Integer.parseInt(num);

        String originalFileName = mr.getParameter("prevOriginalFileName");
        String saveFileName = mr.getParameter("prevSaveFileName");
        String filedelete = mr.getParameter("check");
        if (Objects.equals(filedelete, "1")){
            originalFileName="";
            saveFileName="";
        }
        System.out.printf("orifile:"+originalFileName);
        //세션에서 비밀번호 가져옴
        HttpSession session = req.getSession();
        String pass = (String)session.getAttribute("pass");
        
        BoardDTO bdto = new BoardDTO();
        bdto.setNum(number);
        bdto.setWriter(mr.getParameter("writer"));
        bdto.setTitle(mr.getParameter("title"));
        bdto.setContent(mr.getParameter("content"));
        bdto.setSubject(mr.getParameter("subject"));
        bdto.setCategory(mr.getParameter("category"));
        bdto.setPass(pass);
        
        Validations validator = new Validations();
        //고객유형 처리
        if( mr.getParameterValues("type") != null) {
            validator.typeMakeSentence(bdto, mr.getParameterValues("type"));
        } else {
            bdto.setType("");
        }
    
        //파일명 처리
        String fileName = mr.getFilesystemName("file_name");
        System.out.println("fileName:" + fileName);
        if (fileName != null){ //첨부파일 있으면
            validator.changeFileName(bdto, fileName, saveDirectory);
            if(saveFileName != "") {
                FileUtil.deleteFile(req, "/uploads/", saveFileName);//기존꺼 지우고
            }
        } else { //첨부파일 없으면
            bdto.setFile_name(originalFileName);
            bdto.setSave_file_name(saveFileName);
        }
        BoardDAO bdao = new BoardDAO();
        int result = bdao.updatePost(bdto);
        bdao.close();
        
        if(result == 1){
            session.removeAttribute("pass");
            resp.sendRedirect("/view.do?num="+num);
        } else {
            JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해 주세요", "/view.do?num="+num);
        }
        
    }
}
