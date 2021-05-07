package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public final class Teacher extends User {
    @NotNull String rank;

    public Teacher(@NotNull String userName, @NotNull Date birthDate, @NotNull String rank, @NotNull String address, @NotNull String phoneNumber) {
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
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", rank='" + rank + '\'' +
                ", birthDate=" + simpleDateFormat.format(birthDate) +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String toStringCsv() {
        return userName + ", " + simpleDateFormat.format(birthDate) + ", " + rank + ", " + address + ", " + phoneNumber;
    }
}
