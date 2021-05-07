package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class PersistentCsvReadService {
    private static PersistentCsvReadService instance;
    public final ELearningPlatformService eLearningPlatformService;

    private PersistentCsvReadService() {
        eLearningPlatformService = new ELearningPlatformService();
        eLearningPlatformService.clearALL();
        AuditCsvService auditCsvService = AuditCsvService.getInstance();
        auditCsvService.writeCsv("Loading csv files in memory");
        readTeachers(eLearningPlatformService);
        readTeachingAssistants(eLearningPlatformService);
        readStudents(eLearningPlatformService);
        readCourses(eLearningPlatformService);
        readUserCourseRepartition(eLearningPlatformService);
        readQuizzes(eLearningPlatformService);
        readFeedbacks(eLearningPlatformService);
    }

    private void readFeedbacks(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("feedbacks.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int courseId = Integer.parseInt(args[0]);
                Course course = eLearningPlatformService.findCourseById(courseId);
                AnonymousCourseFeedback feedback = new AnonymousCourseFeedback(course, args[1]);
                eLearningPlatformService.feedbacks.add(feedback);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readTeachingAssistants(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("assistants.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                Date date = eLearningPlatformService.parseDate(args[1]);
                int teacherId = Integer.parseInt(args[2]);
                Teacher teacher = (Teacher) eLearningPlatformService.findUserById(teacherId);
                TeachingAssistant teachingAssistant = new TeachingAssistant(args[0], date, teacher, args[3], args[4]);
                eLearningPlatformService.users.add(teachingAssistant);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readQuizzes(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("quizzes.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int courseId = Integer.parseInt(args[0]);
                Course course = eLearningPlatformService.findCourseById(courseId);
                Quiz quiz = new Quiz(course, args[1]);
                eLearningPlatformService.quizzes.add(quiz);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readUserCourseRepartition(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("repartitions.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int courseId = Integer.parseInt(args[0]);
                Course course = eLearningPlatformService.findCourseById(courseId);
                Date date = eLearningPlatformService.parseDate(args[1]);
                int userId = Integer.parseInt(args[2]);
                User user = eLearningPlatformService.findUserById(userId);

                UserCourseRepartition userCourseRepartition = new UserCourseRepartition(date, course, user);

                eLearningPlatformService.userCourseRepartitions.add(userCourseRepartition);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCourses(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("courses.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                int teacherId = Integer.parseInt(args[0]);
                Teacher teacher = (Teacher) eLearningPlatformService.findUserById(teacherId);
                Course course = new Course(teacher, args[1], args[2]);
                eLearningPlatformService.courses.add(course);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readStudents(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("students.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                Date date = eLearningPlatformService.parseDate(args[1]);
                Student student = new Student(args[0], date, args[2], args[3]);
                eLearningPlatformService.users.add(student);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readTeachers(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("teachers.csv"));
            while (fin.hasNext()) {
                String s = fin.nextLine();
                String[] args = s.replaceAll(" ", "").split(",");
                Date date = eLearningPlatformService.parseDate(args[1]);
                Teacher teacher = new Teacher(args[0], date, args[2], args[3], args[4]);
                eLearningPlatformService.users.add(teacher);
            }
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PersistentCsvReadService getInstance() {
        if (instance == null)
            instance = new PersistentCsvReadService();
        return instance;
    }
}