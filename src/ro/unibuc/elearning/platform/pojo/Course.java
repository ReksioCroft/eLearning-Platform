package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

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
        this.id = ++co;
    }

    public Course(@NotNull User teacher, @NotNull String courseName) {
        this.teacher = teacher;
        this.courseName = courseName;
        this.id = ++co;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", courseName='" + courseName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getId() == course.getId() && getTeacher().equals(course.getTeacher()) && getCourseName().equals(course.getCourseName()) && Objects.equals(getDescription(), course.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeacher(), getCourseName(), getDescription());
    }

    public @NotNull User getTeacher() {
        return teacher;
    }

    public int getId() {
        return id;
    }

    public @NotNull String getCourseName() {
        return courseName;
    }
}
