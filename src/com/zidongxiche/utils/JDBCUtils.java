package com.zidongxiche.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class JDBCUtils {
	
	private static final String connectionURL = "jdbc:mysql://127.0.0.1:3306/car_wash_test?useUnicode=true&characterEncoding=UTF8&useSSL=false";
    private static final String username = "root";
    private static final String password = "root";
    
    private static ArrayList<Connection> conList = new ArrayList<Connection>();
    
    //静态代码块：当整个程序执行的时候，优先加载静态代码块
    static {
        for(int i =0;i<5;i++) {
            Connection con = createConnection();
            conList.add(con);
        }
    }
    
  /**
   * 获取连接
   * @return
   */
    public static Connection getConnection() {
    	System.out.println("线程池的大小"+conList.size());
        if(conList.isEmpty()==false) {
            Connection con = conList.get(0);
            conList.remove(con);
            return con;
        }else {
            return createConnection();
        }
    }
    
    /**
     * 创建Connection
     * @return
     */
    private static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            return DriverManager.getConnection(connectionURL, username, password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    /**
     * 关闭
     * @param rs
     * @param stmt
     * @param con
     */
    public static void close(ResultSet rs,Statement stmt,Connection con) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(con);
    }
    public static void close(Statement stmt1,Statement stmt2,Connection con) {
        closeStatement(stmt1);
        closeStatement(stmt2);
        closeConnection(con);
    }
    public static void close(ResultSet rs,Statement stmt1,Statement stmt2,Statement stmt3,Connection con) {
    	closeResultSet(rs);
    	closeStatement(stmt1);
        closeStatement(stmt2);
        closeStatement(stmt3);
        closeConnection(con);
    }
    
    private static void closeResultSet(ResultSet rs ) {
        try {
            if(rs!=null)rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void closeStatement(Statement stmt) {
        try {
            if(stmt!=null)
                stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void closeConnection(Connection con) {
//        try {
//            if(con!=null)con.close();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        conList.add(con);
    }

}
