package ro.unibuc.elearning.platform.pojo;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public final class TeachingAssistant extends User {
    @NotNull
    final Teacher supervisorTeacher;

    public TeachingAssistant(@NotNull String userName, @NotNull Date birthDate, String address, String phoneNumber, @NotNull Teacher supervisorTeacher) {
        super(userName, birthDate, address, phoneNumber);
        this.supervisorTeacher = supervisorTeacher;
    }
}
