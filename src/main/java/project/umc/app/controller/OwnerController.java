package project.umc.app.controller;

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
import project.umc.app.service.OwnerService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("owners/sign-up")
    public ResponseEntity<OwnerJoinResponseDto> OwnerSignUp(@RequestBody @Valid OwnerJoinRequestDto ownerJoinRequestDto){
        OwnerEntity ownerEntity = OwnerJoinRequestDto.toEntity(ownerJoinRequestDto);
        ownerService.saveOwner(ownerEntity);

        OwnerEntity savedOwnerEntity = ownerService.findOwnerByEmail(ownerEntity.getEmail());
        OwnerJoinResponseDto ownerJoinResponseDto = OwnerJoinResponseDto.createOwnerJoinResponseDto(savedOwnerEntity);
        return new ResponseEntity<>(ownerJoinResponseDto, HttpStatus.OK);
    }

}
