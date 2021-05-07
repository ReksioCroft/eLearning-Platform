package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class AnonymousCourseFeedback {
    @NotNull
    final Course course;
    @NotNull
    final String feedback;

    public AnonymousCourseFeedback(@NotNull Course course, @NotNull String feedback) {
        this.course = course;
        this.feedback = feedback;
    }

    public @NotNull Course getCourse() {
        return course;
    }

    public @NotNull String getFeedback() {
        return feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnonymousCourseFeedback feedback1 = (AnonymousCourseFeedback) o;
        return course.equals(feedback1.course) && feedback.equals(feedback1.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, feedback);
    }

    @Override
    public String toString() {
        return "AnonymousCourseFeedback{" +
                "course=" + course +
                ", feedback='" + feedback + '\'' +
                '}';
    }

    public String toStringCsv() {
        return course.id + ", " + feedback;
    }
}
