package com.football.dao.impl;

import com.football.dao.MatchResultsDao;
import com.football.exception.DataAccessException;
import com.football.model.MatchResults;

public class MatchResultsDaoJdbc implements MatchResultsDao {
    @Override
    public int insert(MatchResults e) {
        String sql = "INSERT INTO match_results (match_id, home_score, away_score) VALUES (?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getMatchId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getHomeScore(), java.sql.Types.INTEGER);
            ps.setObject(3, e.getAwayScore(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for match_results", ex);
        }
    }

    @Override
    public java.util.List<MatchResults> findAll() {
        String sql = "SELECT * FROM match_results";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<MatchResults>();
            while (rs.next()) {
                var e = new com.football.model<MatchResults>();
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _homeScore = rs.getInt("home_score"); e.setHomeScore(rs.wasNull()? null : _homeScore);
                int _awayScore = rs.getInt("away_score"); e.setAwayScore(rs.wasNull()? null : _awayScore);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for match_results", ex);
        }
    }

    @Override
    public java.util.Optional<MatchResults> findById(Integer id) {
        String sql = "SELECT * FROM match_results WHERE match_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.MatchResults();
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _homeScore = rs.getInt("home_score"); e.setHomeScore(rs.wasNull()? null : _homeScore);
                int _awayScore = rs.getInt("away_score"); e.setAwayScore(rs.wasNull()? null : _awayScore);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for match_results", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM match_results WHERE match_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for match_results", ex);
        }
    }

}
