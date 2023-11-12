package project.umc.app.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.domain.UserFoodCategoryEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserFoodCategoryRepository {

    private final EntityManager em;

    public void UserPreferSave(UserEntity userEntity, FoodCategoryEntity foodCategoryEntity){
        UserFoodCategoryEntity userFoodCategoryEntity = UserFoodCategoryEntity.builder()
                .userEntity(userEntity)
                .foodCategoryEntity(foodCategoryEntity)
                .build();
        em.persist(userFoodCategoryEntity);
    }
}
