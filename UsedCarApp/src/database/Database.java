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
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "username TEXT NOT NULL UNIQUE," +
                         "password TEXT NOT NULL);";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS owners (" +
                  "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                  "name TEXT NOT NULL," +
                  "id_number TEXT NOT NULL," +
                  "phone TEXT NOT NULL," +
                  "address TEXT NOT NULL);";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS vehicles (" +
                  "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                  "owner_id INTEGER," +
                  "brand TEXT NOT NULL," +
                  "series TEXT NOT NULL," +
                  "model TEXT NOT NULL," +
                  "seat_count INTEGER," +
                  "displacement REAL," +
                  "tonnage REAL," +
                  "manufacture_date TEXT," +
                  "registration_date TEXT," +
                  "plate_number TEXT NOT NULL," +
                  "engine_number TEXT," +
                  "chassis_number TEXT," +
                  "usage_type TEXT," +
                  "mileage INTEGER," +
                  "FOREIGN KEY(owner_id) REFERENCES owners(id));";
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS valuations (" +
                  "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                  "vehicle_id INTEGER," +
                  "valuation_date TEXT," +
                  "valuation_type TEXT," +
                  "estimated_price REAL," +
                  "FOREIGN KEY(vehicle_id) REFERENCES vehicles(id));";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
