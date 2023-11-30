package project.umc.app.vaildation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;
import project.umc.app.service.OwnerService;
import project.umc.app.vaildation.annotation.ExistOwner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OwnerExistValidator implements ConstraintValidator<ExistOwner, Long> {

    private final OwnerService ownerService;
    @Override
    public void initialize(ExistOwner constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        Optional<OwnerEntity> target = ownerService.isExistOwner(id);

        if (target.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.OWNER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}

