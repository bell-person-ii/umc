package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class StoreRepository {

    private final EntityManager em;
    public StoreEntity findOneStore(Long id){
        return em.find(StoreEntity.class,id);
    }

    public void save(StoreEntity storeEntity){
        em.persist(storeEntity);
    }
}
