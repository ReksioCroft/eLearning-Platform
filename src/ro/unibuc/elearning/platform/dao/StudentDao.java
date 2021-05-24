package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public final class StudentDao extends Dao {
    private static StudentDao studentDao;

    private StudentDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Student (\n" +
                "id INT PRIMARY KEY,\n" +
                "FOREIGN KEY (id) REFERENCES User (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static StudentDao getStudentDao() {
        if (studentDao == null)
            studentDao = new StudentDao();
        return studentDao;
    }

}
