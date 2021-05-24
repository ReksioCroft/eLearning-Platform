package ro.unibuc.elearning.platform.dao;

public class Repository {
    private static Repository repository = null;

    private Repository() {
        UserDao userDao = UserDao.getUserDao();
        TeacherDao teacherDao = TeacherDao.getTeacherDao();
        StudentDao studentDao = StudentDao.getStudentDao();
        TeachingAssistantDao teachingAssistantDao = TeachingAssistantDao.getTeachingAssistantDao();
        CourseDao courseDao = CourseDao.getCourseDao();
        UserCourseRepartitionDao userCourseRepartitionDao = UserCourseRepartitionDao.getUserCourseRepartitionDao();
        QuizDao quizDao = QuizDao.getQuizDao();
        AnonymousCourseFeedbackDao anonymousCourseFeedbackDao = AnonymousCourseFeedbackDao.getAnonymousCourseFeedbackDao();
    }

    public static Repository getInstance() {
        if (repository == null)
            repository = new Repository();
        return repository;
    }
}
