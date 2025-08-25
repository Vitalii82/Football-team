package com.football.dao.impl;

import com.football.dao.TransfersDao;
import com.football.exception.DataAccessException;
import com.football.model.Transfers;

public class TransfersDaoJdbc implements TransfersDao {
    @Override
    public int insert(Transfers e) {
        String sql = "INSERT INTO transfers (player_id, from_team_id, to_team_id, transfer_fee, transfer_date) VALUES (?, ?, ?, ?, ?)";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, e.getPlayerId(), java.sql.Types.INTEGER);
            ps.setObject(2, e.getFromTeamId(), java.sql.Types.INTEGER);
            ps.setObject(3, e.getToTeamId(), java.sql.Types.INTEGER);
            ps.setBigDecimal(4, e.getTransferFee());
            if (e.getTransferDate()==null) ps.setNull(5, java.sql.Types.DATE); else ps.setDate(5, java.sql.Date.valueOf(e.getTransferDate()));
            ps.executeUpdate();
            try (var keys = ps.getGeneratedKeys()) {
                if (keys.next()) return keys.getInt(1);
                return 1;
            }
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("insert failed for transfers", ex);
        }
    }

    @Override
    public java.util.List<Transfers> findAll() {
        String sql = "SELECT * FROM transfers";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql);
             var rs = ps.executeQuery()) {
            var list = new java.util.ArrayList<Transfers>();
            while (rs.next()) {
                var e = new com.football.model<Transfers>();
                int _transferId = rs.getInt("transfer_id"); e.setTransferId(rs.wasNull()? null : _transferId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _fromTeamId = rs.getInt("from_team_id"); e.setFromTeamId(rs.wasNull()? null : _fromTeamId);
                int _toTeamId = rs.getInt("to_team_id"); e.setToTeamId(rs.wasNull()? null : _toTeamId);
                e.setTransferFee(rs.getBigDecimal("transfer_fee"));
                java.sql.Date d_transferDate = rs.getDate("transfer_date"); if (d_transferDate!=null) e.setTransferDate(d_transferDate.toLocalDate());
                list.add(e);
            }
            return list;
        } catch (java.sql.SQLException ex) {
            throw new DataAccessException("findAll failed for transfers", ex);
        }
    }

    @Override
    public java.util.Optional<Transfers> findById(Integer id) {
        String sql = "SELECT * FROM transfers WHERE transfer_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                var e = new com.football.model.Transfers();
                int _transferId = rs.getInt("transfer_id"); e.setTransferId(rs.wasNull()? null : _transferId);
                int _playerId = rs.getInt("player_id"); e.setPlayerId(rs.wasNull()? null : _playerId);
                int _fromTeamId = rs.getInt("from_team_id"); e.setFromTeamId(rs.wasNull()? null : _fromTeamId);
                int _toTeamId = rs.getInt("to_team_id"); e.setToTeamId(rs.wasNull()? null : _toTeamId);
                e.setTransferFee(rs.getBigDecimal("transfer_fee"));
                java.sql.Date d_transferDate = rs.getDate("transfer_date"); if (d_transferDate!=null) e.setTransferDate(d_transferDate.toLocalDate());
                return java.util.Optional.of(e);
            }
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("findById failed for transfers", ex);
        }
    }

    @Override
    public int delete(Integer id) {
        String sql = "DELETE FROM transfers WHERE transfer_id = ?";
        try (var c = com.football.config.ConnectionManager.getConnection();
             var ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (java.sql.SQLException ex) {
            throw new com.football.exception.DataAccessException("delete failed for transfers", ex);
        }
    }

}
