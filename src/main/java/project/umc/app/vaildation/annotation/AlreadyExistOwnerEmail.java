package project.umc.app.vaildation.annotation;


import project.umc.app.vaildation.validator.AlreadyExistOwnerEmailVaildator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AlreadyExistOwnerEmailVaildator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyExistOwnerEmail {
    String message() default "이미 가입된 이메일 주소입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
