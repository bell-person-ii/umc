package project.umc.app.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.vaildation.annotation.AlreadyExistOwnerEmail;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerJoinRequestDto {

    @NotNull
    private String name;
    private Gender gender;
    @NotNull
    private String birthYear;
    @NotNull
    private String birthMonth;
    @NotNull
    private String birthDay;
    @Valid
    @NotNull
    private Address address;
    @AlreadyExistOwnerEmail
    private String email;


    // String 타입 날짜데이터 -> LocalDate 타입 날짜데이터
    public static LocalDate stringDateToLocalDate(OwnerJoinRequestDto ownerJoinRequestDto){
        String year = ownerJoinRequestDto.getBirthYear();
        String month = ownerJoinRequestDto.getBirthMonth();
        String day = ownerJoinRequestDto.getBirthDay();
        String strDate = year+'-'+month+'-'+day;
        LocalDate resultDate = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
        return resultDate;
    }


    public static OwnerEntity toEntity(OwnerJoinRequestDto ownerJoinRequestDto){
        OwnerEntity ownerEntity = OwnerEntity.builder()
                .name(ownerJoinRequestDto.getName())
                .gender(ownerJoinRequestDto.getGender())
                .birthday(stringDateToLocalDate(ownerJoinRequestDto))
                .address(ownerJoinRequestDto.getAddress())
                .email(ownerJoinRequestDto.getEmail())
                .build();
        return ownerEntity;
    }


}
