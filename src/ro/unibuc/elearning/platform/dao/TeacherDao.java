package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public final class TeacherDao extends Dao {
    private static TeacherDao teacherDao;

    private TeacherDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Teacher (\n" +
                "id INT PRIMARY KEY,\n" +
                "rank VARCHAR(32) NOT NULL,\n" +
                "FOREIGN KEY (id) REFERENCES User (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static TeacherDao getTeacherDao() {
        if (teacherDao == null)
            teacherDao = new TeacherDao();
        return teacherDao;
    }

}
