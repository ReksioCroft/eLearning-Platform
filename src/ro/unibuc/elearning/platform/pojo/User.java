package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class User {
    @NotNull
    protected String userName;
    protected final int id;
    @NotNull
    protected final Date birthDate;
    protected String address;
    protected String phoneNumber;
    private static int co = 0;

    public User(@NotNull String userName, @NotNull Date birthDate, String address, String phoneNumber) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getId(), getBirthDate(), getAddress(), getPhoneNumber());
    }
}
