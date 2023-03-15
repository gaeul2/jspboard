import Util.FileUtil;
import Util.JSFunction;
import model1.BoardDAO;
import model1.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                HttpSession session = req.getSession();
                session.setAttribute("pass", pass);//패스워드 세션에 저장
                //num만 가지고 수정하지 못하도록 세션에 패스워드 저장해두고 서블릿에서 확인.
                resp.sendRedirect("/edit.do?num=" + num);
            } else if (mode.equals("delete")){//삭제
                int number = Integer.parseInt(num);
                BoardDTO bdto = bdao.getPost(number);
                int result = bdao.deletePost(number);
                bdao.close();
                if ( result == 1 ){//삭제성공시
                    String saveFileName = bdto.getSave_file_name();
                    FileUtil.deleteFile(req, "/uploads/", saveFileName);
                    JSFunction.alertLocation(resp, "삭제되었습니다.", "/list.do");
                }else{
                    JSFunction.alertBack(resp, "삭제에 실패하였습니다.");
                }
            }
        } else{
            JSFunction.alertBack(resp,"비밀번호가 일치하지 않습니다.");
        }
    }
}
