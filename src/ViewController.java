import model1.BoardDAO;
import model1.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "viewController", urlPatterns = "/view.do")
public class ViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int num = Integer.parseInt(req.getParameter("num"));
        BoardDAO bdao = new BoardDAO();
        /*
        return파라미터는 1. update.jsp에서 수정하다 취소눌렀을 때, 2. 수정 완료 이후
        넘겨주도록 함. 1,2의 상황들을 제외하고 게시글 조회시 조회수 값 올려줌.
         */
        if (req.getParameter("return") == null){
            //hit 늘려주고 (같은 bdao객체 사용하는데 여기서 con.close()닫으면 다음애가 못씀)
            bdao.hitPlus(num);
        }
        
        BoardDTO bdto = bdao.getPost(num);
        //줄바꿈처리
        bdto.setContent(bdto.getContent().replaceAll("\r\n","<br/>"));
        
        req.setAttribute("bdto", bdto);
        req.getRequestDispatcher("/showPost.jsp").forward(req,resp);
    }
}
