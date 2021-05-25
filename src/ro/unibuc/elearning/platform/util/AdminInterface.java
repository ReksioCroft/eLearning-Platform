package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public interface AdminInterface {
    List<Course> courses = Collections.synchronizedList(new ArrayList<>());
    List<Quiz> quizzes = Collections.synchronizedList(new ArrayList<>());
    List<UserCourseRepartition> userCourseRepartitions = Collections.synchronizedList(new ArrayList<>());
    List<User> users = Collections.synchronizedList(new ArrayList<>());
    List<AnonymousCourseFeedback> feedbacks = Collections.synchronizedList(new ArrayList<>());

    default Date parseDate(Scanner cin) {
        try {
            String date = cin.next();
            return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    default Date parseDate(String date) {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
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

    Teacher addTeacher(Scanner in) throws Exception;

    Course addCourse(Scanner in);

    Quiz addQuiz(Scanner in);

    Student addStudent(Scanner in) throws Exception;

    UserCourseRepartition addUserCourseRepartition(Scanner in);

    AnonymousCourseFeedback addFeedback(Scanner in);

    TeachingAssistant addTeachingAssistant(Scanner in) throws Exception;

    Course findCourseById(int courseId);

    Quiz findQuizById(int quizId);

    User findUserById(int userId);

    TreeMap<Integer,UserCourseRepartition> findSpecificStudentCourseRepartitionsByStudentId(int userId);

    TreeMap<Integer,UserCourseRepartition> findUserCourseRepartitionByCourseId(int courseId);

    ArrayList<AnonymousCourseFeedback> findFeedbacksByCourseId(int courseId);
}
