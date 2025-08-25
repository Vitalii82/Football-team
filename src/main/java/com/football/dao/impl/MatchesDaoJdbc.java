package com.football.dao.impl;

import com.football.dao.MatchesDao;
import com.football.exception.DataAccessException;
import com.football.model.Matches;

public class MatchesDaoJdbc implements MatchesDao {
    @Override
    public int insert(Matches e) {
        String sql = "INSERT INTO matches (season_id, match_date, home_team_id, away_team_id, stadium_id) VALUES (?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getSeasonId(), java.sql.Types.INTEGER);
            if (e.getMatchDate()==null) ps.setNull(2, java.sql.Types.DATE); else ps.setDate(2, java.sql.Date.valueOf(e.getMatchDate()));
            ps.setObject(3, e.getHomeTeamId(), java.sql.Types.INTEGER);
            ps.setObject(4, e.getAwayTeamId(), java.sql.Types.INTEGER);
            ps.setObject(5, e.getStadiumId(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for matches", ex);
        }
    }

    @Override
    public java.util.List<Matches> findAll() {
        String sql = "SELECT * FROM matches";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Matches>();
            while (rs.next()) {
                var e = new com.football.model<Matches>();
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                java.sql.Date d_matchDate = rs.getDate("match_date"); if (d_matchDate!=null) e.setMatchDate(d_matchDate.toLocalDate());
                int _homeTeamId = rs.getInt("home_team_id"); e.setHomeTeamId(rs.wasNull()? null : _homeTeamId);
                int _awayTeamId = rs.getInt("away_team_id"); e.setAwayTeamId(rs.wasNull()? null : _awayTeamId);
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for matches", ex);
        }
    }

    @Override
    public java.util.Optional<Matches> findById(Integer id) {
        String sql = "SELECT * FROM matches WHERE match_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Matches();
                int _matchId = rs.getInt("match_id"); e.setMatchId(rs.wasNull()? null : _matchId);
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                java.sql.Date d_matchDate = rs.getDate("match_date"); if (d_matchDate!=null) e.setMatchDate(d_matchDate.toLocalDate());
                int _homeTeamId = rs.getInt("home_team_id"); e.setHomeTeamId(rs.wasNull()? null : _homeTeamId);
                int _awayTeamId = rs.getInt("away_team_id"); e.setAwayTeamId(rs.wasNull()? null : _awayTeamId);
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for matches", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM matches WHERE match_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for matches", ex);
        }
    }

}
