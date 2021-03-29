package ro.unibuc.elearning.platform;

import org.jetbrains.annotations.NotNull;

public class Course {
    protected final int id;
    @NotNull
    protected final User teacher;
    @NotNull
    protected final String courseName;
    protected String description;
    private static int co = 0;

    public Course(@NotNull User teacher, @NotNull String courseName, String description) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.description = description;
        this.id=co;
    }

    public Course(@NotNull User teacher, @NotNull String courseName) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.id=co;
    }

    static {
        co++;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull User getTeacher() {
        return teacher;
    }

    public @NotNull String getCourseName() {
        return courseName;
    }
}
