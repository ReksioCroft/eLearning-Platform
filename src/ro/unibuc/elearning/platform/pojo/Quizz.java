package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

public class Quizz {
    public int getId() {
        return id;
    }

    protected final int id;
    @NotNull
    protected final Course course;
    @NotNull
    protected String quizz;
    private static int co = 0;

    public Quizz(@NotNull Course course, @NotNull String quizz) {
        this.course = course;
        this.quizz = quizz;
        this.id = co;
    }

    static {
        co++;
    }

    public void setQuizz(@NotNull String quizz) {
        this.quizz = quizz;
    }

    public @NotNull Course getCourse() {
        return course;
    }

    public @NotNull String getQuizz() {
        return quizz;
    }
}
