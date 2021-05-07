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
    ArrayList<AnonymousCourseFeedback> feedbacks = new ArrayList<>();

    default Date parseDate(Scanner cin) {
        try {
            String date = cin.next();
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    default void clearALL() {
        courses.clear();
        quizzes.clear();
        userCourseRepartitions.clear();
        users.clear();
        feedbacks.clear();
    }

    Teacher addTeacher(Scanner in);

    Course addCourse(Scanner in);

    Quiz addQuiz(Scanner in);

    Student addStudent(Scanner in);

    UserCourseRepartition addUserCourseRepartition(Scanner in);

    AnonymousCourseFeedback addFeedback(Scanner in);

    TeachingAssistant addTeachingAssistant(Scanner in);

    Course findCourseById(int courseId);

    Quiz findQuizById(int quizId);

    User findUserById(int userId);

    ArrayList<UserCourseRepartition> findSpecificStudentCourseRepartitionsByStudentId(int userId);

    ArrayList<UserCourseRepartition> findUserCourseRepartitionByCourseId(int courseId);
}
