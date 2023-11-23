package project.umc.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.umc.app.restApiResponse.detailStatusInfo.BaseErrorCode;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorReasonDTO;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode baseErrorCode;

    public ErrorReasonDTO getErrorReason(){
        return this.baseErrorCode.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.baseErrorCode.getReasonHttpStatus();
    }

}
