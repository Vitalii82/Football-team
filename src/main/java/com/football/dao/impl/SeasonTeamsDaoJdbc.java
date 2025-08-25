package com.football.dao.impl;

import com.football.dao.SeasonTeamsDao;
import com.football.exception.DataAccessException;
import com.football.model.SeasonTeams;

public class SeasonTeamsDaoJdbc implements SeasonTeamsDao {
    @Override
    public int insert(SeasonTeams e) {
        String sql = "INSERT INTO season_teams (season_id, team_id) VALUES (?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getSeasonId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getTeamId(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for season_teams", ex);
        }
    }

    @Override
    public java.util.List<SeasonTeams> findAll() {
        String sql = "SELECT * FROM season_teams";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<SeasonTeams>();
            while (rs.next()) {
                var e = new com.football.model<SeasonTeams>();
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for season_teams", ex);
        }
    }

}
