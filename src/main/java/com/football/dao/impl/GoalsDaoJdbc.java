package com.football.dao.impl;

import com.football.dao.GoalsDao;
import com.football.exception.DataAccessException;
import com.football.model.Goals;

public class GoalsDaoJdbc implements GoalsDao {
    @Override
    public int insert(Goals e) {
        String sql = "INSERT INTO goals (match_id, player_id, minute_scored, is_own_goal) VALUES (?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getMatchId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getPlayerId(), java.sql.Types.INTEGER);
            ps.setObject(3, e.getMinuteScored(), java.sql.Types.INTEGER);
            if (e.getIsOwnGoal()==null) ps.setNull(4, java.sql.Types.BOOLEAN); else ps.setBoolean(4, e.getIsOwnGoal());
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for goals", ex);
        }
    }

    @Override
    public java.util.List<Goals> findAll() {
        String sql = "SELECT * FROM goals";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Goals>();
            while (rs.next()) {
                var e = new com.football.model<Goals>();
                int _goalId = rs.getInt("goal_id"); e.setGoalId(rs.wasNull()? null : _goalId);
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _minuteScored = rs.getInt("minute_scored"); e.setMinuteScored(rs.wasNull()? null : _minuteScored);
                boolean b_isOwnGoal = rs.getBoolean("is_own_goal"); e.setIsOwnGoal(rs.wasNull()? null : b_isOwnGoal);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for goals", ex);
        }
    }

    @Override
    public java.util.Optional<Goals> findById(Integer id) {
        String sql = "SELECT * FROM goals WHERE goal_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Goals();
                int _goalId = rs.getInt("goal_id"); e.setGoalId(rs.wasNull()? null : _goalId);
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _minuteScored = rs.getInt("minute_scored"); e.setMinuteScored(rs.wasNull()? null : _minuteScored);
                boolean b_isOwnGoal = rs.getBoolean("is_own_goal"); e.setIsOwnGoal(rs.wasNull()? null : b_isOwnGoal);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for goals", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM goals WHERE goal_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for goals", ex);
        }
    }

}
