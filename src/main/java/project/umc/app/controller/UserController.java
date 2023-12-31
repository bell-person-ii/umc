package project.umc.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.UserEntity;
import project.umc.app.dto.UserJoinRequestDto;
import project.umc.app.dto.UserJoinResponseDto;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.repository.UserFoodCategoryRepository;
import project.umc.app.repository.UserRepository;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.UserService;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodCategoryRepository userFoodCategoryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("users/sign-up")
    @Operation(summary = "유저 등록 API", description = "유저의 이름, 성별, 생년월일, 이메일 입력을 받아 저장함 ")
    public ResponseEntity<ApiResponse<UserJoinResponseDto>> UserSignUp(@RequestBody @Valid UserJoinRequestDto userJoinRequestDto){

        // 이미 존재하는 이메일이면 유저 추가 가입 거부
        /*
        if(userService.isNotExistEmail(userJoinrRequestDto.getEmail())){
            ErrorStatus errorStatus = ErrorStatus.EXIST_EMAIL_ADDRESS;
            return new ResponseEntity<>(ApiResponse.onFailure(errorStatus.getCode(),errorStatus.getMessage(),null),HttpStatus.BAD_REQUEST);
        }
         */
        UserEntity userEntity = UserJoinRequestDto.toEntity(userJoinRequestDto);

        userService.saveUser(userEntity);

        userService.saveUserPreferFoodCategory(userEntity, userJoinRequestDto.getUserPreferFoodCategory());

        UserEntity savedUserEntity = userService.findUserByEmail(userEntity.getEmail());
        savedUserEntity.addUserFoodCategories(userService.findUserFoodPrefer(savedUserEntity));

        UserJoinResponseDto userJoinResponseDto = UserJoinResponseDto.createUserJoinResponseDto(savedUserEntity);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,userJoinResponseDto),HttpStatus.OK);
    }

}
