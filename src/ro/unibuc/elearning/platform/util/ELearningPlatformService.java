package ro.unibuc.elearning.platform.util;

import org.jetbrains.annotations.NotNull;
import ro.unibuc.elearning.platform.dao.Repository;
import ro.unibuc.elearning.platform.pojo.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class ELearningPlatformService implements AdminInterface {
    private final AuditCsvService auditCsvService;
    private final PersistentCsvWriteService persistentCsvWriteService;
    private final Repository repository;
    private static ELearningPlatformService instance = null;

    public static ELearningPlatformService getInstance() {
        if (instance == null) {
            instance = new ELearningPlatformService();
        }
        return instance;
    }

    private ELearningPlatformService() {
        auditCsvService = AuditCsvService.getInstance();
        persistentCsvWriteService = PersistentCsvWriteService.getInstance();
        repository = Repository.getInstance();
    }

    public void readFromCsv(Scanner cin) {
        try {
            System.out.println("Do you want to read from csv too? y/N");
            String ans = cin.nextLine();
            if (ans.strip().toLowerCase().charAt(0) == 'y')
                PersistentCsvReadService.getInstance();
            else
                auditCsvService.writeCsv("No csv read");
        } catch (Exception e) {
            auditCsvService.writeCsv("No csv read because of: " + e);
        }
    }

    @Override
    public Teacher addTeacher(Scanner in) throws RuntimeException {
        System.out.println("teacher name");
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
            throw new IllegalArgumentException("incorrect phone number");
        }

        Teacher teacher = new Teacher(name, date, rank, address, phoneNumber);
        users.add(teacher);
        repository.getTeacherDao().writeTeacher(teacher);
        return persistentCsvWriteService.writeTeacher(teacher);
    }

    @Override
    public Course addCourse(Scanner in) throws RuntimeException {
        System.out.println("id prof");
        int teacherId = in.nextInt();
        User teacher = findUserById(teacherId);
        System.out.println("course name");
        String courseName = in.next();
        System.out.println("desc");
        String description = in.next();
        Course course = new Course(teacher, courseName, description);
        courses.add(course);
        repository.getCourseDao().writeCourse(course);
        return persistentCsvWriteService.writeCourse(course);
    }

    @Override
    public Quiz addQuiz(Scanner in) throws RuntimeException {
        System.out.println("id curs");
        int courseId = in.nextInt();
        Course course = findCourseById(courseId);
        System.out.println("quiz description");
        String quizContent = in.next();
        Quiz quiz = new Quiz(course, quizContent);
        quizzes.add(quiz);
        repository.getQuizDao().writeQuiz(quiz);
        return persistentCsvWriteService.writeQuiz(quiz);
    }

    @Override
    public Student addStudent(Scanner in) throws RuntimeException {
        System.out.println("username");
        String userName = in.next();

        System.out.println("date yyyy-MM-dd");
        Date birthDate = parseDate(in);

        System.out.println("address");
        String address = in.next();
        System.out.println("phone");
        String phoneNumber = in.next();
        if (!phoneNumber.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("incorrect phone number");
        }
        Student student = new Student(userName, birthDate, address, phoneNumber);
        users.add(student);
        repository.getStudentDao().writeStudent(student);
        return persistentCsvWriteService.writeStudent(student);
    }

    @Override
    public UserCourseRepartition addUserCourseRepartition(Scanner in) throws RuntimeException {
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
        repository.getUserCourseRepartitionDao().writeUserCourseRepartition(userCourseRepartition);
        return persistentCsvWriteService.writeUserCourseRepartition(userCourseRepartition);
    }

    @Override
    public AnonymousCourseFeedback addFeedback(Scanner in) throws RuntimeException {
        System.out.println("id curs");
        int courseId = in.nextInt();
        Course course = findCourseById(courseId);
        System.out.println("feedback description");
        String feedbackContent = in.next();
        AnonymousCourseFeedback feedback = new AnonymousCourseFeedback(course, feedbackContent);
        feedbacks.add(feedback);
        repository.getAnonymousCourseFeedbackDao().writeAnonymousCourseFeedback(feedback);
        return persistentCsvWriteService.writeFeedback(feedback);
    }

    @Override
    public TeachingAssistant addTeachingAssistant(Scanner in) throws RuntimeException {
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
            throw new IllegalArgumentException("incorrect phone number");
        }
        TeachingAssistant teachingAssistant = new TeachingAssistant(name, date, teacher, address, phoneNumber);
        users.add(teachingAssistant);
        repository.getTeachingAssistantDao().writeTeachingAssistant(teachingAssistant);
        return persistentCsvWriteService.writeTeachingAssistant(teachingAssistant);
    }

    public static @NotNull Quiz findQuizById(int quizId) throws NullPointerException {
        for (Quiz quiz : quizzes) {
            if (quiz.getId() == quizId)
                return quiz;
        }
        throw new NullPointerException("Exception in ELearningPlatformService.java: findQuizById: no quiz found");
    }

    public static Course findCourseById(int courseId) throws NullPointerException {
        for (Course course : courses) {
            if (course.getId() == courseId)
                return course;
        }
        throw new NullPointerException("Exception in ELearningPlatformService.java: findCourseById: no course found");
    }

    public static User findUserById(int userId) throws NullPointerException {
        for (User user : users) {
            if (user.getId() == userId)
                return user;
        }
        throw new NullPointerException("Exception in ELearningPlatformService.java: findUserById: no user found");
    }

    public static TreeSet<UserCourseRepartition> findSpecificStudentCourseRepartitionsByStudentId(int userId) {
        TreeSet<UserCourseRepartition> repartitions =
                new TreeSet<>((userCourseRepartition, t1) -> userCourseRepartition.getStartDate().compareTo(t1.getStartDate()));
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getUser().getId() == userId)
                repartitions.add(userCourseRepartition);
        }
        return repartitions;
    }

    public static TreeSet<UserCourseRepartition> findUserCourseRepartitionByCourseId(int courseId) {
        TreeSet<UserCourseRepartition> repartitions =
                new TreeSet<>((userCourseRepartition, t1) -> userCourseRepartition.getStartDate().compareTo(t1.getStartDate()));
        for (UserCourseRepartition userCourseRepartition : userCourseRepartitions) {
            if (userCourseRepartition.getCourse().getId() == courseId)
                repartitions.add(userCourseRepartition);
        }
        return repartitions;
    }

    public static ArrayList<AnonymousCourseFeedback> findFeedbacksByCourseId(int courseId) {
        ArrayList<AnonymousCourseFeedback> ans = new ArrayList<>();
        for (AnonymousCourseFeedback feedback : feedbacks) {
            if (feedback.getCourse().getId() == courseId)
                ans.add(feedback);
        }
        return ans;
    }
}
