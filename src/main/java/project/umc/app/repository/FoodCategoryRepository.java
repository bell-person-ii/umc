package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.umc.app.domain.FoodCategoryEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class FoodCategoryRepository {

    private final EntityManager em;

    public FoodCategoryEntity findFoodCategory(Long id){
        FoodCategoryEntity result = em.find(FoodCategoryEntity.class,id);
        return result;
    }

    public boolean existsById(Long value) {
        FoodCategoryEntity foodCategoryEntity = em.find(FoodCategoryEntity.class,value);
        if(foodCategoryEntity == null){
            return false;
        }
        return true;
    }
}
