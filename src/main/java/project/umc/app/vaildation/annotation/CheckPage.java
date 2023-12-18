package project.umc.app.vaildation.annotation;

import project.umc.app.vaildation.validator.CategoriesExistValidator;
import project.umc.app.vaildation.validator.CheckPageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CheckPageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPage {

    String message() default "존재하지 않는 페이지 입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
