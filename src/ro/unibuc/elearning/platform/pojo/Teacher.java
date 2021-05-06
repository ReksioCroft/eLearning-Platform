package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

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
}
