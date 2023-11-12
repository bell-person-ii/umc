package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.UserEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private String name;
    private Gender gender;
    private LocalDate birthday;
    private Address address;
    private String category;



    public static UserResponseDto createUserResponseDto(UserEntity userEntity){
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(userEntity.getName())
                .gender(userEntity.getGender())
                .birthday(userEntity.getBirthday())
                .address(userEntity.getAddress())
                .build();

        return userResponseDto;

    }
}
