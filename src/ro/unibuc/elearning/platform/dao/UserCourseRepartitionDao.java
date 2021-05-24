package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class UserCourseRepartitionDao extends Dao{
    private static UserCourseRepartitionDao userCourseRepartitionDao;

    private UserCourseRepartitionDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS UserCourseRepartition (\n" +
                "courseId INT,\n" +
                "userId Int,\n" +
                "startDate Date Not NULL,\n" +
                "PRIMARY KEY (courseId, userId, startDate),\n" +
                "FOREIGN KEY (courseId) REFERENCES Course (id),\n" +
                "FOREIGN KEY (userId) REFERENCES User (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static UserCourseRepartitionDao getUserCourseRepartitionDao() {
        if (userCourseRepartitionDao == null)
            userCourseRepartitionDao = new UserCourseRepartitionDao();
        return userCourseRepartitionDao;
    }

}
