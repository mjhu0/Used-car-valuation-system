import javax.swing.*;import java.awt.*;import java.awt.event.*;import java.sql.*;import java.util.*;
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
}