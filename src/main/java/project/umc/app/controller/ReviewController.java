package project.umc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.dto.AddReviewRequestDto;
import project.umc.app.dto.AddReviewResponseDto;
import project.umc.app.dto.StoreReviewsResponseDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;
import project.umc.app.service.ReviewService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("allReviews")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>>showStoreReview(@RequestParam("storeId")Long storeId){
        List<ReviewEntity> reviewEntityList = reviewService.findAllByStoreId(storeId);
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= reviewService.ReviewEntitiesToResponseDto(reviewEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,storeReviewsResponseDtoList),HttpStatus.OK);
    }




    @GetMapping("reviews")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>>showSectionalStoreReview(@RequestParam("storeId")Long storeId,@RequestParam("page")Integer pageNumber){
        List<ReviewEntity> reviewEntityList = reviewService.findSectionByStoreId(storeId,pageNumber);
        List<StoreReviewsResponseDto> storeReviewsResponseDtoList= reviewService.ReviewEntitiesToResponseDto(reviewEntityList);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._OK,storeReviewsResponseDtoList),HttpStatus.OK);
    }


    @PostMapping("review/registration")
    public ResponseEntity<ApiResponse<AddReviewResponseDto>>addReview(@RequestBody@Valid AddReviewRequestDto addReviewRequestDto){

        ReviewEntity reviewEntity = AddReviewRequestDto.toEntity(addReviewRequestDto);
        reviewService.insertUserAndStoreEntity(reviewEntity,
                addReviewRequestDto.getUserId(), addReviewRequestDto.getStoreId());

        reviewService.save(reviewEntity);

        AddReviewResponseDto addReviewResponseDto = AddReviewResponseDto.createAddReviewResponseDto(reviewEntity);

        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,addReviewResponseDto),HttpStatus.ACCEPTED);



    }
}
