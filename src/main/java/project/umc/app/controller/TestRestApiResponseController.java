package project.umc.app.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.dto.TestDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.*;

@RequiredArgsConstructor
@RestController
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
}
