package project.umc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.UserEntity;
import project.umc.app.dto.UserRequestDto;
import project.umc.app.dto.UserResponseDto;
import project.umc.app.repository.UserRepository;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    @PostMapping("user/sign-up")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){

        UserEntity userEntity = UserRequestDto.toEntity(userRequestDto);
        userRepository.save(userEntity);
        UserResponseDto userResponseDto = UserResponseDto.createUserResponseDto(userEntity);

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
