package project.umc.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.umc.app.domain.UserMissionEntity;
import project.umc.app.dto.*;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.UserMissionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserMissionContorller {
    private final UserMissionService userMissionService;

    @PostMapping("missions/challenge")
    @Operation(summary = "유저 미션 등록 API", description = "유저 ID와 미션 ID를 입력 받아 유저를 미션에 참여 처리함")
    public ResponseEntity<ApiResponse<AddUserMissionResponseDto>> registerUserMission(@RequestBody @Valid AddUserMissionRequestDto addUserMissionRequestDto){
        UserMissionEntity userMissionEntity = AddUserMissionRequestDto.toEntity(addUserMissionRequestDto);
        userMissionService.insertUserAndMissionEntityById(userMissionEntity,
                addUserMissionRequestDto.getUserId(), addUserMissionRequestDto.getMissionId());

        //userMissionEntity 저장하기
        userMissionService.save(userMissionEntity);

        AddUserMissionResponseDto addUserMissionResponseDto = AddUserMissionResponseDto.createAddUserMissionResponseDto(userMissionEntity);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addUserMissionResponseDto),HttpStatus.ACCEPTED);
    }

    @GetMapping("users/missions/in-progress")
    @Operation(summary = "유저 미션 조회 API", description = "유저가 참여중인 미션중 상태가 미완료인 미션을 조회함")
    public ResponseEntity<ApiResponse<List<UserMissionResponseDto>>> showNotCompletedUserMissions(@RequestParam("userId")Long userId){
        List<UserMissionEntity> userMissionEntityList = userMissionService.findNotCompletedUserMissions(userId);
        List<UserMissionResponseDto> userMissionResponseDtoList= userMissionService.EntityListToDtoList(userMissionEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,userMissionResponseDtoList),HttpStatus.OK);

    }

    @PutMapping("users/missions/completetion")
    @Operation(summary = "유저 미션 완료처리 API", description = "유저가 참여중인 미션을 완료 처리함")
    public ResponseEntity<ApiResponse<UserMissionResponseDto>> missionComplete(@RequestParam("userMissionId")Long userMissionId){
        UserMissionEntity userMissionEntity = userMissionService.changeCompletion(userMissionId);
        UserMissionResponseDto userMissionResponseDto = UserMissionResponseDto.createUserMissionResponseDto(userMissionEntity);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,userMissionResponseDto),HttpStatus.OK);
    }
}
