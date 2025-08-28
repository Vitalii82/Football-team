
package com.football.dao.impl;

import com.football.config.ConnectionPool;
import com.football.dao.ManagersDao;
import com.football.model.Manager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagersDaoJdbc implements ManagersDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int insert(Manager m) {
        String sql = "INSERT INTO managers(first_name,last_name,nationality,date_of_birth,experience_years) VALUES(?,?,?,?,?)";
        Connection c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getFirstName());
            ps.setString(2, m.getLastName());
            ps.setString(3, m.getNationality());
            if (m.getDateOfBirth()==null) ps.setNull(4, Types.DATE); else ps.setDate(4, Date.valueOf(m.getDateOfBirth()));
            if (m.getExperienceYears()==null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, m.getExperienceYears());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException("Insert manager failed", e);
        } finally {
            pool.release(c);
        }
    }

    @Override
    public List<Manager> findAll() {
        String sql = "SELECT manager_id, first_name, last_name, nationality, date_of_birth, experience_years FROM managers";
        Connection c = pool.borrow();
        List<Manager> list = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Manager m = new Manager();
                m.setManagerId(rs.getInt("manager_id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setNationality(rs.getString("nationality"));
                Date d = rs.getDate("date_of_birth");
                if (d!=null) m.setDateOfBirth(d.toLocalDate());
                int exp = rs.getInt("experience_years");
                m.setExperienceYears(rs.wasNull()? null : exp);
                list.add(m);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Find managers failed", e);
        } finally {
            pool.release(c);
        }
    }
}
