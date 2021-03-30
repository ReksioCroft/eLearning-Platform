package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public interface AdminInterface {
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Quizz> quizzes = new ArrayList<>();
    ArrayList<StudentCourseRepartition> studentCourseRepartitions = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    default Date parseDate(Scanner cin) {
        try {
            String date = cin.next();
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    void addUser(Scanner cin);

    void addCourse(Scanner cin);

    void addQuizz(Scanner cin);

    void addStudent(Scanner cin);

    void addStudentCourseRepartition(Scanner cin);

    Course findCourse(int courseId);

    Quizz findQuizz(int quizzId);

    User findUser(int userId);

    ArrayList<StudentCourseRepartition> findStudentCourseRepartitionByStudent(int studentId);

    ArrayList<StudentCourseRepartition> findStudentCourseRepartitionByCourse(int courseId);
}
