package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public final class CourseDao extends Dao {
    private static CourseDao courseDao;

    private CourseDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Course (\n" +
                "id INT PRIMARY KEY,\n" +
                "teacherId INT NOT NULL,\n" +
                "courseName VARCHAR(128) NOT NULL,\n" +
                "description VARCHAR(1024) NOT NULL,\n" +
                "FOREIGN KEY (teacherId) REFERENCES Teacher(id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static CourseDao getCourseDao() {
        if (courseDao == null)
            courseDao = new CourseDao();
        return courseDao;
    }
}
