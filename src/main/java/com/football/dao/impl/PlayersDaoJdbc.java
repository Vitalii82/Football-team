package com.football.dao.impl;

import com.football.dao.PlayersDao;
import com.football.exception.DataAccessException;
import com.football.model.Players;

public class PlayersDaoJdbc implements PlayersDao {
    @Override
    public int insert(Players e) {
        String sql = "INSERT INTO players (first_name, last_name, nationality, date_of_birth, height_cm, weight_kg) VALUES (?, ?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getNationality());
            if (e.getDateOfBirth()==null) ps.setNull(4, java.sql.Types.DATE); else ps.setDate(4, java.sql.Date.valueOf(e.getDateOfBirth()));
            ps.setObject(5, e.getHeightCm(), java.sql.Types.INTEGER);
            ps.setObject(6, e.getWeightKg(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for players", ex);
        }
    }

    @Override
    public java.util.List<Players> findAll() {
        String sql = "SELECT * FROM players";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Players>();
            while (rs.next()) {
                var e = new com.football.model<Players>();
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setNationality(rs.getString("nationality"));
                java.sql.Date d_dateOfBirth = rs.getDate("date_of_birth"); if (d_dateOfBirth!=null) e.setDateOfBirth(d_dateOfBirth.toLocalDate());
                int _heightCm = rs.getInt("height_cm"); e.setHeightCm(rs.wasNull()? null : _heightCm);
                int _weightKg = rs.getInt("weight_kg"); e.setWeightKg(rs.wasNull()? null : _weightKg);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for players", ex);
        }
    }

    @Override
    public java.util.Optional<Players> findById(Integer id) {
        String sql = "SELECT * FROM players WHERE player_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Players();
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setNationality(rs.getString("nationality"));
                java.sql.Date d_dateOfBirth = rs.getDate("date_of_birth"); if (d_dateOfBirth!=null) e.setDateOfBirth(d_dateOfBirth.toLocalDate());
                int _heightCm = rs.getInt("height_cm"); e.setHeightCm(rs.wasNull()? null : _heightCm);
                int _weightKg = rs.getInt("weight_kg"); e.setWeightKg(rs.wasNull()? null : _weightKg);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for players", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM players WHERE player_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for players", ex);
        }
    }

}
