package ro.unibuc.elearning;

import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int opt = cin.nextInt();
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        while (opt > 0) {
            if (opt == 1) { //adauga utilizator
                eLearningPlatformService.addUser(cin);
            } else if (opt == 2) {
                eLearningPlatformService.addCourse(cin);
            } else if (opt == 3) {
                eLearningPlatformService.addQuizz(cin);
            } else if (opt == 4) {
                eLearningPlatformService.addStudent(cin);
            } else if (opt == 5) {
                eLearningPlatformService.addStudentCourseRepartition(cin);
            } else if (opt == 6) {
                int id = cin.nextInt();
                User user = eLearningPlatformService.findUser(id);
                if (user != null)
                    System.out.println(user);
            } else if (opt == 7) {
                int id = cin.nextInt();
                Course course = eLearningPlatformService.findCourse(id);
                if (course != null)
                    System.out.println(course);
            } else if (opt == 8) {
                int id = cin.nextInt();
                Student student = (Student) eLearningPlatformService.findUser(id);
                if (student != null)
                    System.out.println(student);
            } else if (opt == 9) {
                int id = cin.nextInt();
                Quizz quizz = eLearningPlatformService.findQuizz(id);
                if (quizz != null)
                    System.out.println(quizz);
            } else if (opt == 10) {
                int id = cin.nextInt();
                ArrayList<StudentCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findStudentCourseRepartitionByCourse(id);
                System.out.println(eLearningPlatformServices);
            } else if (opt == 11) {
                int id = cin.nextInt();
                ArrayList<StudentCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findStudentCourseRepartitionByStudent(id);
                System.out.println(eLearningPlatformServices);
            }

            opt = cin.nextInt();
        }
    }
}
