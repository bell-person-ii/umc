package project.umc.app.restApiResponse.detailStatusInfo;

import lombok.AllArgsConstructor;


public interface BaseErrorCode {

    public ErrorReasonDTO getReason();

    public ErrorReasonDTO getReasonHttpStatus();


}
