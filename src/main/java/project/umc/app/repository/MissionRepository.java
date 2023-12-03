package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.MissionEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MissionRepository {

    private final EntityManager em;

    public void save(MissionEntity missionEntity){
        em.persist(missionEntity);
    }

    public MissionEntity findOneById(Long id){
        return em.find(MissionEntity.class,id);
    }

    public List<MissionEntity> findAllByStoreId(Long storeId){
        List<MissionEntity> missionEntities =
        em.createQuery("select m from MissionEntity m where m.storeEntity.id =: storeId")
                .setParameter("storeId",storeId).getResultList();
        return missionEntities;
    }

}
