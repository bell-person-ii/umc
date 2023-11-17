package project.umc.app.restApiResponse.detailStatusInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK(HttpStatus.OK,"COMMON200","성공");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason(){
        return ReasonDTO.builder()
                .message(this.message)
                .code(this.code)
                .isSuccess(true)
                .build();
    } //BaseCode 구현부

    @Override
    public ReasonDTO getReasonHttpStatus(){
        return ReasonDTO.builder()
                .message(this.message)
                .code(this.code)
                .isSuccess(true)
                .httpStatus(this.httpStatus)
                .build();
    } //BaseCode 구현부

}
