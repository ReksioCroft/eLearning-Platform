package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public interface AdminInterface {
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Quiz> quizzes = new ArrayList<>();
    ArrayList<UserCourseRepartition> userCourseRepartitions = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    default Date parseDate(Scanner cin) {
        try {
            String date = cin.next();
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    void addTeacher(Scanner cin);

    void addCourse(Scanner cin);

    void addQuiz(Scanner cin);

    void addStudent(Scanner cin);

    void addUserCourseRepartition(Scanner cin);

    Course findCourse(int courseId);

    Quiz findQuiz(int quizId);

    User findUser(int userId);

    ArrayList<UserCourseRepartition> findStudentCourseRepartitionByUserId(int userId);

    ArrayList<UserCourseRepartition> findUserCourseRepartitionByCourseId(int courseId);
}
