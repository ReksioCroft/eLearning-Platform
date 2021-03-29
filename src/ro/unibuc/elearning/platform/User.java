package ro.unibuc.elearning.platform;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

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
        this.id = co;
    }

    static {
        co++;
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

}
