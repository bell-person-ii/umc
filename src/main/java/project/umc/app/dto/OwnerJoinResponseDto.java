package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.Address;
import project.umc.app.domain.Gender;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerJoinResponseDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate birthday;
    private Address address;
    private String email;
    private List<String> storeList;


    public static OwnerJoinResponseDto createOwnerJoinResponseDto(OwnerEntity ownerEntity){

        OwnerJoinResponseDto ownerJoinResponseDto = OwnerJoinResponseDto.builder()
                .name(ownerEntity.getName())
                .gender(ownerEntity.getGender())
                .birthday(ownerEntity.getBirthday())
                .address(ownerEntity.getAddress())
                .email(ownerEntity.getEmail())
                .build();

        return ownerJoinResponseDto;
    }
}
