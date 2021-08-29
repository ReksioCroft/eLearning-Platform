package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.AdminInterface;
import ro.unibuc.elearning.platform.util.Dao;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class UserCourseRepartitionDao extends Dao {
    private static UserCourseRepartitionDao userCourseRepartitionDao;

    private UserCourseRepartitionDao() {
        super();
    }

    @Override
    protected void createTable() {
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
            auditCsvService.writeCsv("Exception in UserCourseRepartitionDao.java: createTable: " + throwables);
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
            auditCsvService.writeCsv("Exception in UserCourseRepartitionDao.java: writeUserCourseRepartition: " + throwables);
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
            auditCsvService.writeCsv("Exception in UserCourseRepartitionDao.java: deleteUserCourseRepartition: " + throwables);
        }
    }

    @Override
    public void run() {
        try {
            final String query = "SELECT userId, courseId, startDate FROM UserCourseRepartition";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (AdminInterface.userCourseRepartitions) {
                    AdminInterface.userCourseRepartitions.add(mapToUserCourseRepartition(resultSet));
                }
            }
        } catch (SQLException | InterruptedException throwables) {
            auditCsvService.writeCsv("Exception in UserCourseRepartitionDao.java: run: " + throwables);
        }
    }

    private UserCourseRepartition mapToUserCourseRepartition(ResultSet resultSet) throws SQLException, InterruptedException {
        User user = null;
        while (user == null) {
            try {
                user = ELearningPlatformService.findUserById(resultSet.getInt(1));
            } catch (NullPointerException e) {
                Thread.sleep(500);
            }
        }
        Course course = null;
        while (course == null) {
            try {
                course = ELearningPlatformService.findCourseById(resultSet.getInt(2));
            } catch (NullPointerException e) {
                Thread.sleep(500);
            }
        }
        return new UserCourseRepartition(resultSet.getDate(3), course, user);
    }
}
