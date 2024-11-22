package com.busanit501.helloworld.jdbcex;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void test() throws SQLException {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
    hikariConfig.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
    hikariConfig.setUsername("webuser");
    hikariConfig.setPassword("webuser");
    // 캐시 기능 이용해서 메모리 상에서 임시 저장에서, 꺼내서 사용한다.
        // 250길이로 최대 2048 까지 기억하겠다.
    hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
    Connection connection = hikariDataSource.getConnection();
    System.out.println(connection);
    connection.close();
    }
}
