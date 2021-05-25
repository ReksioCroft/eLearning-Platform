package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.Course;
import ro.unibuc.elearning.platform.pojo.Quiz;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class QuizDao extends Dao {
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

    public void writeQuiz(Quiz quiz) {
        try {
            final String query = "INSERT INTO Quiz(id, courseId, quiz) values(?,?,?)";

            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, quiz.getId());
            preparedStatement1.setInt(2, quiz.getCourse().getId());
            preparedStatement1.setString(3, quiz.getQuiz());
            preparedStatement1.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteQuiz(Quiz quiz) {
        try {
            final String query = "DELETE FROM Quiz WHERE id=? and courseId=?";
            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, quiz.getId());
            preparedStatement1.setInt(2, quiz.getCourse().getId());
            preparedStatement1.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT id, courseId, quiz FROM Quiz";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.quizzes) {
                    eLearningPlatformService.quizzes.add(mapToQuiz(resultSet));
                }
            }
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }

    private Quiz mapToQuiz(ResultSet resultSet) throws SQLException, InterruptedException {
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        Course course = null;
        while (course == null) {
            course = eLearningPlatformService.findCourseById(resultSet.getInt(2));
            if (course == null)
                Thread.sleep(500);
        }
        return new Quiz(resultSet.getInt(1), course, resultSet.getString(3));
    }
}
