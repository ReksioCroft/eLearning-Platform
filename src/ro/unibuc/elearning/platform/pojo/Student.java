package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Student extends User {

    public Student(@NotNull String userName, Date birthDate, String address, String phoneNumber) {
        super(userName, birthDate, address, phoneNumber);
    }

}
