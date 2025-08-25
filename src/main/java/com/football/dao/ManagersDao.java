package com.football.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.example.football.config.ConnectionPool;
import com.example.football.model.Manager;

public class ManagersDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public int insert(Manager m) throws SQLException {
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement(
                "INSERT INTO managers(first_name,last_name,nationality,date_of_birth,experience_years) VALUES(?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS)) {
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
        }
    }

    public List<Manager> findAll() throws SQLException {
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement(
                "SELECT manager_id, first_name, last_name, nationality, date_of_birth, experience_years FROM managers");
             ResultSet rs = ps.executeQuery()) {
            List<Manager> list = new ArrayList<>();
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
                list.add(m);
            }
            return list;
        }
    }
}
