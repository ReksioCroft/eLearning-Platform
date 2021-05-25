package ro.unibuc.elearning.platform.dao;

import org.jetbrains.annotations.NotNull;
import ro.unibuc.elearning.platform.pojo.Teacher;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.*;

public final class TeacherDao extends UserDao {
    private static TeacherDao teacherDao;

    private TeacherDao() {
        super();
        createTable();
        createUpdateProcedure();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Teacher (\n" +
                "id INT PRIMARY KEY,\n" +
                "ranking VARCHAR(32) NOT NULL,\n" +
                "FOREIGN KEY (id) REFERENCES User (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createUpdateProcedure() {
        final String query = "CREATE OR REPLACE PROCEDURE updateTeacherRanking (IN id1 INT, IN ranking1 VARCHAR(1024) ) " +
                "BEGIN " +
                "update Teacher " +
                "set ranking=ranking1 " +
                "where id=id1; " +
                "end";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateTeacherRanking(int teacherId, @NotNull String ranking) {
        try {
            final String query = "{call updateTeacherRanking(?,?)}";
            CallableStatement callableStatement = databaseConnection.prepareCall(query);

            callableStatement.setInt(1, teacherId);
            callableStatement.setString(2, ranking);
            callableStatement.executeUpdate();

            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            Teacher teacher = (Teacher) eLearningPlatformService.findUserById(teacherId);
            teacher.setRank(ranking);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static TeacherDao getTeacherDao() {
        if (teacherDao == null)
            teacherDao = new TeacherDao();
        return teacherDao;
    }


    public void writeTeacher(Teacher teacher) {
        try {
            final String query = "INSERT INTO Teacher(id,ranking) values(?,?)";
            writeUser(teacher);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getRank());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTeacher(int teacherId) {
        try {
            final String query = "DELETE FROM Teacher WHERE id=?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teacherId);
            preparedStatement.execute();
            deleteUser(teacherId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT t.id, u.userName, u.birthDate, t.ranking, u.address, u.phoneNumber FROM User u, Teacher t where u.id=t.id";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.users) {
                    eLearningPlatformService.users.add(mapToTeacher(resultSet));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Teacher mapToTeacher(ResultSet resultSet) throws SQLException {
        return new Teacher(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
    }


}
