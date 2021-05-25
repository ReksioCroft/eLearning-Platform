package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.AdminInterface;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class UserCourseRepartitionDao extends Dao {
    private static UserCourseRepartitionDao userCourseRepartitionDao;

    private UserCourseRepartitionDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS UserCourseRepartition (\n" +
                "userId Int,\n" +
                "courseId INT,\n" +
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

    public void writeUserCourseRepartition(UserCourseRepartition userCourseRepartition) {
        try {
            final String query = "INSERT INTO UserCourseRepartition(userId, courseId, startDate) values(?,?,?)";

            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, userCourseRepartition.getUser().getId());
            preparedStatement1.setInt(2, userCourseRepartition.getCourse().getId());
            preparedStatement1.setDate(3, userCourseRepartition.getStartDate());
            preparedStatement1.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUserCourseRepartition(UserCourseRepartition userCourseRepartition) {
        try {
            final String query = "DELETE FROM UserCourseRepartition where userId=? and courseId=? and startDate=?";
            PreparedStatement preparedStatement1 = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement1.setInt(1, userCourseRepartition.getUser().getId());
            preparedStatement1.setInt(2, userCourseRepartition.getCourse().getId());
            preparedStatement1.setDate(3, userCourseRepartition.getStartDate());
            preparedStatement1.execute();
            AdminInterface.userCourseRepartitions.remove(userCourseRepartition);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT userId, courseId, startDate FROM UserCourseRepartition";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.userCourseRepartitions) {
                    eLearningPlatformService.userCourseRepartitions.add(mapToUserCourseRepartition(resultSet));
                }
            }
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }

    private UserCourseRepartition mapToUserCourseRepartition(ResultSet resultSet) throws SQLException, InterruptedException {
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        User user = null;
        while (user == null) {
            user = eLearningPlatformService.findUserById(resultSet.getInt(1));
            if (user == null)
                Thread.sleep(500);
        }
        Course course = null;
        while (course == null) {
            course = eLearningPlatformService.findCourseById(resultSet.getInt(2));
            if (course == null)
                Thread.sleep(500);
        }
        return new UserCourseRepartition(resultSet.getDate(3), course, user);
    }
}
