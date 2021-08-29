package ro.unibuc.elearning.platform.util;

import org.jetbrains.annotations.NotNull;
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

    default @NotNull Date parseDate(Scanner cin) throws RuntimeException {
        String date = cin.next();
        try {
            return parseDate(date, "yyyy-MM-dd");
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static @NotNull Date parseDate(String date, String pattern) throws ParseException {
        return new Date(new SimpleDateFormat(pattern).parse(date).getTime());
    }

    static void clearALL() {
        courses.clear();
        quizzes.clear();
        userCourseRepartitions.clear();
        users.clear();
        feedbacks.clear();
    }

    Teacher addTeacher(Scanner in) throws RuntimeException;

    Course addCourse(Scanner in) throws RuntimeException;

    Quiz addQuiz(Scanner in) throws RuntimeException;

    Student addStudent(Scanner in) throws RuntimeException;

    UserCourseRepartition addUserCourseRepartition(Scanner in) throws RuntimeException;

    AnonymousCourseFeedback addFeedback(Scanner in) throws RuntimeException;

    TeachingAssistant addTeachingAssistant(Scanner in) throws RuntimeException;
}
