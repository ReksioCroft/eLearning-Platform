package ro.unibuc.elearning.platform.dao;

import java.sql.*;

abstract class Dao extends Thread {
    protected static Connection databaseConnection;

    Dao() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                createDatabase();
                databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning", "root", "root");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Exception in Dao.java: " + throwables);
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
            System.out.println("Exception in Dao.java: createDatabase(create): " + throwables);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                System.out.println("Exception in Dao.java: createDatabase(close): " + throwables);
            }
        }
    }
}
