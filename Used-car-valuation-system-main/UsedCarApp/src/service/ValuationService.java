package service;

import database.CreateDatabase;
import model.Valuation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValuationService {

    public List<Valuation> getAllValuations() {
        List<Valuation> valuations = new ArrayList<>();
        String sql = "SELECT * FROM valuations";

        try (Connection conn = CreateDatabase.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Valuation valuation = new Valuation();
                valuation.setId(rs.getInt("id"));
                valuation.setVehicleId(rs.getInt("vehicle_id"));
                valuation.setValuationDate(rs.getString("valuation_date"));
                valuation.setValuationType(rs.getString("valuation_type"));
                valuation.setEstimatedPrice(rs.getDouble("estimated_price"));
                valuations.add(valuation);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valuations;
    }

    public void deleteValuation(int id) {
        String sql = "DELETE FROM valuations WHERE id = ?";

        try (Connection conn = CreateDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Valuation getValuationById(int id) {
        String sql = "SELECT * FROM valuations WHERE id = ?";
        Valuation valuation = new Valuation();

        try (Connection conn = CreateDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                valuation.setId(rs.getInt("id"));
                valuation.setVehicleId(rs.getInt("vehicle_id"));
                valuation.setValuationDate(rs.getString("valuation_date"));
                valuation.setValuationType(rs.getString("valuation_type"));
                valuation.setEstimatedPrice(rs.getDouble("estimated_price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return valuation;
    }
}
