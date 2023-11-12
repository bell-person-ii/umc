package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.UserEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String name;
    private Gender gender;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private Address address;
    private List<Long> userPreferFoodCategory =new ArrayList<>();


    public static LocalDate stringDateToLocalDate(UserRequestDto userRequestDto){
        String year = userRequestDto.getBirthYear();
        String month = userRequestDto.getBirthMonth();
        String day = userRequestDto.getBirthDay();
        String strDate = year+'-'+month+'-'+day;
        LocalDate resultDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        return resultDate;
    }

    public static UserEntity toEntity(UserRequestDto userRequestDto){
        UserEntity userEntity = UserEntity.builder()
                .name(userRequestDto.getName())
                .gender(userRequestDto.getGender())
                .birthday(UserRequestDto.stringDateToLocalDate(userRequestDto))
                .address(userRequestDto.getAddress())
                .build();

        return userEntity;
    }
}
