package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() && getUserName().equals(user.getUserName()) && getBirthDate().equals(user.getBirthDate()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber());
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
    public int hashCode() {
        return Objects.hash(getUserName(), getId(), getBirthDate(), getAddress(), getPhoneNumber());
    }
}
