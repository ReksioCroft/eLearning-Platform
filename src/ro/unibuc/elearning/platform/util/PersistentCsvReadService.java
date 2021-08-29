package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.dao.Repository;
import ro.unibuc.elearning.platform.pojo.*;

import java.io.FileInputStream;
import java.sql.Date;
import java.util.Scanner;

public class PersistentCsvReadService {
    private final AuditCsvService auditCsvService;
    private static PersistentCsvReadService instance = null;
    private final Repository repository;

    private PersistentCsvReadService() {
        auditCsvService = AuditCsvService.getInstance();
        repository = Repository.getInstance();
        auditCsvService.writeCsv("Loading csv files in memory");
        readTeachers();
        readTeachingAssistants();
        readStudents();
        readCourses();
        readUserCourseRepartition();
        readQuizzes();
        readFeedbacks();
    }

    private void readFeedbacks() {
        try {
            Scanner fin = new Scanner(new FileInputStream("feedbacks.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                int courseId = Integer.parseInt(args[1]);
                Course course = ELearningPlatformService.findCourseById(courseId);
                AnonymousCourseFeedback feedback = new AnonymousCourseFeedback(id, course, args[2]);
                if (!AdminInterface.feedbacks.contains(feedback)) {
                    AdminInterface.feedbacks.add(feedback);
                    repository.getAnonymousCourseFeedbackDao().writeAnonymousCourseFeedback(feedback);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: ReadFeedbacks: " + e);
        }
    }

    private void readTeachingAssistants() {
        try {
            Scanner fin = new Scanner(new FileInputStream("assistants.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                Date date = AdminInterface.parseDate(args[2], "yyyy-MM-dd");
                int teacherId = Integer.parseInt(args[3]);
                Teacher teacher = (Teacher) ELearningPlatformService.findUserById(teacherId);
                TeachingAssistant teachingAssistant = new TeachingAssistant(id, args[1], date, teacher, args[4], args[5]);
                if (!AdminInterface.users.contains(teachingAssistant)) {
                    AdminInterface.users.add(teachingAssistant);
                    repository.getTeachingAssistantDao().writeTeachingAssistant(teachingAssistant);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readTeachingAssistants: " + e);
        }
    }

    private void readQuizzes() {
        try {
            Scanner fin = new Scanner(new FileInputStream("quizzes.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                int courseId = Integer.parseInt(args[1]);
                Course course = ELearningPlatformService.findCourseById(courseId);
                Quiz quiz = new Quiz(id, course, args[2]);
                if (!AdminInterface.quizzes.contains(quiz)) {
                    AdminInterface.quizzes.add(quiz);
                    repository.getQuizDao().writeQuiz(quiz);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readQuizzes: " + e);
        }
    }

    private void readUserCourseRepartition() {
        try {
            Scanner fin = new Scanner(new FileInputStream("repartitions.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int courseId = Integer.parseInt(args[0]);
                Course course = ELearningPlatformService.findCourseById(courseId);
                Date date = AdminInterface.parseDate(args[1], "yyyy-MM-dd");
                int userId = Integer.parseInt(args[2]);
                User user = ELearningPlatformService.findUserById(userId);

                UserCourseRepartition userCourseRepartition = new UserCourseRepartition(date, course, user);
                if (!AdminInterface.userCourseRepartitions.contains(userCourseRepartition)) {
                    AdminInterface.userCourseRepartitions.add(userCourseRepartition);
                    repository.getUserCourseRepartitionDao().writeUserCourseRepartition(userCourseRepartition);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readUserCourseRepartition: " + e);
        }
    }

    private void readCourses() {
        try {
            Scanner fin = new Scanner(new FileInputStream("courses.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                int teacherId = Integer.parseInt(args[1]);
                Teacher teacher = (Teacher) ELearningPlatformService.findUserById(teacherId);
                Course course = new Course(id, teacher, args[2], args[3]);
                if (!AdminInterface.courses.contains(course)) {
                    AdminInterface.courses.add(course);
                    repository.getCourseDao().writeCourse(course);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readCourses: " + e);
        }
    }

    private void readStudents() {
        try {
            Scanner fin = new Scanner(new FileInputStream("students.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                Date date = AdminInterface.parseDate(args[2], "yyyy-MM-dd");
                Student student = new Student(id, args[1], date, args[3], args[4]);
                if (!AdminInterface.users.contains(student)) {
                    AdminInterface.users.add(student);
                    repository.getStudentDao().writeStudent(student);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readStudents: " + e);
        }
    }

    private void readTeachers() {
        try {
            Scanner fin = new Scanner(new FileInputStream("teachers.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int id = Integer.parseInt(args[0]);
                Date date = AdminInterface.parseDate(args[2], "yyyy-MM-dd");
                Teacher teacher = new Teacher(id, args[1], date, args[3], args[4], args[5]);
                if (!AdminInterface.users.contains(teacher)) {
                    AdminInterface.users.add(teacher);
                    repository.getTeacherDao().writeTeacher(teacher);
                }
            }
            fin.close();
        } catch (Exception e) {
            auditCsvService.writeCsv("Exception in PersistentCsvReadService.java: readTeachers: " + e);
        }
    }

    static PersistentCsvReadService getInstance() {
        if (instance == null)
            instance = new PersistentCsvReadService();
        return instance;
    }
}
