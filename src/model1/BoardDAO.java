package model1;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class BoardDAO {
    
    PreparedStatement pstmt;
    ResultSet rs;
    Connection con = getcon();
    
    public BoardDAO() { // DAO객체 생성시 DB연결되도록.
        //커넥트 풀연결시 여기 변경!
    }
    
    public Connection getcon() {
        Connection con = null;
        
        String server = "localhost:3306";
        String database = "board";
        String user_name = "webuser";
        String password = "1234";
        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" 드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }
        
        try {
            con = DriverManager.getConnection("jdbc:mariadb://" +
                    server + "/" +
                    database +
                    "?useSSL=false", user_name, password); // SSL 실행 확인
            System.out.println("연결 성공");
        } catch (SQLException e) {
            System.err.println("에러 내용 :" + e.getMessage());
            e.printStackTrace();
        }

//        try {
//            if(con != null)
//                con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return con;
    }
    
    //전체 게시글 목록 가져오기
    public List<BoardDTO> getAllPost() {
        List<BoardDTO> blist = new ArrayList<>();
        
        try {
            //검색 구현시 쿼리 변경할 예정
            String query = "SELECT * FROM board ORDER BY num DESC";
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 한 게시물씩 DTO에 저장
                BoardDTO bdto = new BoardDTO();
                
                bdto.setNum(rs.getString("num"));
                bdto.setWriter(rs.getString("writer"));
                bdto.setTitle(rs.getString("title"));
                bdto.setContent(rs.getString("content"));
                bdto.setSubject(rs.getString("subject"));
                bdto.setCategory(rs.getString("Category"));
                bdto.setType(rs.getString("Type"));
                bdto.setCreated_at(rs.getDate("Created_at"));
                bdto.setPass(rs.getString("Pass"));
                bdto.setHit(rs.getInt("hit"));
                //파일 구현시
//                bdto.setFile_name(rs.getString("File_name"));
    
                blist.add(bdto);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }
        return blist;
    }
    
    public void createPost(BoardDTO bean) {
        try {
            String createPostSql = "INSERT INTO board (writer, title, content, subject, category, type, created_at, pass,hit) VALUES(?,?,?,?,?,?,now(),?,?)";
            pstmt = con.prepareStatement(createPostSql);
            pstmt.setString(1, bean.getWriter());
            pstmt.setString(2, bean.getTitle());
            pstmt.setString(3, bean.getContent());
            pstmt.setString(4, bean.getSubject());
            pstmt.setString(5, bean.getCategory());
            pstmt.setString(6, bean.getType());
            pstmt.setString(7, bean.getPass());
            pstmt.setInt(8, 0);
            pstmt.executeUpdate();
    
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}