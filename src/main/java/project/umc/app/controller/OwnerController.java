package project.umc.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.dto.OwnerJoinRequestDto;
import project.umc.app.dto.OwnerJoinResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.OwnerService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @Operation(summary = "유저 가입 API",description = "유저 이름, 성별, 생년월일, 주소, 이메일을 입력 받아 화원정보를 저장함")
    @PostMapping("owners/sign-up")
    public ResponseEntity<ApiResponse<OwnerJoinResponseDto>> OwnerSignUp(@RequestBody @Valid OwnerJoinRequestDto ownerJoinRequestDto){
        OwnerEntity ownerEntity = OwnerJoinRequestDto.toEntity(ownerJoinRequestDto);
        ownerService.saveOwner(ownerEntity);

        OwnerEntity savedOwnerEntity = ownerService.findOwnerByEmail(ownerEntity.getEmail());
        OwnerJoinResponseDto ownerJoinResponseDto = OwnerJoinResponseDto.createOwnerJoinResponseDto(savedOwnerEntity);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,ownerJoinResponseDto),HttpStatus.ACCEPTED);
    }

}
