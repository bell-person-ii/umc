package project.umc.app.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.umc.app.domain.UserEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepository {

    private final EntityManager em;


    //유저 등록
    public void save(UserEntity userEntity){
        em.persist(userEntity);
    }

    // 유저 단일조회(id)

    public UserEntity findOneById(Long id){
        return em.find(UserEntity.class,id);
    }

    public boolean isExistEmail(String emailAddress){
        List<UserEntity> result = em.createQuery("select u from UserEntity u where u.email = : email", UserEntity.class)
                .setParameter("email",emailAddress).getResultList();
        if(result.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    //유저 단일조회(email)
    public List<UserEntity> findOneByEmail(String email){
        List<UserEntity> result = em.createQuery("select u from UserEntity u where u.email = : email", UserEntity.class)
                .setParameter("email",email).getResultList();
        return result;
    }

}
