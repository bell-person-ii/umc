package project.umc.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.dto.AddReviewRequestDto;
import project.umc.app.dto.AddReviewResponseDto;
import project.umc.app.dto.StoreReviewsResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.ReviewService;
import project.umc.app.vaildation.annotation.CheckPage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("stores/allReviews")
    @Operation(summary = "리뷰 전체 조회 API", description = "URI로 스토어ID를 받아 해당 스토어의 리뷰를 전체 조회, 페이징 미적용")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>>showStoreReviewByStoreId(@RequestParam("storeId")Long storeId){
        List<ReviewEntity> reviewEntityList = reviewService.findAllByStoreId(storeId);
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= reviewService.ReviewEntitiesToResponseDto(reviewEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,storeReviewsResponseDtoList),HttpStatus.OK);
    }




    @GetMapping("stores/reviews")
    @Operation(summary = "리뷰 페이징 조회 API", description = "URI로 스토어ID를 받아 해당 스토어의 리뷰를 조회, 페이징 적용")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>>showSectionalStoreReviewByStoreId(@RequestParam("storeId")Long storeId,@RequestParam("page") @CheckPage Integer pageNumber){
        List<ReviewEntity> reviewEntityList = reviewService.findSectionByStoreId(storeId,pageNumber);
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= reviewService.ReviewEntitiesToResponseDto(reviewEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,storeReviewsResponseDtoList),HttpStatus.OK);
    }

    @GetMapping("users/reviews")
    @Operation(summary = "리뷰 페이징 조회 API", description = "URI로 유저ID를 받아 해당 유저가 작성한 리뷰를 조회, 페이징 적용")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>>showSectionalStoreReviewByUserId(@RequestParam("userId")Long userId,  @RequestParam("page") @CheckPage Integer pageNumber){
        List<ReviewEntity> reviewEntityList = reviewService.findSectionByUserId(userId,pageNumber);
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= reviewService.ReviewEntitiesToResponseDto(reviewEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,storeReviewsResponseDtoList),HttpStatus.OK);
    }


    @PostMapping("review/registration")
    @Operation(summary = "리뷰 등록 API", description = "유저 아이디, 스토어 아이디, 평점, 리뷰 내용을 받아서 저장함")
    public ResponseEntity<ApiResponse<AddReviewResponseDto>>addReview(@RequestBody@Valid AddReviewRequestDto addReviewRequestDto){

        ReviewEntity reviewEntity = AddReviewRequestDto.toEntity(addReviewRequestDto);
        reviewService.insertUserAndStoreEntity(reviewEntity,
                addReviewRequestDto.getUserId(), addReviewRequestDto.getStoreId());

        reviewService.save(reviewEntity);

        AddReviewResponseDto addReviewResponseDto = AddReviewResponseDto.createAddReviewResponseDto(reviewEntity);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addReviewResponseDto),HttpStatus.ACCEPTED);



    }
}
