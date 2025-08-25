package com.football.dao.impl;

import com.football.dao.TrainingSessionsDao;
import com.football.exception.DataAccessException;
import com.football.model.TrainingSessions;

public class TrainingSessionsDaoJdbc implements TrainingSessionsDao {
    @Override
    public int insert(TrainingSessions e) {
        String sql = "INSERT INTO training_sessions (team_id, session_date, focus_area) VALUES (?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getTeamId(), java.sql.Types.INTEGER);
            if (e.getSessionDate()==null) ps.setNull(2, java.sql.Types.DATE); else ps.setDate(2, java.sql.Date.valueOf(e.getSessionDate()));
            ps.setString(3, e.getFocusArea());
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for training_sessions", ex);
        }
    }

    @Override
    public java.util.List<TrainingSessions> findAll() {
        String sql = "SELECT * FROM training_sessions";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<TrainingSessions>();
            while (rs.next()) {
                var e = new com.football.model<TrainingSessions>();
                int _sessionId = rs.getInt("session_id"); e.setSessionId(rs.wasNull()? null : _sessionId);
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                java.sql.Date d_sessionDate = rs.getDate("session_date"); if (d_sessionDate!=null) e.setSessionDate(d_sessionDate.toLocalDate());
                e.setFocusArea(rs.getString("focus_area"));
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for training_sessions", ex);
        }
    }

    @Override
    public java.util.Optional<TrainingSessions> findById(Integer id) {
        String sql = "SELECT * FROM training_sessions WHERE session_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.TrainingSessions();
                int _sessionId = rs.getInt("session_id"); e.setSessionId(rs.wasNull()? null : _sessionId);
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                java.sql.Date d_sessionDate = rs.getDate("session_date"); if (d_sessionDate!=null) e.setSessionDate(d_sessionDate.toLocalDate());
                e.setFocusArea(rs.getString("focus_area"));
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for training_sessions", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM training_sessions WHERE session_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for training_sessions", ex);
        }
    }

}
