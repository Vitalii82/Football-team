package com.football.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public final class MyBatisUtil {
    private static final MyBatisUtil INSTANCE = new MyBatisUtil();
    private final SqlSessionFactory factory;

    private MyBatisUtil() {
        try (Reader reader = Resources.getResourceAsReader("mybatis/mybatis-config.xml")) {
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            throw new RuntimeException("Failed to init MyBatis factory", e);
        }
    }

    public static MyBatisUtil getInstance() { return INSTANCE; }
    public SqlSessionFactory factory() { return factory; }
}
