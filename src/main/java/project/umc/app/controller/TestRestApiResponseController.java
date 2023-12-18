package project.umc.app.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.domain.ReviewEntity;
import project.umc.app.dto.StoreReviewsResponseDto;
import project.umc.app.dto.TestDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.*;
import project.umc.app.vaildation.annotation.CheckPage;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class TestRestApiResponseController {

    @GetMapping("/test1")
    public ApiResponse<TestDto> testDtoApiResponse(){
        TestDto testDto = TestDto.builder().testString("200 테스트").build();

        return ApiResponse.onSuccess(testDto);
    }

    @GetMapping("/test2")
    public ApiResponse<TestDto> testDtoApiResponse2(){
        TestDto testDto = TestDto.builder().testString("202 테스트").build();

        return ApiResponse.of(SuccessStatus._ACCEPTED,testDto);
    }

    @GetMapping("test3")
    public ApiResponse<TestDto> testDtoApiResponse3(){
        TestDto testDto  = TestDto.builder().testString("403 테스트").build();
        ErrorStatus errorStatus = ErrorStatus._FORBIDDEN;
        return ApiResponse.onFailure(errorStatus.getCode(), errorStatus.getMessage(),testDto);
    }

    @GetMapping("test")
    public ResponseEntity<ApiResponse<List<StoreReviewsResponseDto>>> showSectional(@RequestParam("page") @CheckPage Integer page){
        System.out.println("pageNumber = " + page);
        return new ResponseEntity<>(ApiResponse.of(SuccessStatus._ACCEPTED,null),HttpStatus.ACCEPTED);
    }
}
