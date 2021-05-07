package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ELearningPlatformService implements AdminInterface {
    private final PersistentCsvWriteService persistentCsvWriteService;

    public ELearningPlatformService() {
        persistentCsvWriteService = PersistentCsvWriteService.getInstance();
    }

    @Override
    public Teacher addTeacher(Scanner in) throws Exception {
        System.out.println("name");
        String name = in.next();

        System.out.println("Date yyyy-MM-dd");
        Date date = parseDate(in);

        System.out.println("rank");
        String rank = in.next();

        System.out.println("address");
        String address = in.next();

        System.out.println("phone");
        String phoneNumber = in.next();
        if (!phoneNumber.matches("[0-9]{10}")) {
            throw new Exception("incorrect phone number");
        }

        Teacher teacher = new Teacher(name, date, rank, address, phoneNumber);
        users.add(teacher);
        return persistentCsvWriteService.writeTeacher(teacher);
    }

    @Override
    public Course addCourse(Scanner in) {
        System.out.println("id prof");
        int teacherId = in.nextInt();
        User teacher = findUserById(teacherId);
        System.out.println("course name");
        String courseName = in.next();
        System.out.println("desc");
        String description = in.nextLine();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
        return persistentCsvWriteService.writeCourse(course);
    }

    @Override
    public Quiz addQuiz(Scanner in) {
        System.out.println("id curs");
        int courseId = in.nextInt();
        Course course = findCourseById(courseId);
        System.out.println("quiz description");
        String quizContent = in.next();
        Quiz quiz = new Quiz(course, quizContent);
        quizzes.add(quiz);
        return persistentCsvWriteService.writeQuiz(quiz);
    }

    @Override
    public Student addStudent(Scanner in) throws Exception {
        System.out.println("username");
        String userName = in.next();
        System.out.println("date yyyy-MM-dd");
        Date birthDate = parseDate(in);
        System.out.println("address");
        String address = in.next();
        System.out.println("phone");
        String phoneNumber = in.next();
        if (!phoneNumber.matches("[0-9]{10}")) {
            throw new Exception("incorrect phone number");
        }
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
        return persistentCsvWriteService.writeStudent(student);
    }

    @Override
    public UserCourseRepartition addUserCourseRepartition(Scanner in) {
        System.out.println("id curs");
        int idCourse = in.nextInt();
        Course course = findCourseById(idCourse);

        System.out.println("date yyyy-MM-dd");
        Date startDate = parseDate(in);

        System.out.println("id student");
        int idStudent = in.nextInt();
        Student student = (Student) findUserById(idStudent);
        UserCourseRepartition userCourseRepartition = new UserCourseRepartition(startDate, course, student);
        userCourseRepartitions.add(userCourseRepartition);
        return persistentCsvWriteService.writeUserCourseRepartition(userCourseRepartition);
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
        return persistentCsvWriteService.writeFeedback(feedback);
    }

    @Override
    public TeachingAssistant addTeachingAssistant(Scanner in) throws Exception {
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
        if (!phoneNumber.matches("[0-9]{10}")) {
            throw new Exception("incorrect phone number");
        }
        TeachingAssistant teachingAssistant = new TeachingAssistant(name, date, teacher, address, phoneNumber);
        users.add(teachingAssistant);
        return persistentCsvWriteService.writeTeachingAssistant(teachingAssistant);
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

    @Override
    public ArrayList<AnonymousCourseFeedback> findFeedbacksByCourseId(int courseId) {
        ArrayList<AnonymousCourseFeedback> ans = new ArrayList<>();
        for (AnonymousCourseFeedback feedback : feedbacks) {
            if (feedback.getCourse().getId() == courseId)
                ans.add(feedback);
        }
        return ans;
    }
}
