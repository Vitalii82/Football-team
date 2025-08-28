package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.SeasonsDao;
import com.football.exception.DataAccessException;
import com.football.model.Season;
import java.sql.*; import java.util.*;

public class SeasonsDaoJdbc implements SeasonsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public int insert(Season s) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO seasons(start_year,end_year) VALUES (?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getStartYear());
            ps.setInt(2, s.getEndYear());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next()? k.getInt(1): -1; }
        } catch (SQLException e) { throw new DataAccessException("insert season failed", e); }
        finally { pool.release(c); }
    }
    @Override
    public java.util.Optional<Season> findByYears(int start, int end) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM seasons WHERE start_year=? AND end_year=?")) {
            ps.setInt(1, start); ps.setInt(2, end);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                Season s = new Season();
                s.setSeasonId(rs.getInt("season_id"));
                s.setStartYear(rs.getInt("start_year"));
                s.setEndYear(rs.getInt("end_year"));
                return java.util.Optional.of(s);
            }
        } catch (SQLException e) { throw new DataAccessException("find season failed", e); }
        finally { pool.release(c); }
    }
}
