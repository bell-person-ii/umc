package project.umc.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.domain.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<String> category;

    public static List<String> foodCategoryEntitiesToStringList(List<UserFoodCategoryEntity> userFoodCategoryEntity){
        List<String> foodCategoryStringList= new ArrayList<>();
        for(int i =0; i< userFoodCategoryEntity.size();i++){
            FoodCategoryEntity foodCategoryEntity = userFoodCategoryEntity.get(i).getFoodCategoryEntity();
            foodCategoryStringList.add(foodCategoryEntity.getName());
        }
        return foodCategoryStringList;
    }



    public static UserJoinResponseDto createUserJoinResponseDto(UserEntity userEntity){

        UserJoinResponseDto userJoinResponseDto = UserJoinResponseDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .gender(userEntity.getGender())
                .birthday(userEntity.getBirthday())
                .address(userEntity.getAddress())
                .email(userEntity.getEmail())
                .category(UserJoinResponseDto.foodCategoryEntitiesToStringList(userEntity.getUserFoodCategories()))
                .build();

        return userJoinResponseDto;

    }
}
