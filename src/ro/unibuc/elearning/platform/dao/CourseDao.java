package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.pojo.Course;
import ro.unibuc.elearning.platform.pojo.Teacher;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class CourseDao extends Dao {
    private static CourseDao courseDao;

    private CourseDao() {
        super();
        createTable();
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
            throwables.printStackTrace();
        }
    }

    static CourseDao getCourseDao() {
        if (courseDao == null)
            courseDao = new CourseDao();
        return courseDao;
    }

    public void writeCourse(Course course) {
        final String query = "INSERT INTO Course(id,teacherId, courseName, description) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setInt(2, course.getTeacher().getId());
            preparedStatement.setString(3, course.getCourseName());
            preparedStatement.setString(4, course.getDescription());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
            throwables.printStackTrace();
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
