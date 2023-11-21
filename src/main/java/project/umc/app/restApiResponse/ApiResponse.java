package project.umc.app.restApiResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import project.umc.app.restApiResponse.detailStatusInfo.BaseCode;
import project.umc.app.restApiResponse.detailStatusInfo.SuccessStatus;

@AllArgsConstructor
@Getter
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final String code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 데이터가 없는 경우 null 처리
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(),
                SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode baseCode, T result){
        return new ApiResponse<>(true,baseCode.getReasonHttpStatus().getCode(),
                baseCode.getReasonHttpStatus().getMessage(),result);
    }

    public static <T>  ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(false, code, message, data);
    }
}
