package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public final class UserDao extends Dao {
    private static UserDao userDao;

    private UserDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS User (\n" +
                "id INT PRIMARY KEY,\n" +
                "userName VARCHAR(64) NOT NULL,\n" +
                "birthDate DATE NOT NULL,\n" +
                "address VARCHAR(128) NOT NULL,\n" +
                "phoneNumber VARCHAR(10) NOT NULL)";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static UserDao getUserDao() {
        if (userDao == null)
            userDao = new UserDao();
        return userDao;
    }

}
