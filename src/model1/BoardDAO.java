package model1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
                
                bdto.setNum(rs.getInt(1));
                bdto.setWriter(rs.getString(2));
                bdto.setTitle(rs.getString(3));
                bdto.setContent(rs.getString(4));
                bdto.setSubject(rs.getString(5));
                bdto.setCategory(rs.getString(6));
                bdto.setType(rs.getString(7));
                bdto.setCreated_at(rs.getDate(8).toString());
                bdto.setPass(rs.getString(9));
                bdto.setHit(rs.getInt(10));
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
    
    public BoardDTO getPost(int num) {
        BoardDTO bdto = new BoardDTO();
        try{
            //쿼리실행
            String query = "SELECT * FROM board WHERE num=?";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while (rs.next()){
                bdto.setNum(rs.getInt("num"));
                bdto.setWriter(rs.getString("writer"));
                bdto.setTitle(rs.getString("title"));
                bdto.setContent(rs.getString("content"));
                bdto.setSubject(rs.getString("subject"));
                bdto.setCategory(rs.getString("category"));
                bdto.setType(rs.getString("type"));
                bdto.setCreated_at(rs.getString("created_at"));
                bdto.setPass(rs.getString("pass"));
                bdto.setHit(rs.getInt("hit"));
//                bdto.setFile_name(rs.getString(10));
            }
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return bdto;
    }
    
    public void hitPlus(int num) {
        try{
            String hitPlusSql = "UPDATE board SET hit = hit + 1 WHERE num=? ";
            pstmt = con.prepareStatement(hitPlusSql);
            pstmt.setInt(1, num);
            pstmt.executeUpdate();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void updatePost(BoardDTO updateDto) {
        try{
            String updatePostSql = "UPDATE board SET writer=?, title=?, content=?, subject=?, category=?, type=? WHERE num= ?";
            pstmt = con.prepareStatement(updatePostSql);
            pstmt.setString(1,updateDto.getWriter());
            pstmt.setString(2,updateDto.getTitle());
            pstmt.setString(3,updateDto.getContent());
            pstmt.setString(4,updateDto.getSubject());
            pstmt.setString(5,updateDto.getCategory());
            pstmt.setString(6,updateDto.getType());
            pstmt.setInt(7,updateDto.getNum());
            pstmt.executeUpdate();
            
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void deletePost(int num) {
        try{
            String deleteSql = "DELETE FROM board WHERE num = ?";
            pstmt = con.prepareStatement(deleteSql);
            pstmt.setInt(1,num);
            pstmt.executeUpdate();
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}