package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.ContractsDao;
import com.football.exception.DataAccessException;
import com.football.model.Contract;
import java.sql.*; import java.util.*;

public class ContractsDaoJdbc implements ContractsDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public int insert(Contract ctt) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO contracts(player_id,team_id,start_date,end_date,salary) VALUES (?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ctt.getPlayerId());
            ps.setInt(2, ctt.getTeamId());
            ps.setDate(3, java.sql.Date.valueOf(ctt.getStartDate()));
            if (ctt.getEndDate()==null) ps.setNull(4, Types.DATE); else ps.setDate(4, java.sql.Date.valueOf(ctt.getEndDate()));
            if (ctt.getSalary()==null) ps.setBigDecimal(5, java.math.BigDecimal.ZERO); else ps.setBigDecimal(5, ctt.getSalary());
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next()? k.getInt(1): -1; }
        } catch (SQLException e) { throw new DataAccessException("insert contract failed", e); }
        finally { pool.release(c); }
    }

    @Override
    public java.util.Optional<Contract> findActiveByPlayer(int playerId) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "SELECT * FROM contracts WHERE player_id=? AND (end_date IS NULL OR end_date > CURRENT_DATE) ORDER BY start_date DESC LIMIT 1")) {
            ps.setInt(1, playerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return java.util.Optional.empty();
                Contract ct = new Contract();
                ct.setContractId(rs.getInt("contract_id"));
                ct.setPlayerId(rs.getInt("player_id"));
                ct.setTeamId(rs.getInt("team_id"));
                ct.setStartDate(rs.getDate("start_date").toLocalDate());
                java.sql.Date ed=rs.getDate("end_date"); if (ed!=null) ct.setEndDate(ed.toLocalDate());
                ct.setSalary(rs.getBigDecimal("salary"));
                return java.util.Optional.of(ct);
            }
        } catch (SQLException e) { throw new DataAccessException("active contract lookup failed", e); }
        finally { pool.release(c); }
    }

    @Override
    public int endActiveContract(int playerId, java.time.LocalDate endDate) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "UPDATE contracts SET end_date=? WHERE player_id=? AND (end_date IS NULL OR end_date > ?)")) {
            ps.setDate(1, java.sql.Date.valueOf(endDate));
            ps.setInt(2, playerId);
            ps.setDate(3, java.sql.Date.valueOf(endDate));
            return ps.executeUpdate();
        } catch (SQLException e) { throw new DataAccessException("end contract failed", e); }
        finally { pool.release(c); }
    }
}
