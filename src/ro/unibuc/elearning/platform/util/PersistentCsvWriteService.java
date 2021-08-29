package ro.unibuc.elearning.platform.util;

import ro.unibuc.elearning.platform.pojo.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PersistentCsvWriteService {
    private static PersistentCsvWriteService instance = null;
    private final PrintStream printCourse;
    private final PrintStream printQuiz;
    private final PrintStream printStudent;
    private final PrintStream printTeacher;
    private final PrintStream printTeachingAssistant;
    private final PrintStream printUserCourseRepartition;
    private final PrintStream printFeedback;
    private final AuditCsvService auditCsvService;


    private PersistentCsvWriteService() throws FileNotFoundException {
        printCourse = new PrintStream(new FileOutputStream("courses.csv", true));
        printQuiz = new PrintStream(new FileOutputStream("quizzes.csv", true));
        printStudent = new PrintStream(new FileOutputStream("students.csv", true));
        printTeacher = new PrintStream(new FileOutputStream("teachers.csv", true));
        printTeachingAssistant = new PrintStream(new FileOutputStream("assistants.csv", true));
        printUserCourseRepartition = new PrintStream(new FileOutputStream("repartitions.csv", true));
        printFeedback = new PrintStream(new FileOutputStream("feedbacks.csv", true));
        auditCsvService = AuditCsvService.getInstance();
    }

    Course writeCourse(Course course) {
        printCourse.println(course.toStringCsv());
        auditCsvService.writeCsv("New Course Added");
        return course;
    }

    Quiz writeQuiz(Quiz quiz) {
        printQuiz.println(quiz.toStringCsv());
        auditCsvService.writeCsv("New Quiz Added");
        return quiz;
    }

    Student writeStudent(Student student) {
        printStudent.println(student.toStringCsv());
        auditCsvService.writeCsv("New Student Added");
        return student;
    }

    Teacher writeTeacher(Teacher teacher) {
        printTeacher.println(teacher.toStringCsv());
        auditCsvService.writeCsv("New Teacher Added");
        return teacher;
    }

    TeachingAssistant writeTeachingAssistant(TeachingAssistant teachingAssistant) {
        printTeachingAssistant.println(teachingAssistant.toStringCsv());
        auditCsvService.writeCsv("New Teaching Assistant Added");
        return teachingAssistant;
    }

    UserCourseRepartition writeUserCourseRepartition(UserCourseRepartition userCourseRepartition) {
        printUserCourseRepartition.println(userCourseRepartition.toStringCsv());
        auditCsvService.writeCsv("New Course Repartition Added");
        return userCourseRepartition;
    }

    AnonymousCourseFeedback writeFeedback(AnonymousCourseFeedback feedback) {
        printFeedback.println(feedback.toStringCsv());
        auditCsvService.writeCsv("New Feedback Added");
        return feedback;
    }

    static PersistentCsvWriteService getInstance() {
        if (instance == null) {
            try {
                instance = new PersistentCsvWriteService();
            } catch (FileNotFoundException e) {
                AuditCsvService.getInstance().writeCsv("Exception in PersistentCsvWriteService.java: getInstance: " + e);
            }
        }
        return instance;
    }
}
