package project.umc.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.umc.app.domain.FoodCategoryEntity;
import project.umc.app.domain.UserEntity;
import project.umc.app.domain.UserFoodCategoryEntity;
import project.umc.app.repository.FoodCategoryRepository;
import project.umc.app.repository.UserFoodCategoryRepository;
import project.umc.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserFoodCategoryRepository userFoodCategoryRepository;

    public void saveUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    public void saveUserPreferFoodCategory(UserEntity userEntity,List<Long>preferfoodCategoryIdList ){
        for(Long foodCategoryId : preferfoodCategoryIdList){
            FoodCategoryEntity foodCategoryEntity = foodCategoryRepository.findFoodCategory(foodCategoryId);
            System.out.println("**********************");
            System.out.println(foodCategoryId);
            userFoodCategoryRepository.UserPreferSave(userEntity,foodCategoryEntity);
        }
    }

    public boolean isNotExistEmail(String email){

        if(userRepository.isExistEmail(email) == false){
            return true;
        }
        else{
            return false;
        }
    }

    @Transactional(readOnly = true)
    public UserEntity findUserByEmail(String email){
        List<UserEntity> savedUserEntity=userRepository.findOneByEmail(email);
        return savedUserEntity.get(0);
    }

    public List<UserFoodCategoryEntity> findUserFoodPrefer(UserEntity userEntity){
        return userFoodCategoryRepository.findUserPreferFoodCategories(userEntity);
    }

    public Optional<UserEntity> isExistUser(Long id){

        UserEntity findUserEntity = userRepository.findOneById(id);

        Optional<UserEntity> optionalUserEntity;

        if(findUserEntity == null){
            return optionalUserEntity = Optional.empty();
        }
        else{
            return optionalUserEntity = Optional.of(findUserEntity);
        }
    }

    public Optional<UserEntity> isAlreadyExistUserByEmail(String email){

        List<UserEntity>userEntities = userRepository.findOneByEmail(email);
        Optional<UserEntity> userEntity;

        if(userEntities.size() == 0){
            return userEntity=Optional.empty();
        }

        else{
             userEntity = Optional.of(userRepository.findOneByEmail(email).get(0));
            return userEntity;
        }

    }
}
