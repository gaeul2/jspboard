import Util.FileUtil;
import model1.BoardDAO;
import model1.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pass.do")
public class PassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mode", req.getParameter("mode"));
        req.getRequestDispatcher("/pass.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num = req.getParameter("num");
        String mode = req.getParameter("mode");
        String pass = req.getParameter("pass");
        
        //비밀번호 확인
        BoardDAO bdao = new BoardDAO();
        boolean checked = bdao.checkPassword(pass, num);
        
        if(checked){//일치시
            if(mode.equals("edit")){//수정
            
            } else if (mode.equals("delete")){//삭제
                int number = Integer.parseInt(num);
                BoardDTO bdto = bdao.getPost(number);
                int result = bdao.deletePost(number);
                bdao.close();
                if ( result == 1 ){//삭제성공시
                    String saveFileName = bdto.getSave_file_name();
                    FileUtil.deleteFile(req, "/uploads", saveFileName);
                }
                
            }
        }
    }
}
