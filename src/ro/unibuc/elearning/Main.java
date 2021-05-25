package ro.unibuc.elearning;

import ro.unibuc.elearning.platform.dao.Repository;
import ro.unibuc.elearning.platform.pojo.*;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        // PersistentCsvReadService persistentCsvReadService = PersistentCsvReadService.getInstance();
        //ELearningPlatformService eLearningPlatformService = persistentCsvReadService.eLearningPlatformService;
        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        Repository repository = Repository.getRepository();
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
                        System.out.println("add student");
                        eLearningPlatformService.addStudent(cin);
                        break;
                    case 3:
                        System.out.println("add teaching assistant");
                        eLearningPlatformService.addTeachingAssistant(cin);
                        break;
                    case 4:
                        System.out.println("add course");
                        eLearningPlatformService.addCourse(cin);
                        break;
                    case 5:
                        System.out.println("add quiz");
                        eLearningPlatformService.addQuiz(cin);
                        break;
                    case 6:
                        System.out.println("add Feedback");
                        eLearningPlatformService.addFeedback(cin);
                        break;
                    case 7:
                        System.out.println("add a repartition of an user to a course");
                        eLearningPlatformService.addUserCourseRepartition(cin);
                        break;
                    case 8: {
                        System.out.println("find user");
                        System.out.println("Type id user");
                        int id = cin.nextInt();
                        User user = eLearningPlatformService.findUserById(id);
                        if (user != null)
                            System.out.println(user);
                        break;
                    }
                    case 9: {
                        System.out.println("find course");
                        System.out.println("Type id curs");
                        int id = cin.nextInt();
                        Course course = eLearningPlatformService.findCourseById(id);
                        if (course != null)
                            System.out.println(course);
                        break;
                    }
                    case 10: {
                        System.out.println("find quiz");
                        System.out.println("Type id quiz");
                        int id = cin.nextInt();
                        Quiz quiz = eLearningPlatformService.findQuizById(id);
                        if (quiz != null)
                            System.out.println(quiz);
                        break;
                    }
                    case 11: {
                        System.out.println("show repartitions to a course");
                        System.out.println("Type id curs");
                        int id = cin.nextInt();
                        TreeSet<UserCourseRepartition> userCourseRepartitions = eLearningPlatformService.findUserCourseRepartitionByCourseId(id);
                        System.out.println(userCourseRepartitions.toString());
                        break;
                    }
                    case 12: {
                        System.out.println("show repartitions of a specific student");
                        System.out.println("Type id student");
                        int id = cin.nextInt();
                        TreeSet<UserCourseRepartition> userCourseRepartitions = eLearningPlatformService.findSpecificStudentCourseRepartitionsByStudentId(id);
                        System.out.println(userCourseRepartitions.toString());
                        break;
                    }
                    case 13: {
                        System.out.println("show feedbacks of a specific course");
                        System.out.println("Type id course");
                        int id = cin.nextInt();
                        ArrayList<AnonymousCourseFeedback> feedbacks = eLearningPlatformService.findFeedbacksByCourseId(id);
                        System.out.println(feedbacks.toString());
                        break;
                    }
                    case 14:
                        System.out.println("Users: ");
                        System.out.println(ELearningPlatformService.users);
                        break;
                    case 15:
                        System.out.println("Courses: ");
                        System.out.println(ELearningPlatformService.courses);
                        break;
                    case 16:
                        System.out.println("Delete student by id");
                        repository.getStudentDao().deleteStudent(cin.nextInt());
                        break;
                    case 17:
                        System.out.println("Delete Teacher by Id");
                        repository.getTeacherDao().deleteTeacher(cin.nextInt());
                        break;
                    case 18:
                        System.out.println("Delete Teaching assistant by Id");
                        repository.getTeachingAssistantDao().deleteTeachingAssistant(cin.nextInt());
                        break;
                    case 19:
                        System.out.println("Delete Course by Id");
                        repository.getCourseDao().deleteCourse(cin.nextInt());
                        break;
                    case 20:
                        System.out.println("Delete Quiz by Id");
                        Quiz quiz = eLearningPlatformService.findQuizById(cin.nextInt());
                        repository.getQuizDao().deleteQuiz(quiz);
                        break;
                    case 21:
                        System.out.println("Delete Feedback by CourseId and Index");
                        ArrayList<AnonymousCourseFeedback> feedbacks = eLearningPlatformService.findFeedbacksByCourseId(cin.nextInt());
                        System.out.println(feedbacks);
                        AnonymousCourseFeedback feedback = feedbacks.get(cin.nextInt());
                        repository.getAnonymousCourseFeedbackDao().deleteAnonymousCourseFeedback(feedback);
                        break;
                    case 22:
                        System.out.println("Delete repartition by CourseId and index");
                        TreeSet<UserCourseRepartition> userCourseRepartitionTreeSet = eLearningPlatformService.findUserCourseRepartitionByCourseId(cin.nextInt());
                        ArrayList<UserCourseRepartition> userCourseRepartitions = new ArrayList<>(userCourseRepartitionTreeSet);
                        System.out.println(userCourseRepartitions);
                        UserCourseRepartition userCourseRepartition = userCourseRepartitions.get(cin.nextInt());
                        repository.getUserCourseRepartitionDao().deleteUserCourseRepartition(userCourseRepartition);
                        break;
                    case 23:
                        System.out.println("Update Course Description by id (format: id\\n desc\\n)");
                        repository.getCourseDao().updateCourseDescription(cin.nextInt(), cin.next());
                        break;
                    case 24:
                        System.out.println("Update Quiz by Id  (format: quizId\\n content\\n)");
                        repository.getQuizDao().updateQuizContent(cin.nextInt(), cin.next());
                        break;
                    case 25:
                        System.out.println("Update Teacher Ranking by Id (format: quizId\\n content\\n)");
                        repository.getTeacherDao().updateTeacherRanking(cin.nextInt(), cin.next());
                        break;
                    case 26:
                        System.out.println("Update User Address and PhoneNumber (format: userId\\n address\\n phoneNumber\\n)");
                        System.out.println("enter '*' to address or phoneNumber to not update that value");
                        repository.getStudentDao().updateUserPhoneAddress(cin.nextInt(), cin.next(), cin.next());
                        break;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Type option");
            opt = cin.nextInt();
        }
        System.out.println("Good Bye!");
    }
}
