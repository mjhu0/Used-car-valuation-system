package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    public static void main(String[] args) {
        new CreateDatabase();
    }
    // JDBC 驱动名称和数据库 URL
    // 注意：这些值需要根据你的数据库配置进行修改
    protected static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    protected static final String DB_URL = "jdbc:mysql://localhost:3306/shc_valuation_platform";

    // 数据库的用户名与密码
    protected static final String USER = "root";
    protected static final String PASS = "MySQL120033";

    public static Connection connect() throws SQLException {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw e; // 直接抛出 SQLException
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public CreateDatabase() {
        Connection conn = null;
        try {
            conn = connect(); // 这里可能会抛出 SQLException
            if (conn != null) {
                try (Statement stmt = conn.createStatement()) {
                    //创建登录信息
                    String sql = "CREATE TABLE IF NOT EXISTS user_login (" +
                            "user_account INTEGER NOT NULL PRIMARY KEY," +  //用户账号
                            "user_status ENUM('用户','管理员') NOT NULL ," +     //身份
                            "user_title VARCHAR(50) NOT NULL," +      //用户名
                            "password VARCHAR(50) NOT NULL);";        //用户密码
                    stmt.execute(sql);


                    //创建车辆信息
                    sql = "CREATE TABLE IF NOT EXISTS sch_info (" +
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


                    //创建用户信息
                    sql = "CREATE TABLE IF NOT EXISTS user_info (" +
                            "user_account CHAR(10) PRIMARY KEY ," +     //用户账号
                            "user_name VARCHAR(50) NOT NULL," +          //姓名
                            "user_sex ENUM('男','女') NOT NULL," +        //性别
                            "user_age INT CHECK(user_age>12 and user_age<120)," +        //年龄
                            "user_location VARCHAR(50)," +       //所在地
                            "user_id CHAR(18) NOT NULL," +     //证件号码
                            "user_phone CHAR(11) NOT NULL," +         //联系号码
                            "user_email VARCHAR(50) NOT NULL," +       //邮箱地址
                            "client_name VARCHAR(50) ," +               //委托方姓名
                            "client_id CHAR(18) ," +               //委托方证件号码
                            "commission_date VARCHAR(50) ," +         //委托日期
                            "agent_name VARCHAR(50) ," +              //经办人姓名
                            "agent_id CHAR(18) ," +                //经办人证件号码
                            "agent_phone CHAR(11) ," +            //经办人联系电话
                            "agent_email VARCHAR(50) );";           //经办人邮箱地址
                    stmt.execute(sql);
                    System.out.println("Tables created successfully...");
                }
            } else {
                System.err.println("Failed to connect to the database.");
            }
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } finally {
            // 如果需要，可以在这里关闭连接（但在这个例子中，由于使用了try-with-resources，所以不需要）
            // 注意：由于我们在try-with-resources中使用了stmt，所以不需要在这里关闭它
            System.out.println("Goodbye!"); // 这将总是被执行，无论是否有异常
        }
//        try (Connection conn = connect();
//             Statement stmt = conn.createStatement()) {
//
//
//            //创建登录信息
//            String sql = "CREATE TABLE IF NOT EXISTS users (" +
//                         "user_account INTEGER NOT NULL PRIMARY KEY," +  //用户账号
//                         "user_status ENUM('用户','管理员') NOT NULL ," +     //身份
//                         "user_title VARCHAR(50) NOT NULL," +      //用户名
//                         "password VARCHAR(50) NOT NULL);";        //用户密码
//            stmt.execute(sql);
//
//
//
//            //创建车辆信息
//            sql = "CREATE TABLE IF NOT EXISTS vehicles (" +
//                    "car_account CHAR(20) PRIMARY KEY," +     //车辆编号
//                    "car_owner VARCHAR(50) NOT NULL," +            //车主
//                    "car_brand VARCHAR(50) NOT NULL," +         //汽车品牌
//                    "car_series VARCHAR(50) NOT NULL," +        //车系
//                    "car_model VARCHAR(50)," +           //型号
//                    "car_seat_count VARCHAR(50)," +           //座位数
//                    "car_displacement VARCHAR(50) NOT NULL," +          //排量
//                    "car_tonnage VARCHAR(50) NOT NULL," +               //吨位
//                    "car_factory_date VARCHAR(50) NOT NULL," +          //出厂日期
//                    "car_enroll_date VARCHAR(50) NOT NULL," +           //登记日期
//                    "car_license_num VARCHAR(50) NOT NULL," +          //车牌号码
//                    "car_engine_type VARCHAR(50) NOT NULL," +          //发动机号码
//                    "car_function VARCHAR(50) NOT NULL," +             //使用性质（客运）
//                    "car_kilometres VARCHAR(50) NOT NULL," +           //行驶里程（公里）
//                    "car_price VARCHAR(50) NOT NULL," +                //估价结果
//                    "car_evaluate_date VARCHAR(50));";                //估价时间
//            stmt.execute(sql);
//
//
//
//            //创建用户信息
//            sql = "CREATE TABLE IF NOT EXISTS owners (" +
//                  "user_account CHAR(10) PRIMARY KEY ," +     //用户账号
//                  "user_name VARCHAR(50) NOT NULL," +          //姓名
//                  "user_sex ENUM('男','女') NOT NULL," +        //性别
//                  "user_age INT CHECK(user_age>12 and user_age<120)," +        //年龄
//                  "user_location VARCHAR(50)," +       //所在地
//                  "user_id CHAR(18) NOT NULL," +     //证件号码
//                  "user_phone CHAR(11) NOT NULL," +         //联系号码
//                  "user_email VARCHAR(50) NOT NULL," +       //邮箱地址
//                  "client_name VARCHAR(50) ," +               //委托方姓名
//                  "client_id CHAR(18) ," +               //委托方证件号码
//                  "commission_date VARCHAR(50) ," +         //委托日期
//                  "agent_name VARCHAR(50) ,"+              //经办人姓名
//                  "agent_id CHAR(18) ," +                //经办人证件号码
//                  "agent_phone CHAR(11) ," +            //经办人联系电话
//                  "agent_email VARCHAR(50) );";           //经办人邮箱地址
//            stmt.execute(sql);
//            System.out.println("Tables created successfully...");
//
//
//        } catch (SQLException se) {
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        } catch (Exception e) {
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        }
//        System.out.println("Goodbye!");
    }
}
