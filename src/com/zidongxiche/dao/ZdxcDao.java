package com.zidongxiche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zidongxiche.utils.JDBCUtils;

public class ZdxcDao {

    //插入语句
    public static int insert(String code){
        //注册驱动    使用驱动连接数据库
        Connection con = null;
        PreparedStatement stmt = null;
        PreparedStatement selstmt = null;
        PreparedStatement updatestmt = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = JDBCUtils.getConnection();
            String selectSql=" select count(1) from zdxc where code = ? ";
            selstmt=con.prepareStatement(selectSql);
            selstmt.setString(1, code);
 
            rs=selstmt.executeQuery();
            if(rs.next()) {
            	int count=rs.getInt(1);
            	if(count>0) {
            		Date date = new Date();
            		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            		String updatetime=sdf.format(date);
            		
            		String sql = "update zdxc set updatetime = ? where code = ?";
                    updatestmt = con.prepareStatement(sql);
                    updatestmt.setString(1, updatetime);
                    updatestmt.setString(2, code);
                    result =updatestmt.executeUpdate();// 返回值代表收到影响的行数
                    if(result>0) {
                        System.out.println("修改成功");
                    }else {
                        System.out.println("修改失败");
                    }
            	}else {
            		 String sql = "insert into zdxc(code,createtime,updatetime) values(?,now(),now())";
                     stmt = con.prepareStatement(sql);
                     stmt.setString(1, code);
                     result =stmt.executeUpdate();// 返回值代表收到影响的行数
                     System.out.println("插入成功"+code);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, selstmt,updatestmt,stmt, con);
        }
		return result;
    }
    //修改语句
    public static void update(String code,String updatetime) throws SQLException {
    	Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        	con = JDBCUtils.getConnection();
            String sql = "update zdxc set updatetime = ? where code = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, updatetime);
            stmt.setString(2, code);
            int result =stmt.executeUpdate();// 返回值代表收到影响的行数
            if(result>0) {
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
    }
}
