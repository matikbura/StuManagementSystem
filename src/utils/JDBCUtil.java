package utils;

import java.sql.*;

public class JDBCUtil {

    private static String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/sms?useSSL=false";
    private static String User = "root";
    private static String Password = "123456";


    static{
        try {
            Class.forName(DRIVERCLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    public static Connection getConnction() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, User, Password);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return conn;
    }


    public  static void close(ResultSet resultSet, Connection conn, Statement stat){

        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }


        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }


        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stat){


        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}