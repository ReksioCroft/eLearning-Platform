package ro.unibuc.elearning.platform.dao;

import java.sql.*;

public abstract class Dao extends Thread {
    protected static Connection databaseConnection;

    Dao() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                createDatabase();
                databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning", "root", "root");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
            final String query = "CREATE DATABASE IF NOT EXISTS elearning";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
