package project.umc.app.vaildation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.umc.app.domain.UserEntity;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;
import project.umc.app.service.UserService;
import project.umc.app.vaildation.annotation.ExistUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<ExistUser, Long> {

    private final UserService userService;
    @Override
    public void initialize(ExistUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<UserEntity> target = userService.isExistUser(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
