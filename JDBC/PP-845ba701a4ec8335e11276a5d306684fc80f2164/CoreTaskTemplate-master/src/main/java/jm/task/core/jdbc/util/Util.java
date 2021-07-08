package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Util {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306?serverTimezone=Europe/Moscow";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "admin";
    static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    User users;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

    public Util() throws SQLException, ClassNotFoundException {
        Class.forName(DB_DRIVER);
        users = new User();
    }
}

