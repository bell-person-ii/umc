package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.UserEntity;
import project.umc.app.domain.UserFoodCategoryEntity;
import project.umc.app.vaildation.annotation.AlreadyExistUserEmail;
import project.umc.app.vaildation.annotation.ExistCategories;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinRequestDto {

    private String name;
    private Gender gender;
    private String birthYear;
    private String birthMonth;
    private String birthDay;
    private Address address;
    @AlreadyExistUserEmail
    private String email;

    @ExistCategories
    private List<Long> userPreferFoodCategory =new ArrayList<>();

// String 타입 날짜데이터 -> LocalDate 타입 날짜데이터
    public static LocalDate stringDateToLocalDate(UserJoinRequestDto userJoinRequestDto){
        String year = userJoinRequestDto.getBirthYear();
        String month = userJoinRequestDto.getBirthMonth();
        String day = userJoinRequestDto.getBirthDay();
        String strDate = year+'-'+month+'-'+day;
        LocalDate resultDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        return resultDate;
    }

    // Dto -> Entity 변환
    public static UserEntity toEntity(UserJoinRequestDto userJoinRequestDto){

        List<UserFoodCategoryEntity> userFoodCategoryEntities = new ArrayList<>();

        UserEntity userEntity = UserEntity.builder()
                .name(userJoinRequestDto.getName())
                .gender(userJoinRequestDto.getGender())
                .birthday(UserJoinRequestDto.stringDateToLocalDate(userJoinRequestDto))
                .address(userJoinRequestDto.getAddress())
                .email(userJoinRequestDto.getEmail())
                .build();
        return userEntity;
    }
}
