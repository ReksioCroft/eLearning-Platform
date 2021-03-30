package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {

    @Override
    public void addUser(Scanner cin) {
        String name = cin.next();
        Date date = parseDate(cin);
        String address = cin.next();
        String phoneNumber = cin.next();
        User user = new User(name, date, address, phoneNumber);
        users.add(user);
    }

    @Override
    public void addCourse(Scanner cin) {
        int teacherId = cin.nextInt();
        User teacher = findUser(teacherId);
        String courseName = cin.next();
        String description = cin.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
    }

    @Override
    public void addQuizz(Scanner cin) {
        int courseId = cin.nextInt();
        Course course = findCourse(courseId);
        String quizzContent = cin.next();
        Quizz quizz = new Quizz(course, quizzContent);
        quizzes.add(quizz);
    }

    @Override
    public void addStudent(Scanner cin) {
        String userName = cin.next();
        Date birthDate = parseDate(cin);
        String address = cin.next();
        String phoneNumber = cin.next();
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
    }

    @Override
    public void addStudentCourseRepartition(Scanner cin) {
        Date startDate = parseDate(cin);
        int idCourse = cin.nextInt();
        Course course = findCourse(idCourse);
        int idStudent = cin.nextInt();
        Student student = (Student) findUser(idStudent);
        StudentCourseRepartition studentCourseRepartition = new StudentCourseRepartition(startDate, course, student);
        studentCourseRepartitions.add(studentCourseRepartition);
    }

    @Override
    public Quizz findQuizz(int quizzId) {
        for (Quizz quizz : quizzes) {
            if (quizz.getId() == quizzId)
                return quizz;
        }
        return null;
    }

    @Override
    public Course findCourse(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId)
                return course;
        }
        return null;
    }

    @Override
    public User findUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId)
                return user;
        }
        return null;
    }

    @Override
    public ArrayList<StudentCourseRepartition> findStudentCourseRepartitionByStudent(int studentId) {
        ArrayList<StudentCourseRepartition> ans = new ArrayList<>();
        for (StudentCourseRepartition studentCourseRepartition : studentCourseRepartitions) {
            if (studentCourseRepartition.getStudent().getId() == studentId)
                ans.add(studentCourseRepartition);
        }
        return ans;
    }

    @Override
    public ArrayList<StudentCourseRepartition> findStudentCourseRepartitionByCourse(int courseId) {
        ArrayList<StudentCourseRepartition> ans = new ArrayList<>();
        for (StudentCourseRepartition studentCourseRepartition : studentCourseRepartitions) {
            if (studentCourseRepartition.getCourse().getId() == courseId)
                ans.add(studentCourseRepartition);
        }
        return ans;
    }

}
