package com.football.dao.impl;

import com.football.dao.SeasonsDao;
import com.football.exception.DataAccessException;
import com.football.model.Seasons;

public class SeasonsDaoJdbc implements SeasonsDao {
    @Override
    public int insert(Seasons e) {
        String sql = "INSERT INTO seasons (start_year, end_year) VALUES (?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getStartYear(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getEndYear(), java.sql.Types.INTEGER);
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for seasons", ex);
        }
    }

    @Override
    public java.util.List<Seasons> findAll() {
        String sql = "SELECT * FROM seasons";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Seasons>();
            while (rs.next()) {
                var e = new com.football.model<Seasons>();
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                int _startYear = rs.getInt("start_year"); e.setStartYear(rs.wasNull()? null : _startYear);
                int _endYear = rs.getInt("end_year"); e.setEndYear(rs.wasNull()? null : _endYear);
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for seasons", ex);
        }
    }

    @Override
    public java.util.Optional<Seasons> findById(Integer id) {
        String sql = "SELECT * FROM seasons WHERE season_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Seasons();
                int _seasonId = rs.getInt("season_id"); e.setSeasonId(rs.wasNull()? null : _seasonId);
                int _startYear = rs.getInt("start_year"); e.setStartYear(rs.wasNull()? null : _startYear);
                int _endYear = rs.getInt("end_year"); e.setEndYear(rs.wasNull()? null : _endYear);
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for seasons", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM seasons WHERE season_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for seasons", ex);
        }
    }

}
