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
public class UserJoinrRequestDto {

    private String name;
    private Gender gender;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private Address address;
    private String email;
    private List<Long> userPreferFoodCategory =new ArrayList<>();

// String 타입 날짜데이터 -> LocalDate 타입 날짜데이터
    public static LocalDate stringDateToLocalDate(UserJoinrRequestDto userJoinrRequestDto){
        String year = userJoinrRequestDto.getBirthYear();
        String month = userJoinrRequestDto.getBirthMonth();
        String day = userJoinrRequestDto.getBirthDay();
        String strDate = year+'-'+month+'-'+day;
        LocalDate resultDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        return resultDate;
    }

    // Dto -> Entity 변환
    public static UserEntity toEntity(UserJoinrRequestDto userJoinrRequestDto){
        UserEntity userEntity = UserEntity.builder()
                .name(userJoinrRequestDto.getName())
                .gender(userJoinrRequestDto.getGender())
                .birthday(UserJoinrRequestDto.stringDateToLocalDate(userJoinrRequestDto))
                .address(userJoinrRequestDto.getAddress())
                .email(userJoinrRequestDto.getEmail())
                .build();
        return userEntity;
    }
}
