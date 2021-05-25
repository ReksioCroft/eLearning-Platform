package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.util.Objects;

public final class Teacher extends User {
    @NotNull String ranking;

    public Teacher(@NotNull String userName, @NotNull Date birthDate, @NotNull String rank, @NotNull String address, @NotNull String phoneNumber) {
        super(userName, birthDate, address, phoneNumber);
        this.ranking = rank;
    }

    public Teacher(int id, @NotNull String userName, @NotNull Date birthDate, @NotNull String rank, @NotNull String address, @NotNull String phoneNumber) {
        super(id, userName, birthDate, address, phoneNumber);
        this.ranking = rank;
    }

    public @NotNull String getRank() {
        return ranking;
    }

    public void setRank(@NotNull String rank) {
        this.ranking = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return ranking.equals(teacher.ranking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ranking);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", ranking='" + ranking + '\'' +
                ", birthDate=" + simpleDateFormat.format(birthDate) +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String toStringCsv() {
        return id + ", " + userName + ", " + simpleDateFormat.format(birthDate) + ", " + ranking + ", " + address + ", " + phoneNumber;
    }
}
