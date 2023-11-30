package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.repository.FoodCategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodCategoryService {

    private final FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryEntity findOneByid(Long id){
        return foodCategoryRepository.findFoodCategory(id);
    }

    public Optional<FoodCategoryEntity> isExistFoodCategory(Long id){

        FoodCategoryEntity findFoodCategoryEntity = foodCategoryRepository.findFoodCategory(id);
        Optional<FoodCategoryEntity> optionalFoodCategoryEntity;

        if(findFoodCategoryEntity == null){
            return optionalFoodCategoryEntity = Optional.empty();
        }
        else{
            return optionalFoodCategoryEntity = Optional.of(findFoodCategoryEntity);
        }
    }
}
