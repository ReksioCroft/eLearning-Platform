package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public final class TeachingAssistant extends User {
    @NotNull
    final Teacher supervisorTeacher;

    public TeachingAssistant(@NotNull String userName, @NotNull Date birthDate, @NotNull Teacher supervisorTeacher, @NotNull String address, @NotNull String phoneNumber) {
        super(userName, birthDate, address, phoneNumber);
        this.supervisorTeacher = supervisorTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TeachingAssistant that = (TeachingAssistant) o;
        return supervisorTeacher.equals(that.supervisorTeacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), supervisorTeacher);
    }

    @Override
    public String toString() {
        return "TeachingAssistant{" +
                "supervisorTeacher=" + supervisorTeacher +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                ", birthDate=" + simpleDateFormat.format(birthDate) +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String toStringCsv() {
        return userName + ", " + simpleDateFormat.format(birthDate) + ", " + supervisorTeacher.id + " ," + address + ", " + phoneNumber;
    }
}