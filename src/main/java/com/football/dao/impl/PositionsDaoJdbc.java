package com.football.dao.impl;

import com.football.dao.PositionsDao;
import com.football.exception.DataAccessException;
import com.football.model.Positions;

public class PositionsDaoJdbc implements PositionsDao {
    @Override
    public int insert(Positions e) {
        String sql = "INSERT INTO positions (position_name) VALUES (?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getPositionName());
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for positions", ex);
        }
    }

    @Override
    public java.util.List<Positions> findAll() {
        String sql = "SELECT * FROM positions";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Positions>();
            while (rs.next()) {
                var e = new com.football.model<Positions>();
                int _positionId = rs.getInt("position_id"); e.setPositionId(rs.wasNull()? null : _positionId);
                e.setPositionName(rs.getString("position_name"));
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for positions", ex);
        }
    }

    @Override
    public java.util.Optional<Positions> findById(Integer id) {
        String sql = "SELECT * FROM positions WHERE position_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Positions();
                int _positionId = rs.getInt("position_id"); e.setPositionId(rs.wasNull()? null : _positionId);
                e.setPositionName(rs.getString("position_name"));
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for positions", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM positions WHERE position_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for positions", ex);
        }
    }

}
