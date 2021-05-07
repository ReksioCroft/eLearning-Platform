package ro.unibuc.elearning;

import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;
import ro.unibuc.elearning.platform.util.PersistentCsvReadService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        PersistentCsvReadService persistentCsvReadService = PersistentCsvReadService.getInstance();
        ELearningPlatformService eLearningPlatformService = persistentCsvReadService.eLearningPlatformService;
        eLearningPlatformService.setDebug(true);
        System.out.println("Type option");
        int opt = cin.nextInt();
        while (opt > 0) {
            try {
                switch (opt) {
                    case 1:
                        System.out.println("add teacher");
                        eLearningPlatformService.addTeacher(cin);
                        break;
                    case 2:
                        System.out.println("add course");
                        eLearningPlatformService.addCourse(cin);
                        break;
                    case 3:
                        System.out.println("add quiz");
                        eLearningPlatformService.addQuiz(cin);
                        break;
                    case 4:
                        System.out.println("add student");
                        eLearningPlatformService.addStudent(cin);
                        break;
                    case 5:
                        System.out.println("add a repartition of a student to a course");
                        eLearningPlatformService.addUserCourseRepartition(cin);
                        break;
                    case 6: {
                        System.out.println("find user");
                        System.out.println("Type id user");
                        int id = cin.nextInt();
                        User user = eLearningPlatformService.findUserById(id);
                        if (user != null)
                            System.out.println(user);
                        break;
                    }
                    case 7: {
                        System.out.println("find course");
                        System.out.println("Type id curs");
                        int id = cin.nextInt();
                        Course course = eLearningPlatformService.findCourseById(id);
                        if (course != null)
                            System.out.println(course);
                        break;
                    }
                    case 8: {
                        System.out.println("find student");
                        System.out.println("Type id student");
                        int id = cin.nextInt();
                        Student student = (Student) eLearningPlatformService.findUserById(id);
                        if (student != null)
                            System.out.println(student);
                        break;
                    }
                    case 9: {
                        System.out.println("find quiz");
                        System.out.println("Type id quiz");
                        int id = cin.nextInt();
                        Quiz quiz = eLearningPlatformService.findQuizById(id);
                        if (quiz != null)
                            System.out.println(quiz);
                        break;
                    }
                    case 10: {
                        System.out.println("show repartitions to a course");
                        System.out.println("Type id curs");
                        int id = cin.nextInt();
                        ArrayList<UserCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findUserCourseRepartitionByCourseId(id);
                        System.out.println(eLearningPlatformServices.toString());
                        break;
                    }
                    case 11: {
                        System.out.println("show repartitions of a specific student");
                        System.out.println("Type id student");
                        int id = cin.nextInt();
                        ArrayList<UserCourseRepartition> eLearningPlatformServices = eLearningPlatformService.findSpecificStudentCourseRepartitionsByStudentId(id);
                        System.out.println(eLearningPlatformServices.toString());
                        break;
                    }
                    case 12:
                        System.out.println("Users: ");
                        System.out.println(ELearningPlatformService.users);
                        break;
                    case 13:
                        System.out.println("Courses: ");
                        System.out.println(ELearningPlatformService.courses);
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e) {
                System.out.println("!Error: " + e.getMessage());
            }
            System.out.println("Type option");
            opt = cin.nextInt();
        }
        System.out.println("Good Bye!");
    }
}
