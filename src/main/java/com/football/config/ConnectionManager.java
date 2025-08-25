package com.football.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class ConnectionManager {
    private static final String PROPS = "db.properties";
    private static String URL, USER, PASS;

    static {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPS)) {
            Properties p = new Properties();
            p.load(in);
            URL  = p.getProperty("jdbc.url");
            USER = p.getProperty("jdbc.user");
            PASS = p.getProperty("jdbc.password");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Failed to init DB config", e);
        }
    }

    private ConnectionManager() {}

    public static Connection getConnection() {
        try { return DriverManager.getConnection(URL, USER, PASS); }
        catch (Exception e) { throw new RuntimeException("Cannot obtain DB connection", e); }
    }
}
