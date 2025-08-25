package com.football.dao.impl;

import com.football.dao.PlayerPositionsDao;
import com.football.exception.DataAccessException;
import com.football.model.PlayerPositions;

public class PlayerPositionsDaoJdbc implements PlayerPositionsDao {
    @Override
    public int insert(PlayerPositions e) {
        String sql = "INSERT INTO player_positions (player_id, position_id) VALUES (?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getPlayerId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getPositionId(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for player_positions", ex);
        }
    }

    @Override
    public java.util.List<PlayerPositions> findAll() {
        String sql = "SELECT * FROM player_positions";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<PlayerPositions>();
            while (rs.next()) {
                var e = new com.football.model<PlayerPositions>();
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _positionId = rs.getInt("position_id"); e.setPositionId(rs.wasNull()? null : _positionId);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for player_positions", ex);
        }
    }

}
