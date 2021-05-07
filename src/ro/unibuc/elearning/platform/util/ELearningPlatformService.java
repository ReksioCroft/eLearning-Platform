package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {
    private final PersistentCsvWriteService persistentCsvWriteService;
    private boolean debug;

    public ELearningPlatformService() {
        persistentCsvWriteService = PersistentCsvWriteService.getInstance();
        debug = false;
    }

    public void setDebug(boolean opt) {
        debug = opt;
    }

    @Override
    public Teacher addTeacher(Scanner cin) {
        if (debug)
            System.out.println("name");
        String name = cin.next();
        if (debug)
            System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(cin);
        if (debug)
            System.out.println("rank");
        String rank = cin.next();
        if (debug)
            System.out.println("address");
        String address = cin.next();
        if (debug)
            System.out.println("phone");
        String phoneNumber = cin.next();
        Teacher teacher = new Teacher(name, date, rank, address, phoneNumber);
        users.add(teacher);
        if (debug)
            return persistentCsvWriteService.writeTeacher(teacher);
        else return teacher;
    }

    @Override
    public Course addCourse(Scanner cin) {
        if (debug)
            System.out.println("id prof");
        int teacherId = cin.nextInt();
        User teacher = findUserById(teacherId);
        if (debug)
            System.out.println("course name");
        String courseName = cin.next();
        if (debug)
            System.out.println("desc");
        String description = cin.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
        if (debug)
            return persistentCsvWriteService.writeCourse(course);
        else return course;
    }

    @Override
    public Quiz addQuiz(Scanner cin) {
        if (debug)
            System.out.println("id curs");
        int courseId = cin.nextInt();
        Course course = findCourseById(courseId);
        if (debug)
            System.out.println("quiz description");
        String quizContent = cin.next();
        Quiz quiz = new Quiz(course, quizContent);
        quizzes.add(quiz);
        if (debug)
            return persistentCsvWriteService.writeQuiz(quiz);
        else return quiz;
    }

    @Override
    public Student addStudent(Scanner cin) {
        if (debug)
            System.out.println("username");
        String userName = cin.next();
        if (debug)
            System.out.println("date yyyy-MM-dd");
        Date birthDate = parseDate(cin);
        if (debug)
            System.out.println("address");
        String address = cin.next();
        if (debug)
            System.out.println("phone");
        String phoneNumber = cin.next();
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
        if (debug)
            return persistentCsvWriteService.writeStudent(student);
        else return student;
    }

    @Override
    public UserCourseRepartition addUserCourseRepartition(Scanner cin) {
        if (debug)
            System.out.println("date yyyy-MM-dd");
        Date startDate = parseDate(cin);
        if (debug)
            System.out.println("id curs");
        int idCourse = cin.nextInt();
        Course course = findCourseById(idCourse);
        if (debug)
            System.out.println("id student");
        int idStudent = cin.nextInt();
        Student student = (Student) findUserById(idStudent);
        UserCourseRepartition userCourseRepartition = new UserCourseRepartition(startDate, course, student);
        userCourseRepartitions.add(userCourseRepartition);
        if (debug)
            return persistentCsvWriteService.writeUserCourseRepartition(userCourseRepartition);
        else return userCourseRepartition;
    }

    @Override
    public AnonymousCourseFeedback addFeedback(Scanner in) {
        if (debug)
            System.out.println("id curs");
        int courseId = in.nextInt();
        Course course = findCourseById(courseId);
        if (debug)
            System.out.println("feedback description");
        String feedbackContent = in.next();
        AnonymousCourseFeedback feedback = new AnonymousCourseFeedback(course, feedbackContent);
        feedbacks.add(feedback);
        if (debug)
            return persistentCsvWriteService.writeFeedback(feedback);
        else return feedback;
    }

    @Override
    public TeachingAssistant addTeachingAssistant(Scanner in) {
        if (debug)
            System.out.println("name");
        String name = in.next();
        if (debug)
            System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(in);
        if (debug)
            System.out.println("teacherId");
        int teacherId = in.nextInt();
        Teacher teacher = (Teacher) findUserById(teacherId);
        if (debug)
            System.out.println("address");
        String address = in.next();
        if (debug)
            System.out.println("phone");
        String phoneNumber = in.next();
        TeachingAssistant teachingAssistant = new TeachingAssistant(name, date, teacher, address, phoneNumber);
        users.add(teachingAssistant);
        if (debug)
            return persistentCsvWriteService.writeTeachingAssistant(teachingAssistant);
        else return teachingAssistant;
    }

    @Override
    public Quiz findQuizById(int quizId) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId)
                return quiz;
        }
        return null;
    }

    @Override
    public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId)
                return course;
        }
        return null;
    }

    @Override
    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId)
                return user;
        }
        return null;
    }

    @Override
    public ArrayList<UserCourseRepartition> findSpecificStudentCourseRepartitionsByStudentId(int userId) {
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
