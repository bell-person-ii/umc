package project.umc.app.exception.testHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;

@Service
@RequiredArgsConstructor
public class TestCommandQueryImpl implements TestQueryService{

    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TestHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}
