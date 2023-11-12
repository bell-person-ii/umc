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

    public FoodCategoryEntity findOne(Long id){
        FoodCategoryEntity result = em.find(FoodCategoryEntity.class,id);
        return result;
    }
}
