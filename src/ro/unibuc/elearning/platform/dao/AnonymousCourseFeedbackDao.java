package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class AnonymousCourseFeedbackDao extends Dao {
    private static AnonymousCourseFeedbackDao anonymousCourseFeedbackDao;

    private AnonymousCourseFeedbackDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS AnonymousCourseFeedback (\n" +
                "id INT,\n" +
                "courseId INT,\n" +
                "feedback INT NOT NULL,\n" +
                "PRIMARY KEY (id,courseId),\n" +
                "FOREIGN KEY (courseId) REFERENCES Course (id))";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static AnonymousCourseFeedbackDao getAnonymousCourseFeedbackDao() {
        if (anonymousCourseFeedbackDao == null)
            anonymousCourseFeedbackDao = new AnonymousCourseFeedbackDao();
        return anonymousCourseFeedbackDao;
    }

}
