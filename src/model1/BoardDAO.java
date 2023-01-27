package model1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BoardDAO {
    
    public BoardDAO(){ // DAO객체 생성시 DB연결되도록.
        getcon();
    }

    public void getcon(){
        Connection con = null;
    
        String server = "localhost:3306";
        String database = "webdb";
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
        } catch(SQLException e) {
            System.err.println("에러 내용 :" + e.getMessage());
            e.printStackTrace();
        }
    
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
