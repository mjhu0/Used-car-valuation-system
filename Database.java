package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:car_valuation.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void initialize() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {

            //创建用户信息
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "user_account INTEGER PRIMARY KEY AUTOINCREMENT," +  //用户编号
                         "user_status VARCHAR(50) NOT NULL," +      //身份
                         "user_title VARCHAR(50) NOT NULL," +      //用户名
                         "password VARCHAR(50) NOT NULL);";                 //用户密码
            stmt.execute(sql);



            //创建车辆信息
            sql = "CREATE TABLE IF NOT EXISTS vehicles (" +
                    "car_account CHAR(20) PRIMARY KEY," +     //车辆编号
                    "car_owner VARCHAR(50) NOT NULL," +            //车主
                    "car_brand VARCHAR(50) NOT NULL," +         //汽车品牌
                    "car_series VARCHAR(50) NOT NULL," +        //车系
                    "car_model VARCHAR(50)," +           //型号
                    "car_seat_count VARCHAR(50)," +           //座位数
                    "car_displacement VARCHAR(50) NOT NULL," +          //排量
                    "car_tonnage VARCHAR(50) NOT NULL," +               //吨位
                    "car_factory_date VARCHAR(50) NOT NULL," +          //出厂日期
                    "car_enroll_date VARCHAR(50) NOT NULL," +           //登记日期
                    "car_license_num VARCHAR(50) NOT NULL," +          //车牌号码
                    "car_engine_type VARCHAR(50) NOT NULL," +          //发动机号码
                    "car_function VARCHAR(50) NOT NULL," +             //使用性质（客运）
                    "car_kilometres VARCHAR(50) NOT NULL," +           //行驶里程（公里）
                    "car_price VARCHAR(50) NOT NULL," +                //估价结果
                    "car_evaluate_date VARCHAR(50));";                //估价时间
            stmt.execute(sql);



            //创建车主信息
            sql = "CREATE TABLE IF NOT EXISTS owners (" +
                  "user_account CHAR(10) PRIMARY KEY AUTOINCREMENT," +     //用户账号
                  "user_name VARCHAR(50) NOT NULL," +          //姓名
                  "user_sex VARCHAR(10) NOT NULL," +        //性别
                  "user_age VARCHAR(50)," +        //年龄
                  "user_location VARCHAR(50)," +       //所在地
                  "user_id VARCHAR(50) NOT NULL," +     //车主证件号码
                  "user_phone VARCHAR(50) NOT NULL," +         //车主联系号码
                  "user_email VARCHAR(50) NOT NULL," +       //邮箱地址
                  "client_name VARCHAR(50) ," +               //委托方姓名
                  "client_id VARCHAR(50) ," +               //委托方证件号码
                  "commission_date VARCHAR(50) ," +         //委托日期
                  "agent_name VARCHAR(50) ,"+              //经办人姓名
                  "agent_id VARCHAR(50) ," +                //经办人证件号码
                  "agent_phone VARCHAR(50) ," +            //经办人联系电话
                  "agent_email VARCHAR(50) );";           //经办人邮箱地址
            stmt.execute(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
