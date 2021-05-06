package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

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
}
