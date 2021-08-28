package ro.unibuc.elearning.platform.dao;

import org.jetbrains.annotations.NotNull;
import ro.unibuc.elearning.platform.pojo.Course;
import ro.unibuc.elearning.platform.pojo.Teacher;
import ro.unibuc.elearning.platform.util.AdminInterface;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.*;

public final class CourseDao extends Dao {
    private static CourseDao courseDao;

    private CourseDao() {
        super();
        createTable();
        createUpdateProcedure();
    }

    private void createUpdateProcedure() {
        final String query = "CREATE OR REPLACE PROCEDURE updateCourseDesc (IN id1 INT, IN description1 VARCHAR(1024) ) " +
                "BEGIN " +
                "update Course " +
                "set description=description1 " +
                "where id=id1; " +
                "end";
        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            System.out.println("Exception in CourseDao.java: createUpdateProcedure: " + throwables);
        }

    }

    private void createTable() {
        final String query = "CREATE TABLE IF NOT EXISTS Course (\n" +
                "id INT PRIMARY KEY,\n" +
                "teacherId INT NOT NULL,\n" +
                "courseName VARCHAR(128) NOT NULL,\n" +
                "description VARCHAR(1024) NOT NULL,\n" +
                "FOREIGN KEY (teacherId) REFERENCES Teacher(id))";

        try {
            Statement statement = databaseConnection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            System.out.println("Exception in CourseDao.java: createTable: " + throwables);
        }
    }

    static CourseDao getCourseDao() {
        if (courseDao == null)
            courseDao = new CourseDao();
        return courseDao;
    }

    public void writeCourse(Course course) {
        try {
            final String query = "INSERT INTO Course(id,teacherId, courseName, description) values(?,?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setInt(2, course.getTeacher().getId());
            preparedStatement.setString(3, course.getCourseName());
            preparedStatement.setString(4, course.getDescription());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            System.out.println("Exception in CourseDao.java: writeCourse: " + throwables);
        }
    }

    public void deleteCourse(int courseId) {
        try {
            final String query = "DELETE FROM Course WHERE id=?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, courseId);
            preparedStatement.execute();

            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            AdminInterface.courses.remove(eLearningPlatformService.findCourseById(courseId));
        } catch (SQLException throwables) {
            System.out.println("Exception in CourseDao.java: deleteCourse: " + throwables);
        }
    }

    public void updateCourseDescription(int id, @NotNull String desc) {
        try {
            final String query = "{call updateCourseDesc(?,?)}";
            CallableStatement callableStatement = databaseConnection.prepareCall(query);

            callableStatement.setInt(1, id);
            callableStatement.setString(2, desc);
            callableStatement.executeUpdate();

            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            Course course = eLearningPlatformService.findCourseById(id);
            course.setDescription(desc);
        } catch (SQLException throwables) {
            System.out.println("Exception in CourseDao.java: updateCourseDescription: " + throwables);
        }
    }

    @Override
    public void run() {
        try {
            ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
            final String query = "SELECT id, teacherId, courseName, description FROM Course";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                synchronized (eLearningPlatformService.courses) {
                    eLearningPlatformService.courses.add(mapToCourse(resultSet));
                }
            }
        } catch (SQLException | InterruptedException throwables) {
            System.out.println("Exception in CourseDao.java: run: " + throwables);
        }
    }

    private Course mapToCourse(ResultSet resultSet) throws SQLException, InterruptedException {
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        Teacher teacher = null;
        while (teacher == null) {
            teacher = (Teacher) eLearningPlatformService.findUserById(resultSet.getInt(2));
            if (teacher == null)
                Thread.sleep(500);
        }
        return new Course(resultSet.getInt(1), teacher, resultSet.getString(3), resultSet.getString(4));
    }
}
