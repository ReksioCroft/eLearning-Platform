package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class UserDao extends Dao {
    UserDao() {
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

    void writeUser(User user) {
        final String query = "INSERT INTO User(id,userName,birthDate,address,phoneNumber) values (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    void deleteUser(int userId) {
        try {
            final String query = "DELETE FROM User WHERE ID = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
