package com.football.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public final class MyBatisUtil {
    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    static {
        try (Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml")) {
            SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            throw new RuntimeException("Failed to init MyBatis SqlSessionFactory", e);
        }
    }

    private MyBatisUtil() {}

    public static SqlSessionFactory getFactory() {
        return SQL_SESSION_FACTORY;
    }
}
