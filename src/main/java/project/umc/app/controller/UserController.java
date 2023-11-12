package project.umc.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.dto.UserRequestDto;
import project.umc.app.dto.UserResponseDto;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.repository.UserFoodCategoryRepository;
import project.umc.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodCategoryRepository userFoodCategoryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("user/sign-up")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){

        UserEntity userEntity = UserRequestDto.toEntity(userRequestDto);
        userRepository.save(userEntity);

        //System.out.println(userRequestDto.getUserPreferFoodCategory());

        //userPreferFoodCategory json 데이터 따로 추출 -> user_food_category 테이블에 저장
        // 1이면 한식, 2면 일식

        List<Long> userPreferFoodList = new ArrayList<>();

        for(Long category :userRequestDto.getUserPreferFoodCategory()){
            userPreferFoodList.add(category);
        }

        for(Long category : userPreferFoodList){
            FoodCategoryEntity resultFoodCategoryEntity = foodCategoryRepository.findOne(category);
            userFoodCategoryRepository.UserPreferSave(userEntity,resultFoodCategoryEntity);
        }

        ///////
        UserResponseDto userResponseDto = UserResponseDto.createUserResponseDto(userEntity);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
