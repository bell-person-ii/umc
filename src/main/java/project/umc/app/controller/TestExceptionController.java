package project.umc.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.umc.app.exception.testHandler.TestQueryService;
import project.umc.app.exception.testHandler.TestResponseDTO;
import project.umc.app.restApiResponse.ApiResponse;
import project.umc.app.restApiResponse.detailStatusInfo.ReasonDTO;


@RestController
@RequiredArgsConstructor
public class TestExceptionController {
    private final TestQueryService testQueryService;

    @GetMapping("temp/test")
    public ApiResponse<TestResponseDTO.TempTestDTO> testAPI(){

        TestResponseDTO.TempTestDTO tempTestDTO = TestResponseDTO.TempTestDTO.builder()
                .testString("테스트 스트링").build();

        return ApiResponse.onSuccess(tempTestDTO);
    }

    @GetMapping("temp/exception")
    public ApiResponse<TestResponseDTO.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        testQueryService.CheckFlag(flag);
        TestResponseDTO.TempExceptionDTO tempExceptionDTO = TestResponseDTO.TempExceptionDTO.builder().flag(flag).build();
        return ApiResponse.onSuccess(tempExceptionDTO);
    }
}
