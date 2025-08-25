package com.football.dao.impl;

import com.football.dao.TeamsDao;
import com.football.exception.DataAccessException;
import com.football.model.Teams;

public class TeamsDaoJdbc implements TeamsDao {
    @Override
    public int insert(Teams e) {
        String sql = "INSERT INTO teams (team_name, founded_year, stadium_id, manager_id) VALUES (?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, e.getTeamName());
            ps.setObject(2, e.getFoundedYear(), java.sql.Types.INTEGER);
            ps.setObject(3, e.getStadiumId(), java.sql.Types.INTEGER);
            ps.setObject(4, e.getManagerId(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for teams", ex);
        }
    }

    @Override
    public java.util.List<Teams> findAll() {
        String sql = "SELECT * FROM teams";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Teams>();
            while (rs.next()) {
                var e = new com.football.model<Teams>();
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                e.setTeamName(rs.getString("team_name"));
                int _foundedYear = rs.getInt("founded_year"); e.setFoundedYear(rs.wasNull()? null : _foundedYear);
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                int _managerId = rs.getInt("manager_id"); e.setManagerId(rs.wasNull()? null : _managerId);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for teams", ex);
        }
    }

    @Override
    public java.util.Optional<Teams> findById(Integer id) {
        String sql = "SELECT * FROM teams WHERE team_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Teams();
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                e.setTeamName(rs.getString("team_name"));
                int _foundedYear = rs.getInt("founded_year"); e.setFoundedYear(rs.wasNull()? null : _foundedYear);
                int _stadiumId = rs.getInt("stadium_id"); e.setStadiumId(rs.wasNull()? null : _stadiumId);
                int _managerId = rs.getInt("manager_id"); e.setManagerId(rs.wasNull()? null : _managerId);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for teams", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM teams WHERE team_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for teams", ex);
        }
    }

}
