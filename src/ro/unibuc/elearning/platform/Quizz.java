package ro.unibuc.elearning.platform;

import org.jetbrains.annotations.NotNull;

public class Quizz {
    @NotNull
    protected final Course course;
    @NotNull
    protected String quizz;

    public Quizz(@NotNull Course course, @NotNull String quizz) {
        this.course = course;
        this.quizz = quizz;
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
