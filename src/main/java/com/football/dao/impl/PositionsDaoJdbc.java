
package com.football.dao.impl;

import com.football.config.ConnectionPool;
import com.football.dao.PositionsDao;
import com.football.model.Position;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionsDaoJdbc implements PositionsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int insert(Position p) {
        String sql = "INSERT INTO positions(position_name) VALUES(?)";
        Connection c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getPositionName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException("Insert position failed", e);
        } finally {
            pool.release(c);
        }
    }

    @Override
    public List<Position> findAll() {
        String sql = "SELECT position_id, position_name FROM positions";
        Connection c = pool.borrow();
        List<Position> list = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Position p = new Position();
                p.setPositionId(rs.getInt("position_id"));
                p.setPositionName(rs.getString("position_name"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Find positions failed", e);
        } finally {
            pool.release(c);
        }
    }
}
