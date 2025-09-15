package com.football.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;

public final class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    public static ConnectionPool getInstance() { return INSTANCE; }

    private final Deque<Connection> pool = new ArrayDeque<>();
    private String url, user, pass;
    private static final int SIZE = 8;

    private ConnectionPool() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("jdbc.url");
            user = p.getProperty("jdbc.user");
            pass = p.getProperty("jdbc.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i=0;i<SIZE;i++) pool.add(DriverManager.getConnection(url, user, pass));
        } catch (Exception e) {
            throw new RuntimeException("Failed to init pool", e);
        }
    }

    public synchronized Connection acquire() {
        if (pool.isEmpty()) {
            try { return DriverManager.getConnection(url, user, pass); }
            catch (Exception e) { throw new RuntimeException(e); }
        }
        return pool.pop();
    }

    public synchronized void release(Connection c) {
        if (c == null) return;
        if (pool.size() >= SIZE) { try { c.close(); } catch (Exception ignored) {} }
        else pool.push(c);
    }
}
