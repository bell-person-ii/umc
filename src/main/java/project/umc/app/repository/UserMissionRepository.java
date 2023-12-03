package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.MissionCompletion;
import project.umc.app.domain.MissionEntity;
import project.umc.app.domain.UserMissionEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserMissionRepository {

    private final EntityManager em;

    public void save(UserMissionEntity userMissionEntity){
        em.persist(userMissionEntity);
    }

    public List<UserMissionEntity> findAllByUserId(Long id){
        List<UserMissionEntity> userMissionEntityList =
                em.createQuery("select um from UserMissionEntity um where um.userEntity.id =: id",UserMissionEntity.class)
                        .setParameter("id",id).getResultList();
        return userMissionEntityList;
    }


    public List<UserMissionEntity> findNotCompletedUserMissions(Long userId){

        List<UserMissionEntity> userMissionEntityList =
                em.createQuery("select um from UserMissionEntity um where um.userEntity.id =: userId and um.missionCompletion = 'No'"
                ,UserMissionEntity.class).setParameter("userId",userId).getResultList();
        return userMissionEntityList;
    }

    public UserMissionEntity changeCompletion(Long userMissionId){
        List<UserMissionEntity> userMissionEntityList =
        em.createQuery("select um from UserMissionEntity um where um.id =: userMissionId")
                .setParameter("userMissionId",userMissionId).getResultList();

        UserMissionEntity userMissionEntity = userMissionEntityList.get(0);
        userMissionEntity.editMissionCompletion(MissionCompletion.YES);
        em.flush();
        return userMissionEntity;
    }

}
