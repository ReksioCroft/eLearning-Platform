package ro.unibuc.elearning.platform.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PersistanceCsvReadService {
    private static PersistanceCsvReadService instance;

    private PersistanceCsvReadService() {
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        eLearningPlatformService.clearALL();
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
                eLearningPlatformService.addFeedback(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readTeachingAssistants(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("assistants.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addTeachingAssistant(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readQuizzes(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("quizzes.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addQuiz(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readUserCourseRepartition(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("repartitions.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addUserCourseRepartition(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readCourses(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("courses.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addCourse(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readStudents(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("students.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addStudent(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readTeachers(ELearningPlatformService eLearningPlatformService) {
        try {
            Scanner fin = new Scanner(new FileInputStream("teachers.csv"));
            while (fin.hasNext()) {
                eLearningPlatformService.addTeacher(fin);
            }
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static PersistanceCsvReadService getInstance() {
        if (instance == null)
            instance = new PersistanceCsvReadService();
        return instance;
    }
}
