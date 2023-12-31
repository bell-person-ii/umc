package project.umc.app.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;
import project.umc.app.dto.AddStoreRequestDto;
import project.umc.app.dto.AddStoreResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.OwnerService;
import project.umc.app.service.StoreService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StoreContorller {

    private final StoreService storeService;
    private final OwnerService ownerService;

    @PostMapping("stores/registration")
    @Operation(summary = "스토어 등록 API", description = "스토어의 이름, 주소, 오너의 아이디, 스토어 카테고리 ID를 입력 받아 저장함")
    public ResponseEntity<ApiResponse<AddStoreResponseDto>> AddStore(@RequestBody @Valid AddStoreRequestDto addStoreRequestDto){

        OwnerEntity findOwnerEntity= ownerService.findOwnerById(addStoreRequestDto.getOwnerId());
        // Dto -> Entity 전환부
        StoreEntity storeEntity = AddStoreRequestDto.toEntity(addStoreRequestDto);
        // 오너 데이터 추가 부분
        storeEntity.editOwner(findOwnerEntity);
        // 카테고리 데이터 추가부분
        FoodCategoryEntity foodCategoryEntity = storeService.findFoodCategoryById(addStoreRequestDto.getStoreCategoryId());
        String foodCategoryName = foodCategoryEntity.getName();
        storeEntity.editCategory(foodCategoryName);

        storeService.save(storeEntity);

        // 오너 아이디로 스토어 테이블에 저장된 해당 스토어 데이터 들고오기 구현 요구
        StoreEntity savedStoreEntity = storeService.findLastStoreEntityByOwnerId(addStoreRequestDto.getOwnerId());
        OwnerEntity ownerEntity = ownerService.findOwnerById(addStoreRequestDto.getOwnerId());

        AddStoreResponseDto addStoreResponseDto = AddStoreResponseDto
                .createAddStoreResponseDto(savedStoreEntity,ownerEntity);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addStoreResponseDto),HttpStatus.ACCEPTED);


    }
}
