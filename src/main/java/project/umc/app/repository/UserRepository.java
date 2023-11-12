package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.umc.app.domain.UserEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    //유저 등록
    public void save(UserEntity userEntity){
        em.persist(userEntity);
    }

    // 유저 단일조회(id)
    public UserEntity findOne(Long id){
        return em.find(UserEntity.class,id);
    }

}
