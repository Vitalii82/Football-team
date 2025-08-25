package com.football.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.football.config.ConnectionPool;
import com.example.football.model.Position;

public class PositionsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public int insert(Position p) throws SQLException {
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement(
                "INSERT INTO positions(position_name) VALUES(?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getPositionName());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public List<Position> findAll() throws SQLException {
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement(
                "SELECT position_id, position_name FROM positions");
             ResultSet rs = ps.executeQuery()) {
            List<Position> list = new ArrayList<>();
            while (rs.next()) {
                Position p = new Position();
                p.setPositionId(rs.getInt("position_id"));
                p.setPositionName(rs.getString("position_name"));
                list.add(p);
            }
            return list;
        }
    }
}
