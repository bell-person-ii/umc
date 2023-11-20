package project.umc.app.exception;

import project.umc.app.restApiResponse.detailStatusInfo.BaseErrorCode;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorReasonDTO;

public class GeneralException extends RuntimeException{

    private BaseErrorCode baseErrorCode;

    public ErrorReasonDTO getErrorReason(){
        return this.baseErrorCode.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.baseErrorCode.getReasonHttpStatus();
    }

}
