import model1.BoardDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "listController", urlPatterns = "/")
public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDAO dao = new BoardDAO();

        Map<String, Object> map = new HashMap<String, Object>();

        String title_search = req.getParameter("title_search");
        String writer_search = req.getParameter("writer_search");
        String start_date = req.getParameter("start_date");
        String end_date = req.getParameter("end_date");

        System.out.println(title_search);
        System.out.println(writer_search);
        System.out.println(start_date);
        System.out.println(end_date);


        if (title_search != null | writer_search != null | start_date != null | end_date !=null){
//            if (title_search != null){
//                map.put("title", title_search);
//            }
//            if (writer_search != null){
//                map.put("writer", writer_search);
//            }
//            if (start_date != null){
//                map.put("start_date", start_date);
//            }
//            if (end_date != null){
//                map.put("end_date", end_date);
//            }
            map.put("title", title_search);
            map.put("writer", writer_search);
            map.put("start_date", start_date);
            map.put("end_date", end_date);
        }

        int totalCount = dao.selectCount(map); //게시물 갯수

        //---------------------페이지 처리 시작------------------------//
        ServletContext application = getServletContext();

        //페이지 총 갯수 계산
        double posts_per_page = 10;
        int totalPage = (int) Math.ceil(totalCount / posts_per_page);



 /*
        //현재 페이지 확인
        int pageNum = 1; //기본값
        String currentPage = request.getParameter("pageNum");//파라미터로 받아서 현재 페이지확인
        if (currentPage != null && !currentPage.equals("")) // 아무것도 안넘어오면 1이고 넘어오면 요청받은 페이지로 바꿔줌
            pageNum = Integer.parseInt(currentPage);

        //db offset시작번호에 맞게 db인덱스 번호에 맞춘 계산식으로 바꿈(+1만 뺐다.)
        int start = (int) ((pageNum -1) * POSTS_PER_PAGE);

        //***start 와 limit을 파라미터로 전달!***
        //파라미터를 key, value로 보내기 위해 HashMap 사용
        Map<String, Object> param = new HashMap<>();
        param.put("start", start);
        param.put("limit", (int) POSTS_PER_PAGE);

//---------------------페이지 처리 끝------------------------//

        List<BoardDTO> blist = bdao.getAllPost(param);
        bdao.close();
*/
    }
}
