package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class UserCourseRepartition {
    @NotNull
    final Date startDate;
    @NotNull
    final Course course;
    @NotNull
    final User user;

    public UserCourseRepartition(@NotNull Date startDate, @NotNull Course course, @NotNull User user) {
        this.startDate = startDate;
        this.course = course;
        this.user = user;
    }

    public @NotNull Date getStartDate() {
        return startDate;
    }

    public @NotNull Course getCourse() {
        return course;
    }

    public @NotNull User getUser() {
        return user;
    }
}
