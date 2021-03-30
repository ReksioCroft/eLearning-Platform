package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class StudentCourseRepartition {
    @NotNull
    protected final Date startDate;

    @NotNull
    protected final Course course;
    @NotNull
    protected final Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentCourseRepartition)) return false;
        StudentCourseRepartition that = (StudentCourseRepartition) o;
        return getStartDate().equals(that.getStartDate()) && getCourse().equals(that.getCourse()) && getStudent().equals(that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getCourse(), getStudent());
    }

    @Override
    public String toString() {
        return "StudentCourseRepartition{" +
                "startDate=" + startDate +
                ", course=" + course +
                ", student=" + student +
                '}';
    }

    public StudentCourseRepartition(@NotNull Date startDate, @NotNull Course course, @NotNull Student student) {
        this.startDate = startDate;
        this.course = course;
        this.student = student;
    }

    public @NotNull Date getStartDate() {
        return startDate;
    }

    public @NotNull Course getCourse() {
        return course;
    }

    public @NotNull Student getStudent() {
        return student;
    }
}
