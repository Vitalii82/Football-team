package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.MatchesDao;
import com.football.exception.DataAccessException;
import com.football.model.Match;
import java.sql.*; import java.util.*;

public class MatchesDaoJdbc implements MatchesDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public int insert(Match m) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO matches(season_id,match_date,home_team_id,away_team_id,stadium_id) VALUES (?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getSeasonId());
            ps.setDate(2, java.sql.Date.valueOf(m.getMatchDate()));
            ps.setInt(3, m.getHomeTeamId());
            ps.setInt(4, m.getAwayTeamId());
            if (m.getStadiumId()==null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, m.getStadiumId());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next()? k.getInt(1): -1; }
        } catch (SQLException e) { throw new DataAccessException("insert match failed", e); }
        finally { pool.release(c); }
    }

    @Override
    public java.util.Optional<Match> findExisting(Integer seasonId, Integer homeTeamId, Integer awayTeamId, java.time.LocalDate date) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "SELECT * FROM matches WHERE season_id=? AND home_team_id=? AND away_team_id=? AND match_date=?")) {
            ps.setInt(1, seasonId); ps.setInt(2, homeTeamId); ps.setInt(3, awayTeamId); ps.setDate(4, java.sql.Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                Match m = new Match();
                m.setMatchId(rs.getInt("match_id"));
                m.setSeasonId(rs.getInt("season_id"));
                m.setMatchDate(rs.getDate("match_date").toLocalDate());
                m.setHomeTeamId(rs.getInt("home_team_id"));
                m.setAwayTeamId(rs.getInt("away_team_id"));
                int st=rs.getInt("stadium_id"); m.setStadiumId(rs.wasNull()? null: st);
                return java.util.Optional.of(m);
            }
        } catch (SQLException e) { throw new DataAccessException("find existing match failed", e); }
        finally { pool.release(c); }
    }
}
