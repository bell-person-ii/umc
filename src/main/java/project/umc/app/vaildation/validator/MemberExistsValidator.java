package project.umc.app.vaildation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.umc.app.domain.UserEntity;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;
import project.umc.app.service.UserService;
import project.umc.app.vaildation.annotation.ExistMember;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberExistsValidator implements ConstraintValidator<ExistMember, Long> {

    private final UserService userService;
    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<UserEntity> target = userService.isExistUser(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
