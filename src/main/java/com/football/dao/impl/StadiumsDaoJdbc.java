package com.football.dao.impl;

import com.football.dao.StadiumsDao;
import com.football.exception.DataAccessException;
import com.football.model.Stadiums;

public class StadiumsDaoJdbc implements StadiumsDao {
    @Override
    public int insert(Stadiums e) {
        String sql = "INSERT INTO stadiums (stadium_name, capacity, city, country) VALUES (?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getStadiumName());
            ps.setObject(2, e.getCapacity(), java.sql.Types.INTEGER);
            ps.setString(3, e.getCity());
            ps.setString(4, e.getCountry());
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for stadiums", ex);
        }
    }

    @Override
    public java.util.List<Stadiums> findAll() {
        String sql = "SELECT * FROM stadiums";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Stadiums>();
            while (rs.next()) {
                var e = new com.football.model<Stadiums>();
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                e.setStadiumName(rs.getString("stadium_name"));
                int _capacity = rs.getInt("capacity"); e.setCapacity(rs.wasNull()? null : _capacity);
                e.setCity(rs.getString("city"));
                e.setCountry(rs.getString("country"));
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for stadiums", ex);
        }
    }

    @Override
    public java.util.Optional<Stadiums> findById(Integer id) {
        String sql = "SELECT * FROM stadiums WHERE stadium_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Stadiums();
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                e.setStadiumName(rs.getString("stadium_name"));
                int _capacity = rs.getInt("capacity"); e.setCapacity(rs.wasNull()? null : _capacity);
                e.setCity(rs.getString("city"));
                e.setCountry(rs.getString("country"));
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for stadiums", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM stadiums WHERE stadium_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for stadiums", ex);
        }
    }

}
