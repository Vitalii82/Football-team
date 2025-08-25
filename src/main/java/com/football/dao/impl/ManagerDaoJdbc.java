package com.football.dao.impl;

import com.football.config.ConnectionManager;
import com.football.dao.ManagerDao;
import com.football.exception.DataAccessException;
import com.football.model.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoJdbc implements ManagerDao {

    @Override
    public int insert(Manager m) {
        String sql = "INSERT INTO managers(first_name,last_name,nationality,date_of_birth,experience_years) VALUES(?,?,?,?,?)";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getFirstName());
            ps.setString(2, m.getLastName());
            ps.setString(3, m.getNationality());
            if (m.getDateOfBirth() == null) ps.setNull(4, Types.DATE);
            else ps.setDate(4, Date.valueOf(m.getDateOfBirth()));
            if (m.getExperienceYears() == null) ps.setNull(5, Types.INTEGER);
            else ps.setInt(5, m.getExperienceYears());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert manager failed", e);
        }
    }

    @Override
    public List<Manager> findAll() {
        String sql = "SELECT manager_id, first_name, last_name, nationality, date_of_birth, experience_years FROM managers";
        List<Manager> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Manager m = new Manager();
                m.setManagerId(rs.getInt("manager_id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setNationality(rs.getString("nationality"));
                Date d = rs.getDate("date_of_birth");
                if (d != null) m.setDateOfBirth(d.toLocalDate());
                int exp = rs.getInt("experience_years");
                m.setExperienceYears(rs.wasNull() ? null : exp);
                out.add(m);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find managers failed", e);
        }
        return out;
    }
}
