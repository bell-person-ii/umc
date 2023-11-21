package project.umc.app.exception.testHandler;

import project.umc.app.exception.GeneralException;
import project.umc.app.restApiResponse.detailStatusInfo.BaseCode;
import project.umc.app.restApiResponse.detailStatusInfo.BaseErrorCode;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;

public class TestHandler extends GeneralException {
    public TestHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
