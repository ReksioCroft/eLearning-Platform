package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {

    @Override
    public void addTeacher(Scanner cin) {
        System.out.println("name");
        String name = cin.next();
        System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(cin);
        System.out.println("rank");
        String rank = cin.next();
        System.out.println("address");
        String address = cin.next();
        System.out.println("phone");
        String phoneNumber = cin.next();
        User user = new Teacher(name, date, rank, address, phoneNumber);
        users.add(user);
    }

    @Override
    public void addCourse(Scanner cin) {
        System.out.println("id prof");
        int teacherId = cin.nextInt();
        User teacher = findUser(teacherId);
        System.out.println("course name");
        String courseName = cin.next();
        System.out.println("desc");
        String description = cin.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
    }

    @Override
    public void addQuiz(Scanner cin) {
        System.out.println("id curs");
        int courseId = cin.nextInt();
        Course course = findCourse(courseId);
        System.out.println("quiz description");
        String quizContent = cin.next();
        Quiz quiz = new Quiz(course, quizContent);
        quizzes.add(quiz);
    }

    @Override
    public void addStudent(Scanner cin) {
        System.out.println("username");
        String userName = cin.next();
        System.out.println("date yyyy-MM-dd");
        Date birthDate = parseDate(cin);
        System.out.println("address");
        String address = cin.next();
        System.out.println("phone");
        String phoneNumber = cin.next();
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
    }

    @Override
    public void addUserCourseRepartition(Scanner cin) {
        System.out.println("date yyyy-MM-dd");
        Date startDate = parseDate(cin);
        System.out.println("id curs");
        int idCourse = cin.nextInt();
        Course course = findCourse(idCourse);
        System.out.println("id student");
        int idStudent = cin.nextInt();
        Student student = (Student) findUser(idStudent);
        UserCourseRepartition userCourseRepartition = new UserCourseRepartition(startDate, course, student);
        userCourseRepartitions.add(userCourseRepartition);
    }

    @Override
    public Quiz findQuiz(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId)
                return quiz;
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
    public ArrayList<UserCourseRepartition> findStudentCourseRepartitionByUserId(int userId) {
        ArrayList<UserCourseRepartition> ans = new ArrayList<>();
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getUser().getId() == userId)
                ans.add(userCourseRepartition);
        }
        return ans;
    }

    @Override
    public ArrayList<UserCourseRepartition> findUserCourseRepartitionByCourseId(int courseId) {
        ArrayList<UserCourseRepartition> ans = new ArrayList<>();
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getCourse().getId() == courseId)
                ans.add(userCourseRepartition);
        }
        return ans;
    }

}
