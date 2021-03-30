package ro.unibuc.elearning;

import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        System.out.println("Introduceti optiunea");
        int opt = cin.nextInt();
        while (opt > 0) {
            try {
                if (opt == 1) {
                    System.out.println("add user");
                    eLearningPlatformService.addUser(cin);
                } else if (opt == 2) {
                    System.out.println("add course");
                    eLearningPlatformService.addCourse(cin);
                } else if (opt == 3) {
                    System.out.println("add quizz");
                    eLearningPlatformService.addQuizz(cin);
                } else if (opt == 4) {
                    System.out.println("add student");
                    eLearningPlatformService.addStudent(cin);
                } else if (opt == 5) {
                    System.out.println("add a repartion of a student to a course");
                    eLearningPlatformService.addStudentCourseRepartition(cin);
                } else if (opt == 6) {
                    System.out.println("find user");
                    System.out.println("Introduceti id user");
                    int id = cin.nextInt();
                    User user = eLearningPlatformService.findUser(id);
                    if (user != null)
                        System.out.println(user.toString());
                } else if (opt == 7) {
                    System.out.println("find course");
                    System.out.println("Introduceti id curs");
                    int id = cin.nextInt();
                    Course course = eLearningPlatformService.findCourse(id);
                    if (course != null)
                        System.out.println(course.toString());
                } else if (opt == 8) {
                    System.out.println("find student");
                    System.out.println("Introduceti id student");
                    int id = cin.nextInt();
                    Student student = (Student) eLearningPlatformService.findUser(id);
                    if (student != null)
                        System.out.println(student.toString());
                } else if (opt == 9) {
                    System.out.println("find quizz");
                    System.out.println("Introduceti id quizz");
                    int id = cin.nextInt();
                    Quizz quizz = eLearningPlatformService.findQuizz(id);
                    if (quizz != null)
                        System.out.println(quizz.toString());
                } else if (opt == 10) {
                    System.out.println("show repartitions to a course");
                    System.out.println("Introduceti id curs");
                    int id = cin.nextInt();
                    ArrayList<StudentCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findStudentCourseRepartitionByCourse(id);
                    System.out.println(eLearningPlatformServices.toString());
                } else if (opt == 11) {
                    System.out.println("show repartitions of a student");
                    System.out.println("Introduceti id student");
                    int id = cin.nextInt();
                    ArrayList<StudentCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findStudentCourseRepartitionByStudent(id);
                    System.out.println(eLearningPlatformServices.toString());
                } else if (opt == 12) {
                    System.out.println("Users: ");
                    System.out.println(ELearningPlatformService.users.toString());
                } else if (opt == 13) {
                    System.out.println("Courses: ");
                    System.out.println(ELearningPlatformService.courses.toString());
                } else {
                    System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            System.out.println("Introduceti optiunea");
            opt = cin.nextInt();
        }
        System.out.println("Good Bye!");
    }
}
