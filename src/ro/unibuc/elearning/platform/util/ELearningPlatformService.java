package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {

    @Override
    public Teacher addTeacher(Scanner cin) {
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
        Teacher teacher = new Teacher(name, date, rank, address, phoneNumber);
        users.add(teacher);
        return teacher;
    }

    @Override
    public Course addCourse(Scanner cin) {
        System.out.println("id prof");
        int teacherId = cin.nextInt();
        User teacher = findUserById(teacherId);
        System.out.println("course name");
        String courseName = cin.next();
        System.out.println("desc");
        String description = cin.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
        return course;
    }

    @Override
    public Quiz addQuiz(Scanner cin) {
        System.out.println("id curs");
        int courseId = cin.nextInt();
        Course course = findCourseById(courseId);
        System.out.println("quiz description");
        String quizContent = cin.next();
        Quiz quiz = new Quiz(course, quizContent);
        quizzes.add(quiz);
        return quiz;
    }

    @Override
    public Student addStudent(Scanner cin) {
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
        return student;
    }

    @Override
    public UserCourseRepartition addUserCourseRepartition(Scanner cin) {
        System.out.println("date yyyy-MM-dd");
        Date startDate = parseDate(cin);
        System.out.println("id curs");
        int idCourse = cin.nextInt();
        Course course = findCourseById(idCourse);
        System.out.println("id student");
        int idStudent = cin.nextInt();
        Student student = (Student) findUserById(idStudent);
        UserCourseRepartition userCourseRepartition = new UserCourseRepartition(startDate, course, student);
        userCourseRepartitions.add(userCourseRepartition);
        return userCourseRepartition;
    }

    @Override
    public AnonymousCourseFeedback addFeedback(Scanner in) {
        System.out.println("id curs");
        int courseId = in.nextInt();
        Course course = findCourseById(courseId);
        System.out.println("feedback description");
        String feedbackContent = in.next();
        AnonymousCourseFeedback feedback = new AnonymousCourseFeedback(course, feedbackContent);
        feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public TeachingAssistant addTeachingAssistant(Scanner in) {
        System.out.println("name");
        String name = in.next();
        System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(in);
        System.out.println("teacherId");
        int teacherId = in.nextInt();
        Teacher teacher = (Teacher) findUserById(teacherId);
        System.out.println("address");
        String address = in.next();
        System.out.println("phone");
        String phoneNumber = in.next();
        TeachingAssistant teachingAssistant = new TeachingAssistant(name, date, teacher, address, phoneNumber);
        users.add(teachingAssistant);
        return teachingAssistant;
    }

//    @Override
//    public void addUser(User user) {
//        users.add(user);
//    }
//
//    @Override
//    public void addCourse(Course course) {
//        courses.add(course);
//    }
//
//    @Override
//    public void addQuiz(Quiz quiz) {
//        quizzes.add(quiz);
//    }
//
//    @Override
//    public void addUserCourseRepartition(UserCourseRepartition userCourseRepartition) {
//        userCourseRepartitions.add(userCourseRepartition);
//    }
//
//    @Override
//    public void addAnonymousCourseFeedback(AnonymousCourseFeedback anonymousCourseFeedback) {
//        feedbacks.add(anonymousCourseFeedback);
//    }

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

    public ELearningPlatformService() {
        PersistanceCsvReadService.getInstance();

    }
}
