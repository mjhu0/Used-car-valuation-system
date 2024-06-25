package service;

import database.Database;
import model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car(sql, sql, sql, sql);
                car.setId(rs.getInt("id"));
                car.setOwnerId(rs.getInt("owner_id"));
                car.setBrand(rs.getString("brand"));
                car.setSeries(rs.getString("series"));
                car.setModel(rs.getString("model"));
                car.setSeatCount(rs.getInt("seat_count"));
                car.setDisplacement(rs.getDouble("displacement"));
                car.setTonnage(rs.getDouble("tonnage"));
                car.setManufactureDate(rs.getString("manufacture_date"));
                car.setRegistrationDate(rs.getString("registration_date"));
                car.setPlateNumber(rs.getString("plate_number"));
                car.setEngineNumber(rs.getString("engine_number"));
                car.setChassisNumber(rs.getString("chassis_number"));
                car.setUsageType(rs.getString("usage_type"));
                car.setMileage(rs.getInt("mileage"));
                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cars;
    }

	public void addCar(Car car) {
		// TODO 自动生成的方法存根
		
	}

	public void deleteCar(int carId) {
		// TODO 自动生成的方法存根
		
	}

    // 其他方法...
}
