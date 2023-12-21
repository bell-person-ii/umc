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


    public List<ReviewEntity> findSectionByStoreId(Long id,Integer pageNumber){

        Integer pageSize = 10;

        List<ReviewEntity> reviewEntities =
                em.createQuery("select r from ReviewEntity r where r.storeEntity.id =: id",ReviewEntity.class)
                        .setParameter("id",id)
                        .setFirstResult((pageNumber-1)*pageSize)
                        .setMaxResults((pageNumber * pageSize))
                        .getResultList();
        return reviewEntities;
    }

    public List<ReviewEntity> findSectionByUserId(Long id,Integer pageNumber) {

        Integer pageSize = 10;

        List<ReviewEntity> reviewEntities =
                em.createQuery("select r from ReviewEntity r  where r.userEntity.id =: id",ReviewEntity.class)
                        .setParameter("id", id)
                        .setFirstResult((pageNumber - 1) * pageSize)
                        .setMaxResults((pageNumber * pageSize))
                        .getResultList();
        return reviewEntities;
    }


    }
