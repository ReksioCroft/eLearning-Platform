package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.Student;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class StudentDao extends UserDao {
    private static StudentDao studentDao;

    private StudentDao() {
        super();
        createTable();
    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Student (\n" +
                "id INT PRIMARY KEY,\n" +
                "FOREIGN KEY (id) REFERENCES User (id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    static StudentDao getStudentDao() {
        if (studentDao == null)
            studentDao = new StudentDao();
        return studentDao;
    }

    public void writeStudent(Student student) {
        final String query = "INSERT INTO Student(id) values(?)";
        try {
            writeUser(student);

            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        try {
            final String query = "DELETE FROM Student WHERE id=?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();
            deleteUser(studentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT u.id, u.userName, u.birthDate, u.address, u.phoneNumber FROM User u where u.id in (select id from Student)";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.users) {
                    eLearningPlatformService.users.add(mapToStudent(resultSet));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Student mapToStudent(ResultSet resultSet) throws SQLException {
        return new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5));
    }


}
