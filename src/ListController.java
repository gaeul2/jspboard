import Util.BoardPage;
import model1.BoardDAO;
import model1.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "listController", urlPatterns = "/list.do")
public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO bdao = new BoardDAO();

        Map<String, Object> param = new HashMap<>();

        String title_search = req.getParameter("title_search");
        String writer_search = req.getParameter("writer_search");
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");

        if (title_search != null | writer_search != null | start_date != null | end_date !=null){
            param.put("title", title_search);
            param.put("writer", writer_search);
            param.put("start_date", start_date);
            param.put("end_date", end_date);
        }

        int totalCount = bdao.selectCount(param); //게시물 갯수
        //---------------------페이지 처리 시작------------------------//

        //페이지 총 갯수 계산
        double posts_per_page = 10;
        int pages_per_block = 2;
        int totalPage = (int) Math.ceil(totalCount / posts_per_page);
        System.out.println(totalPage);

        //현재 페이지 확인
        int pageNum = 1; //기본값
        String currentPage = req.getParameter("pageNum");//파라미터로 받아서 현재 페이지확인
        if (currentPage != null && !currentPage.equals("")) // 아무것도 안넘어오면 1이고 넘어오면 요청받은 페이지로 바꿔줌
            pageNum = Integer.parseInt(currentPage);
        //db offset시작번호에 맞게 db인덱스 번호에 맞춘 계산식으로 바꿈(+1만 뺐다.)
        int start = (int) ((pageNum -1) * posts_per_page);
        param.put("start", start);
        param.put("limit", (int) posts_per_page);

        List<BoardDTO> blist = bdao.getAllPost(param);
        bdao.close();

        String paging = BoardPage.pagingStr(totalCount, (int)posts_per_page, pages_per_block, pageNum, "/list.do");

        param.put("paging", paging);
        param.put("totalCount", totalCount);
        param.put("totalPage", totalPage);
        param.put("pageSize", (int)posts_per_page);
        param.put("pageNum", pageNum);

        req.setAttribute("blist", blist);
        req.setAttribute("map", param);
        req.getRequestDispatcher("/list.jsp").forward(req,resp);

    }
}
