package project.umc.app.vaildation.annotation;

import project.umc.app.vaildation.validator.AlreadyExistEmailVaildator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AlreadyExistEmailVaildator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyExistEmail{
    String message() default "이미 가입된 이메일 주소입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
