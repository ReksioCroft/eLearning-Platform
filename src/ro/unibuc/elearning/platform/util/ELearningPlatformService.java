package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {

    @Override
    public void addUser(Scanner cin) {
        System.out.println("name");
        String name = cin.next();
        System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(cin);
        System.out.println("address");
        String address = cin.next();
        System.out.println("phone");
        String phoneNumber = cin.next();
        User user = new User(name, date, address, phoneNumber);
        users.add(user);
    }

    @Override
    public void addCourse(Scanner cin) {
        System.out.println("id prof");
        int teacherId = cin.nextInt();
        User teacher = findUser(teacherId);
        System.out.println("nume curs");
        String courseName = cin.next();
        System.out.println("desc");
        String description = cin.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
    }

    @Override
    public void addQuizz(Scanner cin) {
        System.out.println("id curs");
        int courseId = cin.nextInt();
        Course course = findCourse(courseId);
        System.out.println("quizz description");
        String quizzContent = cin.next();
        Quizz quizz = new Quizz(course, quizzContent);
        quizzes.add(quizz);
    }

    @Override
    public void addStudent(Scanner cin) {
        System.out.println("username");
        String userName = cin.next();
        System.out.println("date yyyy-MM-dd");
        Date birthDate = parseDate(cin);
        System.out.println("adress");
        String address = cin.next();
        System.out.println("phone");
        String phoneNumber = cin.next();
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
    }

    @Override
    public void addStudentCourseRepartition(Scanner cin) {
        System.out.println("date yyyy-MM-dd");
        Date startDate = parseDate(cin);
        System.out.println("id curs");
        int idCourse = cin.nextInt();
        Course course = findCourse(idCourse);
        System.out.println("id studdent");
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
