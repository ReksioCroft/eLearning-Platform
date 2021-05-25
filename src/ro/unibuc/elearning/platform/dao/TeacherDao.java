package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.Teacher;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class TeacherDao extends UserDao {
    private static TeacherDao teacherDao;

    private TeacherDao() {
        super();
        createTable();
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

    static TeacherDao getTeacherDao() {
        if (teacherDao == null)
            teacherDao = new TeacherDao();
        return teacherDao;
    }


    public void writeTeacher(Teacher teacher) {
        final String query = "INSERT INTO Teacher(id,ranking) values(?,?)";
        try {
            writeUser(teacher);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getRank());
            preparedStatement.execute();
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
