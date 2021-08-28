package ro.unibuc.elearning.platform.dao;

import ro.unibuc.elearning.platform.util.AuditCsvService;
import ro.unibuc.elearning.platform.util.ELearningPlatformService;

public final class Repository {
    private static Repository repository = null;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final TeachingAssistantDao teachingAssistantDao;
    private final CourseDao courseDao;
    private final UserCourseRepartitionDao userCourseRepartitionDao;
    private final QuizDao quizDao;
    private final AnonymousCourseFeedbackDao anonymousCourseFeedbackDao;

    private Repository() throws InterruptedException {
        AuditCsvService auditCsvService = AuditCsvService.getInstance();
        auditCsvService.writeCsv("Loading database in memory using jdbc");

        ELearningPlatformService eLearningPlatformService = new ELearningPlatformService();
        eLearningPlatformService.clearALL();

        teacherDao = TeacherDao.getTeacherDao();
        teacherDao.start();
        studentDao = StudentDao.getStudentDao();
        studentDao.start();
        teachingAssistantDao = TeachingAssistantDao.getTeachingAssistantDao();
        teachingAssistantDao.start();
        courseDao = CourseDao.getCourseDao();
        courseDao.start();
        userCourseRepartitionDao = UserCourseRepartitionDao.getUserCourseRepartitionDao();
        userCourseRepartitionDao.start();
        quizDao = QuizDao.getQuizDao();
        quizDao.start();
        anonymousCourseFeedbackDao = AnonymousCourseFeedbackDao.getAnonymousCourseFeedbackDao();
        anonymousCourseFeedbackDao.start();

        teacherDao.join();
        studentDao.join();
        teachingAssistantDao.join();
        courseDao.join();
        userCourseRepartitionDao.join();
        quizDao.join();
        anonymousCourseFeedbackDao.join();
    }

    public static Repository getRepository() {
        if (repository == null) {
            try {
                repository = new Repository();
            } catch (InterruptedException e) {
                System.out.println("Exception in Repository.java: getRepository: " + e);
                return getRepository();
            }
        }
        return repository;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }


    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

    public TeachingAssistantDao getTeachingAssistantDao() {
        return teachingAssistantDao;
    }

    public CourseDao getCourseDao() {
        return courseDao;
    }

    public UserCourseRepartitionDao getUserCourseRepartitionDao() {
        return userCourseRepartitionDao;
    }

    public QuizDao getQuizDao() {
        return quizDao;
    }

    public AnonymousCourseFeedbackDao getAnonymousCourseFeedbackDao() {
        return anonymousCourseFeedbackDao;
    }
}
