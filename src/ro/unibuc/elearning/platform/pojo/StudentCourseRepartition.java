package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class StudentCourseRepartition {
    @NotNull
    protected final Date startDate;
    @NotNull
    protected final Course course;
    @NotNull
    protected final Student student;

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
