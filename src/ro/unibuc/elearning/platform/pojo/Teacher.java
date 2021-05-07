package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public final class Teacher extends User {
    @NotNull String rank;

    public Teacher(@NotNull String userName, @NotNull Date birthDate, @NotNull String rank, String address, String phoneNumber) {
        super(userName, birthDate, address, phoneNumber);
        this.rank = rank;
    }

    public @NotNull String getRank() {
        return rank;
    }

    public void setRank(@NotNull String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return rank.equals(teacher.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rank);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "rank='" + rank + '\'' +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
