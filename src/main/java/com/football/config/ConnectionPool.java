package com.football.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {
    private static ConnectionPool INSTANCE;
    private final BlockingQueue<Connection> pool;

    private ConnectionPool() {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties")) {
            Properties p = new Properties();
            p.load(in);
            String url  = p.getProperty("jdbc.url");
            String user = p.getProperty("jdbc.user");
            String pass = p.getProperty("jdbc.password");
            int size = Integer.parseInt(p.getProperty("pool.size","8"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            pool = new ArrayBlockingQueue<>(size);
            for (int i=0;i<size;i++) {
                pool.add(DriverManager.getConnection(url, user, pass));
            }
        } catch (Exception e) {
            throw new RuntimeException("Init pool failed", e);
        }
    }
    public static synchronized ConnectionPool getInstance() {
        if (INSTANCE==null) INSTANCE = new ConnectionPool();
        return INSTANCE;
    }
    public Connection borrow() {
        try { return pool.take(); }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted waiting for connection", e);
        }
    }
    public void release(Connection c) {
        if (c!=null) pool.offer(c);
    }
}
