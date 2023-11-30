package project.umc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.UserMissionEntity;
import project.umc.app.dto.AddStoreRequestDto;
import project.umc.app.dto.AddUserMissionRequestDto;
import project.umc.app.dto.AddUserMissionResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.UserMissionService;

@RestController
@RequiredArgsConstructor
public class UserMissionContorller {
    private final UserMissionService userMissionService;

    @PostMapping("missions/challenge")
    public ResponseEntity<ApiResponse<AddUserMissionResponseDto>> registerUserMission(@RequestBody AddUserMissionRequestDto addUserMissionRequestDto){
        UserMissionEntity userMissionEntity = AddUserMissionRequestDto.toEntity(addUserMissionRequestDto);
        userMissionService.insertUserAndMissionEntityById(userMissionEntity,
                addUserMissionRequestDto.getUserId(), addUserMissionRequestDto.getMissionId());

        //userMissionEntity 저장하기
        userMissionService.save(userMissionEntity);

        AddUserMissionResponseDto addUserMissionResponseDto = AddUserMissionResponseDto.createAddUserMissionResponseDto(userMissionEntity);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addUserMissionResponseDto),HttpStatus.ACCEPTED);
    }
}
