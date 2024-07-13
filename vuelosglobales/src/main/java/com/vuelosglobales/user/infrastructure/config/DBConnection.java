package com.vuelosglobales.user.infrastructure.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://monorail.proxy.rlwy.net:57569/railway";
    private static final String USER = "root";
    private static final String PASSWORD = "PMtXOQvFRxOyitxgthfXvmSNQVhMXklK";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
