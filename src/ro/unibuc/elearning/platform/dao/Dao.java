package ro.unibuc.elearning.platform.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {
    protected static Connection databaseConnection;

    Dao() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning", "reksio", "Octav-mysql1!");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
