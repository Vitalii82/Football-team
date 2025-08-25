package com.football.dao.impl;

import com.football.config.ConnectionManager;
import com.football.dao.PositionDao;
import com.football.exception.DataAccessException;
import com.football.model.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionDaoJdbc implements PositionDao {

    @Override
    public int insert(Position p) {
        String sql = "INSERT INTO positions(position_name) VALUES(?)";
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getPositionName());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert position failed", e);
        }
    }

    @Override
    public List<Position> findAll() {
        String sql = "SELECT position_id, position_name FROM positions";
        List<Position> out = new ArrayList<>();
        try (Connection c = ConnectionManager.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Position p = new Position();
                p.setPositionId(rs.getInt("position_id"));
                p.setPositionName(rs.getString("position_name"));
                out.add(p);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Find positions failed", e);
        }
        return out;
    }
}
