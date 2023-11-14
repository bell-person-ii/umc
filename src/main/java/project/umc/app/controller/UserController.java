package project.umc.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.dto.UserJoinrRequestDto;
import project.umc.app.dto.UserJoinResponseDto;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.repository.UserFoodCategoryRepository;
import project.umc.app.repository.UserRepository;
import project.umc.app.service.UserService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodCategoryRepository userFoodCategoryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("users/sign-up")
    public ResponseEntity<UserJoinResponseDto> UserSignUp(@RequestBody UserJoinrRequestDto userJoinrRequestDto){

        // 이미 존재하는 이메일이면 유저 추가 가입 거부
        if(userService.isNotExistEmail(userJoinrRequestDto.getEmail())){
            UserJoinResponseDto emailExistUserJoinResponseDto = UserJoinResponseDto
                    .builder().message("The email address you reported is Already Exist").build();
            return new ResponseEntity<>(emailExistUserJoinResponseDto,HttpStatus.ACCEPTED);
        }

        UserEntity userEntity = UserJoinrRequestDto.toEntity(userJoinrRequestDto);
        //userRepository.save(userEntity);

        userService.saveUser(userEntity);

        /*
        //json userPreferFoodCategory 리스트 데이터 -> 유저 선호 음식 저장 /1이면 한식, 2면 일식
        for(Long foodCategoryId : userJoinrRequestDto.getUserPreferFoodCategory()){
            FoodCategoryEntity resultFoodCategoryEntity = foodCategoryRepository.findFoodCategory(foodCategoryId);
            userFoodCategoryRepository.UserPreferSave(userEntity,resultFoodCategoryEntity);
        }
         */
        userService.saveUserPreferFoodCategory(userEntity,userJoinrRequestDto.getUserPreferFoodCategory());


        // DB에 저장된 유저 엔티티를 email로 찾아 해당 엔티티의 데이터 일부를 ResponseBody에 담아 리턴
        /*List<UserEntity> savedUserEntity = userRepository.findOneByEmail(userEntity.getEmail());*/

        UserEntity savedUserEntity = userService.findUserByEmail(userEntity.getEmail());

        UserJoinResponseDto userJoinResponseDto = UserJoinResponseDto.createUserJoinResponseDto(savedUserEntity);

        return ResponseEntity.status(HttpStatus.OK).body(userJoinResponseDto);
    }

}
