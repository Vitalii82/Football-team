package com.football.dao.impl;

import com.football.dao.PlayerStatisticsDao;
import com.football.exception.DataAccessException;
import com.football.model.PlayerStatistics;

public class PlayerStatisticsDaoJdbc implements PlayerStatisticsDao {
    @Override
    public int insert(PlayerStatistics e) {
        String sql = "INSERT INTO player_statistics (player_id, season_id, matches_played, goals_scored, assists, yellow_cards, red_cards) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getPlayerId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getSeasonId(), java.sql.Types.INTEGER);
            ps.setObject(3, e.getMatchesPlayed(), java.sql.Types.INTEGER);
            ps.setObject(4, e.getGoalsScored(), java.sql.Types.INTEGER);
            ps.setObject(5, e.getAssists(), java.sql.Types.INTEGER);
            ps.setObject(6, e.getYellowCards(), java.sql.Types.INTEGER);
            ps.setObject(7, e.getRedCards(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for player_statistics", ex);
        }
    }

    @Override
    public java.util.List<PlayerStatistics> findAll() {
        String sql = "SELECT * FROM player_statistics";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<PlayerStatistics>();
            while (rs.next()) {
                var e = new com.football.model<PlayerStatistics>();
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                int _matchesPlayed = rs.getInt("matches_played"); e.setMatchesPlayed(rs.wasNull()? null : _matchesPlayed);
                int _goalsScored = rs.getInt("goals_scored"); e.setGoalsScored(rs.wasNull()? null : _goalsScored);
                int _assists = rs.getInt("assists"); e.setAssists(rs.wasNull()? null : _assists);
                int _yellowCards = rs.getInt("yellow_cards"); e.setYellowCards(rs.wasNull()? null : _yellowCards);
                int _redCards = rs.getInt("red_cards"); e.setRedCards(rs.wasNull()? null : _redCards);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for player_statistics", ex);
        }
    }

}
