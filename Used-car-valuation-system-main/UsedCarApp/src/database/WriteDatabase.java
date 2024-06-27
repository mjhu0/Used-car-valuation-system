package database;

import java.sql.*;

import static database.CreateDatabase.*;

public class WriteDatabase {

    public static void main(String[] args) {
        //加载驱动
//
    }


    //添加用户登录信息
    public void addUserLogin(String title, String account, String password) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName(JDBC_DRIVER);
        //获取链接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        //定义SQL
        String sql="insert into user_login (user_account,user_title,user_password)values (?,?,?) ";
        //创建PreparedStatement插入数据
        PreparedStatement pstmt=conn.prepareStatement(sql);
        //设置参数
        pstmt.setString(1,account);
        pstmt.setString(2,title);
        pstmt.setString(3,password);

        int result=pstmt.executeUpdate();
        if (result >0) {
            System.out.println("信息已成功添加至数据库！");
        }else {
            System.out.println("信息写入失败！");
        }
        //关闭资源
        pstmt.close();
        conn.close();
    }

    //注册账户时的个人信息的初始化
    public void addUserInfo(String account, String name, String sex, int age, String location, String id, String phone, String email) throws ClassNotFoundException, SQLException {
        //注册驱动
        Class.forName(JDBC_DRIVER);
        //获取链接
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        //定义SQL
        String sql="insert into user_info (user_account,user_name,user_sex,user_age,user_location,user_id,user_phone,user_email)values (?,?,?,?,?,?,?,?) ";
        //创建PreparedStatement插入数据
        PreparedStatement pstmt=conn.prepareStatement(sql);
        //设置参数
        pstmt.setString(1,account);
        pstmt.setString(2,name);
        pstmt.setString(3,sex.toString());
        pstmt.setInt(4,age);
        pstmt.setString(5,location);
        pstmt.setString(6,id);
        pstmt.setString(7,phone);
        pstmt.setString(8,email);

        int result=pstmt.executeUpdate();
        if (result >0) {
            System.out.println("信息已成功添加至数据库！");
        }else {
            System.out.println("信息写入失败！");
        }
        //关闭资源
        pstmt.close();
        conn.close();
    }
}

