package project.umc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.umc.app.domain.MissionEntity;
import project.umc.app.domain.StoreEntity;
import project.umc.app.dto.AddMissionRequestDto;
import project.umc.app.dto.AddMissionResponseDto;
import project.umc.app.dto.AddStoreResponseDto;
import project.umc.app.dto.MissionResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.MissionService;
import project.umc.app.service.StoreService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final StoreService storeService;
    private final MissionService missionService;

    @PostMapping("missions/registration")
    public ResponseEntity<ApiResponse<AddMissionResponseDto>> addmission(@RequestBody @Valid AddMissionRequestDto addMissionRequestDto){
        MissionEntity missionEntity = AddMissionRequestDto.toEntity(addMissionRequestDto);

        //미션 엔티티에 스토어 엔티티 추가하기
        StoreEntity storeEntity = storeService.findOneById(addMissionRequestDto.getStoreId());
        missionEntity.editStoreEntity(storeEntity);

        //미션 DB 저장
        missionService.save(missionEntity);

        //addMissionResponseDto에 데이터 넣기
        AddMissionResponseDto addMissionResponseDto = AddMissionResponseDto.createAddMissionResponseDto(missionEntity,storeEntity);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addMissionResponseDto),HttpStatus.ACCEPTED);
    }

    @GetMapping("stores/missions")
    public ResponseEntity<ApiResponse<List<MissionResponseDto>>> showAllStoreMissions(@RequestParam("storeId")Long storeId){

        List<MissionEntity> missionEntityList = missionService.findAllByStoreId(storeId);
        List<MissionResponseDto> missionResponseDtoList = missionService.missionEntityListToDtoList(missionEntityList);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,missionResponseDtoList),HttpStatus.OK);
    }
}
