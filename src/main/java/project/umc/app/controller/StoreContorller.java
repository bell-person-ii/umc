package project.umc.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;
import project.umc.app.dto.AddStoreRequestDto;
import project.umc.app.dto.AddStoreResponseDto;
import project.umc.app.service.OwnerService;
import project.umc.app.service.StoreService;

@RestController
@RequiredArgsConstructor
public class StoreContorller {

    private final StoreService storeService;
    private final OwnerService ownerService;

    @PostMapping("stores/registration")
    public ResponseEntity<AddStoreResponseDto> AddStore(@RequestBody AddStoreRequestDto addStoreRequestDto){

        OwnerEntity findOwnerEntity= ownerService.findOwnerById(addStoreRequestDto.getOwnerId());
        // Dto -> Entity 전환부
        StoreEntity storeEntity = AddStoreRequestDto.toEntity(addStoreRequestDto);
        storeEntity.editOwner(findOwnerEntity);
        storeService.save(storeEntity);

        // 오너 아이디로 스토어 테이블에 저장된 해당 스토어 데이터 들고오기 구현 요구
        StoreEntity savedStoreEntity = storeService.findLastStoreEntityByOwnerId(addStoreRequestDto.getOwnerId());
        OwnerEntity ownerEntity = ownerService.findOwnerById(addStoreRequestDto.getOwnerId());

        AddStoreResponseDto addStoreResponseDto = AddStoreResponseDto
                .createAddStoreResponseDto(savedStoreEntity,ownerEntity);

        return new ResponseEntity<>(addStoreResponseDto, HttpStatus.OK);


    }
}
