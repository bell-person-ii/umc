package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.UserEntity;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserJoinResponseDto {

    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthday;
    private Address address;
    private String email;
    private String category;



    public static UserJoinResponseDto createUserJoinResponseDto(UserEntity userEntity){

        UserJoinResponseDto userJoinResponseDto = UserJoinResponseDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .gender(userEntity.getGender())
                .birthday(userEntity.getBirthday())
                .address(userEntity.getAddress())
                .email(userEntity.getEmail())
                .build();

        return userJoinResponseDto;

    }
}
