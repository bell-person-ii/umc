package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.ReviewEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ReviewRepository {

    private final EntityManager em;

    public void save(ReviewEntity reviewEntity){
        em.persist(reviewEntity);
    }

    public ReviewEntity findOneById(Long id){
        return em.find(ReviewEntity.class,id);
    }

    public List<ReviewEntity> findAllByStoreId(Long id){
        List<ReviewEntity> reviewEntities =
                em.createQuery("select r from ReviewEntity r where r.storeEntity.id =: id")
                        .setParameter("id",id).getResultList();
        return reviewEntities;
    }
}
