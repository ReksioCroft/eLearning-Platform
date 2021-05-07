package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class Course {
    final int id;
    @NotNull
    final User teacher;
    @NotNull
    final String courseName;
    String description;
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

    public String toStringCsv() {
        return teacher.id + ", " + courseName + ", " + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && teacher.equals(course.teacher) && courseName.equals(course.courseName) && Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, courseName, description);
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
