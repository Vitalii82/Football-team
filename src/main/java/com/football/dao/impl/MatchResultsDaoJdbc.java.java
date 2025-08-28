package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.MatchResultsDao;
import com.football.exception.DataAccessException;
import com.football.model.MatchResult;
import java.sql.*;

public class MatchResultsDaoJdbc implements MatchResultsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public int insert(MatchResult r) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO match_results(match_id,home_score,away_score) VALUES (?,?,?)")) {
            ps.setInt(1, r.getMatchId());
            ps.setInt(2, r.getHomeScore());
            ps.setInt(3, r.getAwayScore());
            return ps.executeUpdate();
        } catch (SQLException e) { throw new DataAccessException("insert result failed", e); }
        finally { pool.release(c); }
    }
}
