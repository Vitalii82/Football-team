package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.PlayersDao;
import com.football.exception.DataAccessException;
import com.football.model.Player;
import java.sql.*; import java.util.*;

public class PlayersDaoJdbc implements PlayersDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int insert(Player p) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO players(first_name,last_name,nationality,date_of_birth,height_cm,weight_kg) VALUES (?,?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getNationality());
            if (p.getDateOfBirth()==null) ps.setNull(4, Types.DATE); else ps.setDate(4, java.sql.Date.valueOf(p.getDateOfBirth()));
            if (p.getHeightCm()==null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, p.getHeightCm());
            if (p.getWeightKg()==null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, p.getWeightKg());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next()? k.getInt(1): -1; }
        } catch (SQLException e) { throw new DataAccessException("insert player failed", e); }
        finally { pool.release(c); }
    }

    @Override
    public Optional<Player> findById(int id) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players WHERE player_id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setNationality(rs.getString("nationality"));
                java.sql.Date d = rs.getDate("date_of_birth"); if (d!=null) p.setDateOfBirth(d.toLocalDate());
                int h=rs.getInt("height_cm"); p.setHeightCm(rs.wasNull()? null: h);
                int w=rs.getInt("weight_kg"); p.setWeightKg(rs.wasNull()? null: w);
                return Optional.of(p);
            }
        } catch (SQLException e) { throw new DataAccessException("find player failed", e); }
        finally { pool.release(c); }
    }

    @Override
    public java.util.List<Player> findAll() {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM players");
             ResultSet rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Player>();
            while (rs.next()) {
                Player p = new Player();
                p.setPlayerId(rs.getInt("player_id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                list.add(p);
            }
            return list;
        } catch (SQLException e) { throw new DataAccessException("findAll players failed", e); }
        finally { pool.release(c); }
    }
}
