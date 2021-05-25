package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Objects;

import static java.lang.Math.max;

public abstract class User {
    @NotNull
    protected String userName;
    protected final int id;
    @NotNull
    protected final Date birthDate;
    @NotNull
    protected String address;
    @NotNull
    protected String phoneNumber;
    private static int co = 0;
    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public User(@NotNull String userName, @NotNull Date birthDate, @NotNull String address, @NotNull String phoneNumber) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = ++co;
    }

    public User(int id, @NotNull String userName, @NotNull Date birthDate, @NotNull String address, @NotNull String phoneNumber) {
        this.userName = userName;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        co = max(co, id + 1);
    }

    public @NotNull String getUserName() {
        return userName;
    }

    public void setUserName(@NotNull String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public @NotNull Date getBirthDate() {
        return birthDate;
    }

    public @NotNull String getAddress() {
        return address;
    }

    public void setAddress(@NotNull String address) {
        this.address = address;
    }

    public @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void setCo(int co) {
        User.co = co;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", birthDate=" + simpleDateFormat.format(birthDate) +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && userName.equals(user.userName) && birthDate.equals(user.birthDate) && address.equals(user.address) && phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, id, birthDate, address, phoneNumber);
    }
}
