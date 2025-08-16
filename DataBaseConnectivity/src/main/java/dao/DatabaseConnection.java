package main.java.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try (InputStream inputStream = DatabaseConnection.class
                .getClassLoader().getResourceAsStream("database.properties")) {
            if (inputStream == null) {
                throw new IOException("database.properties not found in classpath");
            }
            Properties prop = new Properties();
            prop.load(inputStream);

            String url = prop.getProperty("db.url");
            String password = prop.getProperty("db.password");
            String user = prop.getProperty("db.username");

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
