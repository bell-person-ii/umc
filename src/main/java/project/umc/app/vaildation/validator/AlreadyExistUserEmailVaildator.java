package project.umc.app.vaildation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.umc.app.domain.UserEntity;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;
import project.umc.app.service.UserService;
import project.umc.app.vaildation.annotation.AlreadyExistUserEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AlreadyExistUserEmailVaildator implements ConstraintValidator<AlreadyExistUserEmail, String> {

    private final UserService userService;
    @Override
    public void initialize(AlreadyExistUserEmail constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        Optional<UserEntity> target = userService.isAlreadyExistUserByEmail(email);

        if(target.isEmpty()){
            return true;
        }
        else{
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.EXIST_EMAIL_ADDRESS.toString()).addConstraintViolation();
            return false;
        }
    }
}
