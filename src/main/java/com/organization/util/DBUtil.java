package com.organization.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
        "jdbc:mysql://localhost:3306/grievance_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "praveena";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.err.println("Database Connection Failed: " + e.getMessage());
            System.err.println("URL: " + URL);
            System.err.println("User: " + USER);
            e.printStackTrace();
            return null;
        }
    }
}
