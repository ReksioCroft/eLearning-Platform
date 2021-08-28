package ro.unibuc.elearning.platform.dao;

import org.jetbrains.annotations.NotNull;
import ro.unibuc.elearning.platform.pojo.Course;
import ro.unibuc.elearning.platform.pojo.Quiz;
import ro.unibuc.elearning.platform.util.AdminInterface;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.*;

public final class QuizDao extends Dao {
    private static QuizDao quizDao;

    private QuizDao() {
        super();
        createTable();
        createUpdateProcedure();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Quiz (\n" +
                "id INT PRIMARY KEY ,\n" +
                "courseId Int,\n" +
                "quiz VARCHAR(1024) Not NULL,\n" +
                "FOREIGN KEY (courseId) REFERENCES Course (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            System.out.println("Exception in QuizDao.java: createTable: " + throwables);
        }
    }

    private void createUpdateProcedure() {
        final String query = "CREATE OR REPLACE PROCEDURE updateQuizContent (IN id1 INT, IN quiz1 VARCHAR(1024) ) " +
                "BEGIN " +
                "update Quiz " +
                "set quiz=quiz1 " +
                "where id=id1; " +
                "end";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            System.out.println("Exception in QuizDao.java: createUpdateProcedure: " + throwables);
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
            System.out.println("Exception in QuizDao.java: writeQuiz: " + throwables);
        }
    }

    public void deleteQuiz(Quiz quiz) {
        try {
            final String query = "DELETE FROM Quiz WHERE id=? and courseId=?";
            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, quiz.getId());
            preparedStatement1.setInt(2, quiz.getCourse().getId());
            preparedStatement1.execute();
            AdminInterface.quizzes.remove(quiz);
        } catch (SQLException throwables) {
            System.out.println("Exception in QuizDao.java: deleteQuiz: " + throwables);
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
            System.out.println("Exception in QuizDao.java: run: " + throwables);
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

    public void updateQuizContent(int quizId, @NotNull String content) {
        try {
            final String query = "{call updateQuizContent(?,?)}";
            CallableStatement callableStatement = databaseConnection.prepareCall(query);

            callableStatement.setInt(1, quizId);
            callableStatement.setString(2, content);
            callableStatement.executeUpdate();

            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            Quiz quiz = eLearningPlatformService.findQuizById(quizId);
            quiz.setQuiz(content);
        } catch (SQLException throwables) {
            System.out.println("Exception in QuizDao.java: updateQuizContent: " + throwables);
        }
    }
}
