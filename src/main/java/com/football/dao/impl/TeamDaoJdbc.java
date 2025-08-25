package com.football.dao.impl;

import com.football.config.ConnectionManager;
import com.football.dao.TeamDao;
import com.football.exception.DataAccessException;
import com.football.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamDaoJdbc implements TeamDao {

    @Override
    public int insert(Team t) {
        String sql = "INSERT INTO teams(team_name, founded_year, stadium_id, manager_id) VALUES(?,?,?,?)";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getTeamName());
            if (t.getFoundedYear() == null) ps.setNull(2, Types.SMALLINT); else ps.setInt(2, t.getFoundedYear());
            if (t.getStadiumId() == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, t.getStadiumId());
            if (t.getManagerId() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, t.getManagerId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert team failed", e);
        }
    }

    @Override
    public List<Team> findAll() {
        String sql = "SELECT team_id, team_name, founded_year, stadium_id, manager_id FROM teams ORDER BY team_name";
        List<Team> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year"); t.setFoundedYear(rs.wasNull()? null : fy);
                int st = rs.getInt("stadium_id"); t.setStadiumId(rs.wasNull()? null : st);
                int mg = rs.getInt("manager_id"); t.setManagerId(rs.wasNull()? null : mg);
                out.add(t);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find all teams failed", e);
        }
        return out;
    }

    @Override
    public Optional<Team> findById(int id) {
        String sql = "SELECT team_id, team_name, founded_year, stadium_id, manager_id FROM teams WHERE team_id = ?";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year"); t.setFoundedYear(rs.wasNull()? null : fy);
                int st = rs.getInt("stadium_id"); t.setStadiumId(rs.wasNull()? null : st);
                int mg = rs.getInt("manager_id"); t.setManagerId(rs.wasNull()? null : mg);
                return Optional.of(t);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find team by id failed", e);
        }
    }

    @Override
    public int update(Team t) {
        String sql = "UPDATE teams SET team_name=?, founded_year=?, stadium_id=?, manager_id=? WHERE team_id=?";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getTeamName());
            if (t.getFoundedYear() == null) ps.setNull(2, Types.SMALLINT); else ps.setInt(2, t.getFoundedYear());
            if (t.getStadiumId() == null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, t.getStadiumId());
            if (t.getManagerId() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, t.getManagerId());
            ps.setInt(5, t.getTeamId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Update team failed", e);
        }
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM teams WHERE team_id=?";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Delete team failed", e);
        }
    }
}
