package com.football.dao.impl;
import com.football.config.ConnectionPool;
import com.football.dao.TransfersDao;
import com.football.exception.DataAccessException;
import com.football.model.Transfer;
import java.sql.*;

public class TransfersDaoJdbc implements TransfersDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public int insert(Transfer t) {
        var c = pool.borrow();
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO transfers(player_id,from_team_id,to_team_id,transfer_fee,transfer_date) VALUES (?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, t.getPlayerId());
            if (t.getFromTeamId()==null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, t.getFromTeamId());
            if (t.getToTeamId()==null) ps.setNull(3, Types.INTEGER); else ps.setInt(3, t.getToTeamId());
            if (t.getTransferFee()==null) ps.setBigDecimal(4, java.math.BigDecimal.ZERO); else ps.setBigDecimal(4, t.getTransferFee());
            ps.setDate(5, java.sql.Date.valueOf(t.getTransferDate()));
            ps.executeUpdate();
            try (ResultSet k = ps.getGeneratedKeys()) { return k.next()? k.getInt(1): -1; }
        } catch (SQLException e) { throw new DataAccessException("insert transfer failed", e); }
        finally { pool.release(c); }
    }
}
