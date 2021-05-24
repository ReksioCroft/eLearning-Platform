package ro.unibuc.elearning.platform.dao;

import java.sql.SQLException;
import java.sql.Statement;

public class QuizDao extends Dao {
    private static QuizDao quizDao;

    private QuizDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Quiz (\n" +
                "id INT,\n" +
                "courseId Int,\n" +
                "quiz VARCHAR(1024) Not NULL,\n" +
                "PRIMARY KEY (courseId, id),\n" +
                "FOREIGN KEY (courseId) REFERENCES Course (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static QuizDao getQuizDao() {
        if (quizDao == null)
            quizDao = new QuizDao();
        return quizDao;
    }

}
