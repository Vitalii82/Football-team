package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.TeamsDao;
import com.football.exception.DataAccessException;
import com.football.model.Team;
import java.sql.*;
import java.util.*;

public class TeamsDaoJdbc implements TeamsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int insert(Team t) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO teams(team_name, founded_year, stadium_id, manager_id) VALUES (?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getTeamName());
            if (t.getFoundedYear()==null) ps.setNull(2, Types.SMALLINT); else ps.setInt(2, t.getFoundedYear());
            if (t.getStadiumId()==null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, t.getStadiumId());
            if (t.getManagerId()==null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, t.getManagerId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next()? keys.getInt(1): -1;
            }
        } catch (SQLException e) {
            throw new DataAccessException("insert team failed", e);
        } finally { pool.release(c); }
    }

    @Override
    public List<Team> findAll() {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT team_id,team_name,founded_year,stadium_id,manager_id FROM teams ORDER BY team_name");
             ResultSet rs = ps.executeQuery()) {
            List<Team> list = new ArrayList<>();
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year"); t.setFoundedYear(rs.wasNull()? null: fy);
                int st = rs.getInt("stadium_id");   t.setStadiumId(rs.wasNull()? null: st);
                int mg = rs.getInt("manager_id");   t.setManagerId(rs.wasNull()? null: mg);
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            throw new DataAccessException("findAll teams failed", e);
        } finally { pool.release(c); }
    }

    @Override
    public Optional<Team> findByName(String name) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT team_id,team_name,founded_year,stadium_id,manager_id FROM teams WHERE team_name=?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year"); t.setFoundedYear(rs.wasNull()? null: fy);
                int st = rs.getInt("stadium_id");   t.setStadiumId(rs.wasNull()? null: st);
                int mg = rs.getInt("manager_id");   t.setManagerId(rs.wasNull()? null: mg);
                return Optional.of(t);
            }
        } catch (SQLException e) {
            throw new DataAccessException("findByName team failed", e);
        } finally { pool.release(c); }
    }

    @Override
    public Optional<Team> findById(int id) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT team_id,team_name,founded_year,stadium_id,manager_id FROM teams WHERE team_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year"); t.setFoundedYear(rs.wasNull()? null: fy);
                int st = rs.getInt("stadium_id");   t.setStadiumId(rs.wasNull()? null: st);
                int mg = rs.getInt("manager_id");   t.setManagerId(rs.wasNull()? null: mg);
                return Optional.of(t);
            }
        } catch (SQLException e) {
            throw new DataAccessException("findById team failed", e);
        } finally { pool.release(c); }
    }
}
