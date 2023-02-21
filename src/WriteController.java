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
//    파일 업로드 처리
        String saveDirectory = req.getServletContext().getRealPath("/uploads");
        ServletContext application = getServletContext();
        int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));

//        파일 업로드
        MultipartRequest mr = Util.File.uploadFile(req, saveDirectory, maxPostSize);
        if (mr == null){
            JSFunction.alertLocation(resp, "첨부 파일이 제한 용량을 초과합니다.", "/write.do");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
