package com.football.dao.impl;

import com.football.dao.ManagersDao;
import com.football.exception.DataAccessException;
import com.football.model.Managers;

public class ManagersDaoJdbc implements ManagersDao {
    @Override
    public int insert(Managers e) {
        String sql = "INSERT INTO managers (first_name, last_name, nationality, date_of_birth, experience_years) VALUES (?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getNationality());
            if (e.getDateOfBirth()==null) ps.setNull(4, java.sql.Types.DATE); else ps.setDate(4, java.sql.Date.valueOf(e.getDateOfBirth()));
            ps.setObject(5, e.getExperienceYears(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for managers", ex);
        }
    }

    @Override
    public java.util.List<Managers> findAll() {
        String sql = "SELECT * FROM managers";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Managers>();
            while (rs.next()) {
                var e = new com.football.model<Managers>();
                int _managerId = rs.getInt("manager_id"); e.setManagerId(rs.wasNull()? null : _managerId);
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setNationality(rs.getString("nationality"));
                java.sql.Date d_dateOfBirth = rs.getDate("date_of_birth"); if (d_dateOfBirth!=null) e.setDateOfBirth(d_dateOfBirth.toLocalDate());
                int _experienceYears = rs.getInt("experience_years"); e.setExperienceYears(rs.wasNull()? null : _experienceYears);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for managers", ex);
        }
    }

    @Override
    public java.util.Optional<Managers> findById(Integer id) {
        String sql = "SELECT * FROM managers WHERE manager_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Managers();
                int _managerId = rs.getInt("manager_id"); e.setManagerId(rs.wasNull()? null : _managerId);
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setNationality(rs.getString("nationality"));
                java.sql.Date d_dateOfBirth = rs.getDate("date_of_birth"); if (d_dateOfBirth!=null) e.setDateOfBirth(d_dateOfBirth.toLocalDate());
                int _experienceYears = rs.getInt("experience_years"); e.setExperienceYears(rs.wasNull()? null : _experienceYears);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for managers", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM managers WHERE manager_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for managers", ex);
        }
    }

}
