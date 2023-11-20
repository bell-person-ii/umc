package project.umc.app.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.dto.TestDto;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.BaseCode;
import project.umc.app.restApiResponse.detailStatusInfo.ReasonDTO;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;

@RequiredArgsConstructor
@RestController
public class TestRestApiResponseController {

    @GetMapping("/test1")
    public ApiResponse<TestDto> testDtoApiResponse(){

        TestDto testDt1 = TestDto.builder().testString("Test Well done").build();
        return ApiResponse.onSuccess(testDt1);
    }

    @GetMapping("/test2")
    public ApiResponse<TestDto> testDtoApiResponse2(){
        TestDto testDto = TestDto.builder().testString("Test of method").build();
       TestDto testDto2 = TestDto.builder().testString("202 테스트").build();
        return ApiResponse.of(SuccessStatus._ACCEPTED,testDto2);


    }
}
