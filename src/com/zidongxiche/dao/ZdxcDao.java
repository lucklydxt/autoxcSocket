package com.zidongxiche.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zidongxiche.utils.JDBCUtils;

public class ZdxcDao {

    //�������
    public static int insert(String code){
        //ע������    ʹ�������������ݿ�
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
                    result =updatestmt.executeUpdate();// ����ֵ�����յ�Ӱ�������
                    if(result>0) {
                        System.out.println("�޸ĳɹ�");
                    }else {
                        System.out.println("�޸�ʧ��");
                    }
            	}else {
            		 String sql = "insert into zdxc(code,createtime,updatetime) values(?,now(),now())";
                     stmt = con.prepareStatement(sql);
                     stmt.setString(1, code);
                     result =stmt.executeUpdate();// ����ֵ�����յ�Ӱ�������
                     System.out.println("����ɹ�"+code);
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs, selstmt,updatestmt,stmt, con);
        }
		return result;
    }
    //�޸����
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
            int result =stmt.executeUpdate();// ����ֵ�����յ�Ӱ�������
            if(result>0) {
                System.out.println("�޸ĳɹ�");
            }else {
                System.out.println("�޸�ʧ��");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
    }
}
