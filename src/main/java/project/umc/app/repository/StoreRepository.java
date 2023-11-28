package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.OwnerEntity;
import project.umc.app.domain.StoreEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class StoreRepository {

    private final EntityManager em;
    public StoreEntity findOneStore(Long id){
        return em.find(StoreEntity.class,id);
    }

    public List<StoreEntity> findOneByOwnerEmail(String email){
        List<StoreEntity> storeEntityList =
                em.createQuery("select s from StoreEntity s where s.ownerEntity.email =: email").
                        setParameter("email",email).getResultList();
        return storeEntityList;
    }

    public List<StoreEntity> findOneByOwnerId(Long id){
        List<StoreEntity> storeEntityList =
                em.createQuery("select s from StoreEntity s where s.ownerEntity.id =: id").
                        setParameter("id",id).getResultList();
        return storeEntityList;
    }



    public void save(StoreEntity storeEntity){
        em.persist(storeEntity);
    }
}
