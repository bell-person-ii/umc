package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.OwnerEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class OwnerRepository {

    private final EntityManager em;

    public void save(OwnerEntity ownerEntity){
        em.persist(ownerEntity);
    }

    public OwnerEntity findOneById(Long id){
        return em.find(OwnerEntity.class,id);
    }

    public OwnerEntity findOneByEmail(String email){
        List<OwnerEntity> result = em.createQuery
                ("select o from OwnerEntity o where o.email =: email", OwnerEntity.class)
                .setParameter("email",email).getResultList();

        if(result.size() ==0){
            return null;
        }
        else {
            return result.get(0);
        }
    }
}
