package project.umc.app.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.dto.TestDto;
import project.umc.app.restApiResponse.ApiResponse;

@RequiredArgsConstructor
@RestController
public class TestRestApiResponseController {

    @GetMapping("/test")
    public ApiResponse<TestDto> testDtoApiResponse(){

        TestDto testDto = TestDto.builder().testString("Test Well done").build();
        return ApiResponse.onSuccess(testDto);
    }
}
