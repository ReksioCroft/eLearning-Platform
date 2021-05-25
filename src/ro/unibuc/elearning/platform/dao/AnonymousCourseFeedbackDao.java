package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.AnonymousCourseFeedback;
import ro.unibuc.elearning.platform.pojo.Course;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class AnonymousCourseFeedbackDao extends Dao {
    private static AnonymousCourseFeedbackDao anonymousCourseFeedbackDao;

    private AnonymousCourseFeedbackDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS AnonymousCourseFeedback (\n" +
                "id INT,\n" +
                "courseId INT,\n" +
                "feedback VARCHAR(1024) NOT NULL,\n" +
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

    public void writeAnonymousCourseFeedback(AnonymousCourseFeedback anonymousCourseFeedback) {
        try {
            final String query = "INSERT INTO AnonymousCourseFeedback(id, courseId, feedback) values(?,?,?)";

            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, anonymousCourseFeedback.getId());
            preparedStatement1.setInt(2, anonymousCourseFeedback.getCourse().getId());
            preparedStatement1.setString(3, anonymousCourseFeedback.getFeedback());
            preparedStatement1.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT id, courseId, feedback FROM AnonymousCourseFeedback";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.feedbacks) {
                    eLearningPlatformService.feedbacks.add(mapToAnonymousCourseFeedback(resultSet));
                }
            }
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }

    private AnonymousCourseFeedback mapToAnonymousCourseFeedback(ResultSet resultSet) throws SQLException, InterruptedException {
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        Course course = null;
        while (course == null) {
            course = eLearningPlatformService.findCourseById(resultSet.getInt(2));
            if (course == null)
                Thread.sleep(500);
        }
        return new AnonymousCourseFeedback(resultSet.getInt(1), course, resultSet.getString(3));
    }
}
