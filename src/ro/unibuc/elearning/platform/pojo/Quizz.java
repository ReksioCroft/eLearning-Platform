package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Quizz {
    protected final int id;
    @NotNull
    protected final Course course;
    @NotNull
    protected String quizz;
    private static int co = 0;

    public Quizz(@NotNull Course course, @NotNull String quizz) {
        this.course = course;
        this.quizz = quizz;
        this.id = ++co;
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Quizz{" +
                "id=" + id +
                ", course=" + course +
                ", quizz='" + quizz + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quizz)) return false;
        Quizz quizz1 = (Quizz) o;
        return getId() == quizz1.getId() && getCourse().equals(quizz1.getCourse()) && getQuizz().equals(quizz1.getQuizz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourse(), getQuizz());
    }
}
