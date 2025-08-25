package com.football.dao.impl;

import com.football.dao.ContractsDao;
import com.football.exception.DataAccessException;
import com.football.model.Contracts;

public class ContractsDaoJdbc implements ContractsDao {
    @Override
    public int insert(Contracts e) {
        String sql = "INSERT INTO contracts (player_id, team_id, start_date, end_date, salary) VALUES (?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getPlayerId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getTeamId(), java.sql.Types.INTEGER);
            if (e.getStartDate()==null) ps.setNull(3, java.sql.Types.DATE); else ps.setDate(3, java.sql.Date.valueOf(e.getStartDate()));
            if (e.getEndDate()==null) ps.setNull(4, java.sql.Types.DATE); else ps.setDate(4, java.sql.Date.valueOf(e.getEndDate()));
            ps.setBigDecimal(5, e.getSalary());
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for contracts", ex);
        }
    }

    @Override
    public java.util.List<Contracts> findAll() {
        String sql = "SELECT * FROM contracts";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Contracts>();
            while (rs.next()) {
                var e = new com.football.model<Contracts>();
                int _contractId = rs.getInt("contract_id"); e.setContractId(rs.wasNull()? null : _contractId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                java.sql.Date d_startDate = rs.getDate("start_date"); if (d_startDate!=null) e.setStartDate(d_startDate.toLocalDate());
                java.sql.Date d_endDate = rs.getDate("end_date"); if (d_endDate!=null) e.setEndDate(d_endDate.toLocalDate());
                e.setSalary(rs.getBigDecimal("salary"));
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for contracts", ex);
        }
    }

    @Override
    public java.util.Optional<Contracts> findById(Integer id) {
        String sql = "SELECT * FROM contracts WHERE contract_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Contracts();
                int _contractId = rs.getInt("contract_id"); e.setContractId(rs.wasNull()? null : _contractId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _teamId = rs.getInt("team_id"); e.setTeamId(rs.wasNull()? null : _teamId);
                java.sql.Date d_startDate = rs.getDate("start_date"); if (d_startDate!=null) e.setStartDate(d_startDate.toLocalDate());
                java.sql.Date d_endDate = rs.getDate("end_date"); if (d_endDate!=null) e.setEndDate(d_endDate.toLocalDate());
                e.setSalary(rs.getBigDecimal("salary"));
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for contracts", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM contracts WHERE contract_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for contracts", ex);
        }
    }

}
