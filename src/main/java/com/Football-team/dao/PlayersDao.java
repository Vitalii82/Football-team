package com.example.football.dao;

import com.example.football.config.ConnectionPool;
import java.sql.*;
import java.util.*;

public class PlayersDao {{
    private final ConnectionPool pool = ConnectionPool.getInstance();

    public List<Map<String,Object>> findAll() throws SQLException {{
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery()) {{
            List<Map<String,Object>> out = new ArrayList<>();
            while (rs.next()) {{
                Map<String,Object> row = new HashMap<>();
                row.put("one", rs.getInt(1));
                out.add(row);
            }}
            return out;
        }} finally {{
            // connection returned by try-with-resources
        }}
    }}

    public int insertDummy() throws SQLException {{
        try (Connection c = pool.borrow();
             PreparedStatement ps = c.prepareStatement("SELECT 1")) {{
            try (ResultSet rs = ps.executeQuery()) {{
                return rs.next() ? rs.getInt(1) : 0;
            }}
        }}
    }}
}}
