package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Quiz {
    final int id;
    @NotNull
    final Course course;
    @NotNull
    String quiz;
    private static int co = 0;

    public Quiz(@NotNull Course course, @NotNull String quiz) {
        this.course = course;
        this.quiz = quiz;
        this.id = ++co;
    }

    public void updateQuiz(@NotNull String quiz) {
        this.quiz = quiz;
    }

    public @NotNull Course getCourse() {
        return course;
    }

    public @NotNull String getQuiz() {
        return quiz;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", course=" + course +
                ", quiz='" + quiz + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quiz)) return false;
        Quiz quiz1 = (Quiz) o;
        return getId() == quiz1.getId() && getCourse().equals(quiz1.getCourse()) && getQuiz().equals(quiz1.getQuiz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourse(), getQuiz());
    }
}
