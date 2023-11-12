package project.umc.app.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {


    @Getter
    public static class JoinDto{
        String name;
        String gender;
        String birthYear;
        String birthMonth;
        String birthDay;
        String address;
        String category;

    }
}
