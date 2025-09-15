package com.football.service.impl.jdbc;

import com.football.config.ConnectionPool;
import com.football.model.Team;
import com.football.service.TeamService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl implements TeamService {
    @Override
    public int create(Team t) {
        String sql = "INSERT INTO teams (team_name, founded_year, stadium_id, manager_id) VALUES (?,?,?,?)";
        Connection c = ConnectionPool.getInstance().acquire();
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, t.getTeamName());
            ps.setObject(2, t.getFoundedYear(), java.sql.Types.INTEGER);
            ps.setObject(3, t.getStadiumId(), java.sql.Types.INTEGER);
            ps.setObject(4, t.getManagerId(), java.sql.Types.INTEGER);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().release(c);
        }
    }

    @Override
    public List<Team> list() {
        String sql = "SELECT team_id, team_name, founded_year, stadium_id, manager_id FROM teams";
        Connection c = ConnectionPool.getInstance().acquire();
        try (PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Team> out = new ArrayList<>();
            while (rs.next()) {
                Team t = new Team();
                t.setTeamId(rs.getInt("team_id"));
                t.setTeamName(rs.getString("team_name"));
                int fy = rs.getInt("founded_year");
                t.setFoundedYear(rs.wasNull()? null : fy);
                int sid = rs.getInt("stadium_id");
                t.setStadiumId(rs.wasNull()? null : sid);
                int mid = rs.getInt("manager_id");
                t.setManagerId(rs.wasNull()? null : mid);
                out.add(t);
            }
            return out;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().release(c);
        }
    }
}
