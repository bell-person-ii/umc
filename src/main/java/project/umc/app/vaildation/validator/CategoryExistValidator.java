package project.umc.app.vaildation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.restApiResponse.detailStatusInfo.ErrorStatus;
import project.umc.app.service.FoodCategoryService;
import project.umc.app.vaildation.annotation.ExistCategories;
import project.umc.app.vaildation.annotation.ExistCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryExistValidator implements ConstraintValidator<ExistCategory, Long> {

    private final FoodCategoryService foodCategoryService;

    @Override
    public void initialize(ExistCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {

        Optional<FoodCategoryEntity> target = foodCategoryService.isExistFoodCategory(id);

        if(target.isEmpty()){
            context.disableDefaultConstraintViolation(); // enum 속 상세 메시지 블럭킹
            context.buildConstraintViolationWithTemplate(ErrorStatus.FOOD_CATEGORY_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        else{
            return true;
        }


    }
}
